package pl.lotto.resultchecker;

import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.TicketDto;
import pl.lotto.resultchecker.dto.PlayerResultDto;
import pl.lotto.winningnumbergenerator.NumberGeneratorFacade;
import pl.lotto.winningnumbergenerator.dto.WinningNumbersDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ResultCheckerFacade {

    private final int MIN_NUMBER_TO_WIN = 3;
    private final NumberReceiverFacade numberReceiverFacade;
    private final NumberGeneratorFacade numberGeneratorFacade;
    private final HitNumberCalculator hitNumberCalculator;
    private final PlayerResultRepository playerResultRepository;

    public ResultCheckerFacade(NumberReceiverFacade numberReceiverFacade,
                               NumberGeneratorFacade numberGeneratorFacade,
                               HitNumberCalculator hitNumberCalculator,
                               PlayerResultRepository playerResultRepository) {
        this.numberReceiverFacade = numberReceiverFacade;
        this.numberGeneratorFacade = numberGeneratorFacade;
        this.hitNumberCalculator = hitNumberCalculator;
        this.playerResultRepository = playerResultRepository;
    }

    // add scheduler
    public List<PlayerResultDto> generateResults() {
        List<TicketDto> ticketsResponseDto = numberReceiverFacade.retrieveNumbersForNextDrawDate();
        WinningNumbersDto winningNumbersResponseDto = numberGeneratorFacade.generateWonNumbersForNextDrawDate();
        List<Integer> winningNumbers = winningNumbersResponseDto.numbers();
        List<Ticket> tickets = ResultCheckerMapper.mapTicketsDtoToTicket(ticketsResponseDto);
        List<PlayerResult> playersResult = tickets.stream()
                .map(ticket -> hitNumberCalculator.calculateHitNumber(ticket, winningNumbers))
                .toList();
        List<PlayerResult> savedPlayerResults = playerResultRepository.saveAll(playersResult);
        return ResultCheckerMapper.mapPlayersResultToPlayersResultDto(savedPlayerResults);
    }

    public List<PlayerResultDto> retrieveWonTickets() {
        List<PlayerResult> tickets = playerResultRepository.findAll();
        return ResultCheckerMapper.mapPlayersResultToPlayersResultDto(tickets.stream()
                .filter(r -> r.hitNumber() >= MIN_NUMBER_TO_WIN)
                .collect(Collectors.toList()));
    }

    public Optional<PlayerResultDto> checkWinner(String lotteryId) {
        PlayerResult ticket = playerResultRepository
                .findById(lotteryId)
                .orElseThrow(() ->
                        new RuntimeException("Ticket not found"));
        return ticket.hitNumber() >= MIN_NUMBER_TO_WIN ?
                Optional.ofNullable(ResultCheckerMapper.mapPlayerResultToPlayersResultDto(ticket)) :
                Optional.empty();
    }

}
