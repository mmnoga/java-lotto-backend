package pl.lotto.numberreceiver.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
@Builder
public record TicketDto(
        String lotteryId,
        List<Integer> userNumbers,
        LocalDateTime drawDate,
        boolean isValid,
        String message
) {
}
