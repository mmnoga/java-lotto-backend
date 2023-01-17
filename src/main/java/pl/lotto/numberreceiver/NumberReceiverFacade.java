package pl.lotto.numberreceiver;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.time.temporal.TemporalAdjusters;

public class NumberReceiverFacade {

    public TicketDto inputNumbers(List<Integer> userNumbers) {
        NumberValidator numberValidator = new NumberValidator();
        TicketIdGenerator ticketIdGenerator = new TicketIdGenerator();
        ValidationResult validationResult = numberValidator.validate(userNumbers);
        if (validationResult.isNotValid()) {
            return new TicketDto(null,
                    userNumbers,
                    null,
                    false,
                    validationResult.message());
        }
        return new TicketDto(ticketIdGenerator.getId(),
                userNumbers,
                retrieveDrawDate(),
                true,
                null);
    }

    public LocalDateTime retrieveDrawDate() {
        LocalDateTime currentDate = LocalDateTime.now();
        return currentDate
                .with(TemporalAdjusters.next(DayOfWeek.SATURDAY))
                .with(LocalTime.of(12, 0, 0, 0));
    }

}
