package pl.lotto.resultchecker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.winningnumbergenerator.NumberGeneratorFacade;

@Configuration
public class ResultCheckerConfiguration {

    @Bean
    public ResultCheckerFacade resultChecker(NumberReceiverFacade numberReceiverFacade,
                                              NumberGeneratorFacade numberGeneratorFacade,
                                              PlayerResultRepository playerResultRepository) {
        HitNumberCalculator hitNumberCalculator = new HitNumberCalculator();
        return new ResultCheckerFacade(numberReceiverFacade,
                numberGeneratorFacade,
                hitNumberCalculator,
                playerResultRepository);
    }

}
