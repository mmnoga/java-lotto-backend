package pl.lotto.resultchecker.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record PlayerResultDto(
        String ticketId,
        LocalDateTime drawDate,
        List<Integer> playerNumbers,
        int hitNumber
) {
}
