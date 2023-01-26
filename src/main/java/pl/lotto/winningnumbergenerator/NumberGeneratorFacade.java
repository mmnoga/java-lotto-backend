package pl.lotto.winningnumbergenerator;

import pl.lotto.numberreceiver.dto.DrawDateDto;
import pl.lotto.winningnumbergenerator.dto.WinningNumbersDto;

public class NumberGeneratorFacade {

    private NumberGeneratorRepository numbersGeneratorRepository;

    public NumberGeneratorFacade(NumberGeneratorRepository numbersGeneratorRepository) {
        this.numbersGeneratorRepository = numbersGeneratorRepository;
    }

    public WinningNumbersDto retrieveWonNumbersForDate(DrawDateDto date) {
        return null;
    }

}
