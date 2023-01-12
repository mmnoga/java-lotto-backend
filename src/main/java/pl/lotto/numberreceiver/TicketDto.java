package pl.lotto.numberreceiver;

import java.time.LocalDateTime;
import java.util.List;

public record TicketDto(
        String lotteryId,
        List<Integer> numbers,
        LocalDateTime drawDate,
        boolean isValid,
        String message
) {
}
