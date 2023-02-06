package pl.lotto.resultchecker;

import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.TicketDto;
import pl.lotto.resultchecker.dto.PlayerResultDto;
import pl.lotto.winningnumbergenerator.NumberGeneratorFacade;
import pl.lotto.winningnumbergenerator.dto.WinningNumbersDto;

import java.util.List;
import java.util.stream.Collectors;

public class ResultCheckerFacade {

    private final NumberReceiverFacade numberReceiverFacade;
    private final NumberGeneratorFacade numberGeneratorFacade;

    public ResultCheckerFacade(NumberReceiverFacade numberReceiverFacade, NumberGeneratorFacade numberGeneratorFacade) {
        this.numberReceiverFacade = numberReceiverFacade;
        this.numberGeneratorFacade = numberGeneratorFacade;
    }

    // add scheduler
    public List<PlayerResultDto> generateResults() {
        List<TicketDto> ticketsDto = numberReceiverFacade
                .retrieveNumbersForNextDrawDate();
        WinningNumbersDto winningNumbersDto = numberGeneratorFacade
                .generateWonNumbersForNextDrawDate();
        List<PlayerResultDto> playerResultDtos = ticketsDto.stream()
                .map(ResultCheckerMapper::mapTicketDtoToPlayerResultDto)
                .toList();
        return playerResultDtos.stream()
                .map(r -> PlayerResultDto.builder()
                        .drawDate(r.drawDate())
                        .playerNumbers(r.playerNumbers())
                        .hitNumber(checkHitNumber(r.playerNumbers(), winningNumbersDto))
                        .build())
                .filter(r -> r.hitNumber() > 2)
                .collect(Collectors.toList());
    }

    private int checkHitNumber(List<Integer> userNumbers, WinningNumbersDto winningNumbers) {
        return (int) userNumbers.stream()
                .filter(number -> winningNumbers.numbers().contains(number))
                .count();
    }

}
