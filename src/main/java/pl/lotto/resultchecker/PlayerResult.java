package pl.lotto.resultchecker;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Document
record PlayerResult(
        @Id
        String ticketId,
        LocalDateTime drawDate,
        List<Integer> userNumbers,
        List<Integer> winningNumbers,
        int hitNumber
) {
}
