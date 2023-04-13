package pl.lotto;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import pl.lotto.numberreceiver.AdjustableClock;
import pl.lotto.winningnumbergenerator.NumbersGeneratorInterface;

import java.time.*;
import java.util.List;

@Configuration
@Profile("integration")
public class IntegrationTestConfiguration {
    @Bean
    @Primary
    public NumbersGeneratorInterface numbersGeneratorInterface() {
        return () -> List.of(1, 2, 3, 4, 5, 6);
    }

    @Bean
    @Primary
    AdjustableClock clock() {
        return AdjustableClock.ofLocalDateAndLocalTime(
                LocalDate.of(2023, 4, 4), LocalTime.of(11, 0),
                ZoneId.systemDefault());
    }

}
