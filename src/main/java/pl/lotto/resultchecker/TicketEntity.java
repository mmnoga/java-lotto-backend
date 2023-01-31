package pl.lotto.resultchecker;

import java.time.LocalDateTime;
import java.util.List;

record TicketEntity(
        String lotteryId,
        List<Integer> numbers,
        LocalDateTime drawDate
) {
}