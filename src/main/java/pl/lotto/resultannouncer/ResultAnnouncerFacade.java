package pl.lotto.resultannouncer;

import pl.lotto.resultchecker.ResultCheckerFacade;

public class ResultAnnouncerFacade {

    private final ResultCheckerFacade resultCheckerFacade;

    public ResultAnnouncerFacade(ResultCheckerFacade resultCheckerFacade) {
        this.resultCheckerFacade = resultCheckerFacade;
    }

    public boolean checkWinner(String uniqueLotteryId) {
        return resultCheckerFacade.checkWinner(uniqueLotteryId);
    }

}
