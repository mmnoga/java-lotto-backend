package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.DrawDateDto;

import java.util.List;

public record TicketEntity(
        String lotteryId,
        List<Integer> numbers,
        DrawDateDto drawDate
) {
}
