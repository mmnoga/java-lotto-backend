package pl.lotto.numberreceiver.util;

import java.util.UUID;

public class TicketIdGenerator {

    public static String getId() {
        return UUID.randomUUID().toString();
    }
}
