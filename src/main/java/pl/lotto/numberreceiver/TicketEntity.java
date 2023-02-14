package pl.lotto.numberreceiver;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
@Builder
@Document(collection = "tickets")
public record TicketEntity(
        @Id
        String lotteryId,
        List<Integer> numbers,
        LocalDateTime drawDate
) {
}
