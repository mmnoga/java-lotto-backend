package pl.lotto.winningnumbergenerator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.lotto.numberreceiver.NumberReceiverFacade;

@Configuration
public class NumberGeneratorConfiguration {

    public NumberGeneratorFacade createdForTest(NumberReceiverFacade numberReceiverFacade,
                                                NumberGeneratorRepository numberGeneratorRepository,
                                                NumbersGeneratorInterface numbersGenerator) {

        return new NumberGeneratorFacade(numberGeneratorRepository, numberReceiverFacade, numbersGenerator);
    }

    @Bean
    public NumberGeneratorFacade numberGeneratorFacade(NumberReceiverFacade numberReceiverFacade,
                                                       NumberGeneratorRepository numberGeneratorRepository,
                                                       NumbersGeneratorInterface numbersGeneratorInterface) {
        return new NumberGeneratorFacade(numberGeneratorRepository, numberReceiverFacade, numbersGeneratorInterface);
    }

    @Bean
    public NumbersGeneratorInterface numbersGeneratorInterface() {
        return new NumbersGenerator();
    }

}
