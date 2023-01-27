package pl.lotto.winningnumbergenerator;

import pl.lotto.numberreceiver.NumberReceiverConfiguration;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.NumberReceiverRepository;

import java.time.Clock;

public class NumberGeneratorConfiguration {

    public NumberGeneratorFacade createdForTest(Clock clock,
                                                NumberReceiverRepository numberReceiverRepository,
                                                NumberGeneratorRepository numberGeneratorRepository,
                                                NumbersGeneratorInterface numbersGenerator) {
        NumberReceiverConfiguration numberReceiverConfiguration = new NumberReceiverConfiguration();
        NumberReceiverFacade numberReceiverFacade =
                numberReceiverConfiguration.createdForTest(clock, numberReceiverRepository);
        return new NumberGeneratorFacade(numberGeneratorRepository, numberReceiverFacade, numbersGenerator);
    }

}
