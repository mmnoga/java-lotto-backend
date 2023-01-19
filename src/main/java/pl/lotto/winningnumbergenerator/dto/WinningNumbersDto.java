package pl.lotto.winningnumbergenerator.dto;

import pl.lotto.numberreceiver.dto.DrawDateDto;

import java.util.List;

public record WinningNumbersDto(
        DrawDateDto drawDate,
        List<Integer> numbers
) {
}
