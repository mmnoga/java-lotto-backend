package pl.lotto.resultchecker;

import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.winningnumbergenerator.NumberGeneratorFacade;

public class ResultCheckerConfiguration {

    public ResultCheckerFacade createdForTest(NumberReceiverFacade numberReceiverFacade,
                                              NumberGeneratorFacade numberGeneratorFacade,
                                              PlayerResultRepository playerResultRepository) {
        HitNumberCalculator hitNumberCalculator = new HitNumberCalculator();
        return new ResultCheckerFacade(numberReceiverFacade,
                numberGeneratorFacade,
                hitNumberCalculator,
                playerResultRepository);
    }

}
