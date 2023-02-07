package pl.lotto.resultchecker;

import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.TicketDto;
import pl.lotto.resultchecker.dto.PlayerResultDto;
import pl.lotto.winningnumbergenerator.NumberGeneratorFacade;
import pl.lotto.winningnumbergenerator.dto.WinningNumbersDto;

import java.util.List;
import java.util.stream.Collectors;

public class ResultCheckerFacade {

    private final int MIN_NUMBER_TO_WIN = 3;

    private final NumberReceiverFacade numberReceiverFacade;
    private final NumberGeneratorFacade numberGeneratorFacade;

    public ResultCheckerFacade(NumberReceiverFacade numberReceiverFacade,
                               NumberGeneratorFacade numberGeneratorFacade) {
        this.numberReceiverFacade = numberReceiverFacade;
        this.numberGeneratorFacade = numberGeneratorFacade;
    }

    // add scheduler
    public List<PlayerResultDto> generateResults() {
        List<TicketDto> tickets = numberReceiverFacade
                .retrieveNumbersForNextDrawDate();
        WinningNumbersDto winningNumbers = numberGeneratorFacade
                .generateWonNumbersForNextDrawDate();
        return tickets.stream()
                .map(ResultCheckerMapper::mapTicketDtoToPlayerResultDto)
                .map(playerResult -> calculateHitNumber(playerResult, winningNumbers))
                .filter(playerResult -> playerResult.hitNumber() >= MIN_NUMBER_TO_WIN)
                .collect(Collectors.toList());
    }

    private PlayerResultDto calculateHitNumber(PlayerResultDto playerResult, WinningNumbersDto winningNumbers) {
        int hitNumber = (int) playerResult.playerNumbers().stream()
                .filter(number -> winningNumbers.numbers().contains(number))
                .count();
        return PlayerResultDto.builder()
                .hitNumber(hitNumber)
                .ticketId(playerResult.ticketId())
                .drawDate(playerResult.drawDate())
                .playerNumbers(playerResult.playerNumbers())
                .build();
    }

    public boolean checkWinner(String lotteryId) {
        List<PlayerResultDto> playersResultDto = generateResults();
        return playersResultDto.stream()
                .anyMatch(r -> r.ticketId().equals(lotteryId));
    }

}
