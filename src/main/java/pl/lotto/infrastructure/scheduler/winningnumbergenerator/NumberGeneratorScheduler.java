package pl.lotto.infrastructure.scheduler.winningnumbergenerator;

import java.util.Optional;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.DrawDateDto;
import pl.lotto.winningnumbergenerator.NumberGeneratorFacade;
import pl.lotto.winningnumbergenerator.dto.WinningNumbersDto;

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
        DrawDateDto nextDrawDate = numberReceiverFacade.retrieveNextDrawDate();
        try {
            Optional<WinningNumbersDto> winningNumbersDto = numberGeneratorFacade.retrieveWonNumbersForDate(nextDrawDate);
            if (winningNumbersDto.isPresent()) {
                log.error("winning numbers have already generated");
                throw new IllegalStateException("winning numbers have already generated");
            }
        } catch (Exception e) {
            new IllegalStateException("no numbers found for drawing date");
        }
        numberGeneratorFacade.generateWonNumbersForNextDrawDate();
    }

}
