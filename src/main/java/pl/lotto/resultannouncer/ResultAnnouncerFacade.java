package pl.lotto.resultannouncer;

import pl.lotto.resultchecker.ResultCheckerFacade;
import pl.lotto.resultchecker.dto.PlayerResultDto;

public class ResultAnnouncerFacade {

    private final ResultCheckerFacade resultCheckerFacade;

    public ResultAnnouncerFacade(ResultCheckerFacade resultCheckerFacade) {
        this.resultCheckerFacade = resultCheckerFacade;
    }

    public PlayerResultDto checkWinner(String uniqueLotteryId) {
        return null;
                //resultCheckerFacade.checkWinner(uniqueLotteryId);
    }

}
