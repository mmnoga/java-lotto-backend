package pl.lotto.numberreceiver;

import java.util.UUID;

class TicketIdGenerator {

    public String getId() {
        return UUID.randomUUID().toString();
    }

}
