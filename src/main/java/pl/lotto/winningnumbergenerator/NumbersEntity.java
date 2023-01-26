package pl.lotto.winningnumbergenerator;

import java.time.LocalDateTime;
import java.util.List;

public record NumbersEntity(
        LocalDateTime drawDate,
        List<Integer> numbers
) {
}
