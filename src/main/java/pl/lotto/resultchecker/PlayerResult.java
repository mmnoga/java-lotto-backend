package pl.lotto.resultchecker;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
record PlayerResult(
        String ticketId,
        LocalDateTime drawDate,
        List<Integer> userNumbers,
        List<Integer> winningNumbers,
        int hitNumber
) {
}
