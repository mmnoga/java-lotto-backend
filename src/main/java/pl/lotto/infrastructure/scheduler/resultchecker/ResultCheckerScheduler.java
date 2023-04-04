package pl.lotto.infrastructure.scheduler.resultchecker;

import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.lotto.resultchecker.ResultCheckerFacade;

@Component
@Log4j2
public class ResultCheckerScheduler {

    private final ResultCheckerFacade resultCheckerFacade;

    public ResultCheckerScheduler(ResultCheckerFacade resultCheckerFacade) {
        this.resultCheckerFacade = resultCheckerFacade;
    }

    @Scheduled(cron = "${result-checker.occurrence}")
    void checkResults() {
        resultCheckerFacade.generateResults();
        log.info("result checked");
    }

}
