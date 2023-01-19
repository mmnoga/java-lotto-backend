package pl.lotto.numberreceiver;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
@Builder
public record TicketEntity(
        String lotteryId,
        List<Integer> numbers,
        LocalDateTime drawDate
) {
}
