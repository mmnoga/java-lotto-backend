package pl.lotto.resultannouncer.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ResultDto(
        String ticketId,
        LocalDateTime drawDate,
        List<Integer> playerNumbers,
        List<Integer> winningNumbers,
        int hitNumber
) {
}
