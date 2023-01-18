package pl.lotto.numberreceiver.dto;

import java.util.List;

public record TicketDto(
        String lotteryId,
        List<Integer> numbers,
        DrawDateDto drawDate,
        boolean isValid,
        String message
) {
}
