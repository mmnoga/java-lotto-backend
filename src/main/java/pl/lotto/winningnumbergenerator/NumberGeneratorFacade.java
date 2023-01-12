package pl.lotto.winningnumbergenerator;

import java.io.IOException;
import java.time.LocalDateTime;

public class NumberGeneratorFacade {

    private final NumberGenerator numberGenerator;

    public NumberGeneratorFacade(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public WinningNumbers retrieveWonNumbersForDate(LocalDateTime date) {
        return new WinningNumbers(date, numberGenerator.getNumbers());
    }

}
