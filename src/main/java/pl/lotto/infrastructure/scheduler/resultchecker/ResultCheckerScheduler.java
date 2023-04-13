package pl.lotto.infrastructure.scheduler.resultchecker;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.lotto.resultchecker.ResultCheckerFacade;

@Component
@Log4j2
@AllArgsConstructor
public class ResultCheckerScheduler {
    private final ResultCheckerFacade resultCheckerFacade;

    @Scheduled(cron = "${result-checker.occurrence}")
    void checkResults() {
        resultCheckerFacade.generateResults();
        log.info("result checked");
    }

}
