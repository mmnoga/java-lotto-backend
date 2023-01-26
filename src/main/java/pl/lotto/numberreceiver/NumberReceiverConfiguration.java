package pl.lotto.numberreceiver;

import java.time.Clock;

public class NumberReceiverConfiguration {

    public NumberReceiverFacade createdForTest(Clock clock, NumberReceiverRepository numberReceiverRepository) {
        NumberValidator numberValidator = new NumberValidator();
        TicketIdGenerator ticketIdGenerator = new TicketIdGenerator();
        DrawDateGenerator drawDateGenerator = new DrawDateGenerator();
        return new NumberReceiverFacade(clock, numberReceiverRepository, numberValidator, drawDateGenerator, ticketIdGenerator);
    }

}