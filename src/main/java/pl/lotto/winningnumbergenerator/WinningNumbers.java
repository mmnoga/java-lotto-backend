package pl.lotto.winningnumbergenerator;

import java.time.LocalDateTime;
import java.util.List;

record WinningNumbers(
        LocalDateTime drawDate,
        List<Integer> numbers
) {
}
