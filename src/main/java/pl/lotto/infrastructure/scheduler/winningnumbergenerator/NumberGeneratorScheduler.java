package pl.lotto.infrastructure.scheduler.winningnumbergenerator;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.DrawDateDto;
import pl.lotto.winningnumbergenerator.NumberGeneratorFacade;
import pl.lotto.winningnumbergenerator.dto.WinningNumbersDto;

import java.util.Optional;

@Component
public class NumberGeneratorScheduler {

    private final NumberGeneratorFacade numberGeneratorFacade;
    private final NumberReceiverFacade numberReceiverFacade;

    public NumberGeneratorScheduler(
            NumberGeneratorFacade numberGeneratorFacade,
            NumberReceiverFacade numberReceiverFacade) {
        this.numberGeneratorFacade = numberGeneratorFacade;
        this.numberReceiverFacade = numberReceiverFacade;
    }

    @Scheduled(cron = "*/10 * * * * *")
    void generateWinningNumbers() {
        DrawDateDto nextDrawDate = numberReceiverFacade.retrieveNextDrawDate();
        Optional<WinningNumbersDto> winningNumbersDto = numberGeneratorFacade.retrieveWonNumbersForDate(nextDrawDate);
        if (winningNumbersDto.isPresent()) {
            throw new IllegalStateException("winning numbers have already generated");
        }
        numberGeneratorFacade.generateWonNumbersForNextDrawDate();
    }

}
