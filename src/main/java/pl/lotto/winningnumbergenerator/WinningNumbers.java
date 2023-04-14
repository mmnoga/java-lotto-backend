package pl.lotto.winningnumbergenerator;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document
record WinningNumbers(
        @Indexed(unique = true)
        LocalDateTime drawDate,
        List<Integer> number
) {
}
