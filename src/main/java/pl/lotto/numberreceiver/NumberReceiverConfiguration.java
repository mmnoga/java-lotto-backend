package pl.lotto.numberreceiver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class NumberReceiverConfiguration {

    public NumberReceiverFacade createdForTest(Clock clock, NumberReceiverRepository numberReceiverRepository) {
        NumberValidator numberValidator = new NumberValidator();
        TicketIdGenerator ticketIdGenerator = new TicketIdGenerator();
        DrawDateGenerator drawDateGenerator = new DrawDateGenerator();
        return new NumberReceiverFacade(clock, numberReceiverRepository, numberValidator, drawDateGenerator, ticketIdGenerator);
    }

    @Bean
    public NumberReceiverFacade createdForProduction(Clock clock, NumberReceiverRepository numberReceiverRepository) {
        NumberValidator numberValidator = new NumberValidator();
        TicketIdGenerator ticketIdGenerator = new TicketIdGenerator();
        DrawDateGenerator drawDateGenerator = new DrawDateGenerator();
        return new NumberReceiverFacade(clock, numberReceiverRepository, numberValidator, drawDateGenerator, ticketIdGenerator);
    }

    @Bean
    Clock timeForProduction() {
        return Clock.systemDefaultZone();
    }

}