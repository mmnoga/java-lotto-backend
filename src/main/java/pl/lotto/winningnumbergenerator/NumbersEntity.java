package pl.lotto.winningnumbergenerator;

import pl.lotto.numberreceiver.dto.DrawDateDto;

import java.util.List;

public record NumbersEntity(
        DrawDateDto drawDate,
        List<Integer> numbers
) {
}
