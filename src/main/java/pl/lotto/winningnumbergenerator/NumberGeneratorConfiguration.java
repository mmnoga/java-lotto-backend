package pl.lotto.winningnumbergenerator;

import pl.lotto.numberreceiver.NumberReceiverFacade;

public class NumberGeneratorConfiguration {

    public NumberGeneratorFacade createdForTest(NumberReceiverFacade numberReceiverFacade,
                                                NumberGeneratorRepository numberGeneratorRepository,
                                                NumbersGeneratorInterface numbersGenerator) {

        return new NumberGeneratorFacade(numberGeneratorRepository, numberReceiverFacade, numbersGenerator);
    }

}
