package pl.lotto.infrastructure.scheduler.winningnumbergenerator;

import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.winningnumbergenerator.NumberGeneratorFacade;

@Component
@Log4j2
public class NumberGeneratorScheduler {

    private final NumberGeneratorFacade numberGeneratorFacade;
    private final NumberReceiverFacade numberReceiverFacade;

    public NumberGeneratorScheduler(
            NumberGeneratorFacade numberGeneratorFacade,
            NumberReceiverFacade numberReceiverFacade) {
        this.numberGeneratorFacade = numberGeneratorFacade;
        this.numberReceiverFacade = numberReceiverFacade;
    }

    @Scheduled(cron = "${number-generator.occurrence}")
    void generateWinningNumbers() {
        log.info("scheduler started");
        numberGeneratorFacade.generateWonNumbersForNextDrawDate();
    }

}
