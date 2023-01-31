package pl.lotto.resultchecker.dto;

import java.time.LocalDateTime;
import java.util.List;

public record PlayerResultDto(
        LocalDateTime drawDate,
        List<Integer> playerNumbers,
        int hitNumber
) {
}
