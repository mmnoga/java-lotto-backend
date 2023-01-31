package pl.lotto.resultchecker;

import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.TicketDto;
import pl.lotto.resultchecker.dto.PlayerResultDto;
import pl.lotto.winningnumbergenerator.NumberGeneratorFacade;

import java.util.List;

public class ResultCheckerFacade {

    private final NumberReceiverFacade numberReceiverFacade;
    private final NumberGeneratorFacade numberGeneratorFacade;

    public ResultCheckerFacade(NumberReceiverFacade numberReceiverFacade, NumberGeneratorFacade numberGeneratorFacade) {
        this.numberReceiverFacade = numberReceiverFacade;
        this.numberGeneratorFacade = numberGeneratorFacade;
    }

    // add scheduler
    public List<PlayerResultDto> generateResults() {
        List<TicketDto> tickets = numberReceiverFacade.retrieveNumbersForNextDrawDate();
        return null;
    }

    public String checkWinner(String uniqueLotteryId) {
        return null;
    }
}
