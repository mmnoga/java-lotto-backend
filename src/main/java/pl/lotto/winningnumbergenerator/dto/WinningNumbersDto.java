package pl.lotto.winningnumbergenerator.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record WinningNumbersDto(
        LocalDateTime drawDate,
        List<Integer> numbers
) {
}
