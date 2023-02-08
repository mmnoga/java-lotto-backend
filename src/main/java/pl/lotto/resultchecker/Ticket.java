package pl.lotto.resultchecker;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
record Ticket(
        String lotteryId,
        List<Integer> userNumbers,
        List<Integer> winningNumbers,
        LocalDateTime drawDate
) {
}