package pl.lotto.infrastructure.configurator.numberreceiver;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "lotto.receiver")
@Getter
@Setter
public class NumberValidatorConfigurator {

    private int minNumber;
    private int maxNumber;
    private int numberOfDraw;

}
