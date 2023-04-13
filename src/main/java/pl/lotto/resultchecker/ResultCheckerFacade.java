package pl.lotto.resultchecker;

import lombok.AllArgsConstructor;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.TicketDto;
import pl.lotto.resultchecker.dto.PlayerResultDto;
import pl.lotto.winningnumbergenerator.NumberGeneratorFacade;
import pl.lotto.winningnumbergenerator.dto.WinningNumbersDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@AllArgsConstructor
public class ResultCheckerFacade {
    private final int MIN_NUMBER_TO_WIN = 3;
    private final NumberReceiverFacade numberReceiverFacade;
    private final NumberGeneratorFacade numberGeneratorFacade;
    private final HitNumberCalculator hitNumberCalculator;
    private final PlayerResultRepository playerResultRepository;

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

    public PlayerResultDto checkWinner(String lotteryId) {

        PlayerResult ticket = playerResultRepository
                .findById(lotteryId)
                .orElseThrow(() ->
                        new TicketNotFoundException("Ticket not found or numbers have not been drawn yet"));

        return ResultCheckerMapper.mapPlayerResultToPlayersResultDto(ticket);

    }

}
