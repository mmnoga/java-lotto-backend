package pl.lotto.numberreceiver;

import java.time.LocalDateTime;
import java.util.List;

record Ticket(
        String lotteryId,
        List<Integer> numbers,
        LocalDateTime drawDate,
        boolean isValid,
        String message
) {
}
