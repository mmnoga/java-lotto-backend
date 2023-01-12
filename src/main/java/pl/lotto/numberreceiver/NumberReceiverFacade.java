package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.util.TicketIdGenerator;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.time.temporal.TemporalAdjusters;

public class NumberReceiverFacade {

    private LocalDateTime date;

    public NumberReceiverFacade(LocalDateTime date) {
        this.date = date;
    }

    public TicketDto inputNumbers(List<Integer> userNumbers) {
        NumberValidator numberValidator = new NumberValidator();
        ValidationResult validationResult = numberValidator.validate(userNumbers);
        if (validationResult.isNotValid()) {
            return new TicketDto(null,
                    userNumbers,
                    null,
                    false,
                    validationResult.message());
        }
        return new TicketDto(TicketIdGenerator.getId(),
                userNumbers,
                retrieveDrawDate(),
                true,
                null);
    }

    public LocalDateTime retrieveDrawDate() {
        LocalDateTime currentDate = this.date;
        return currentDate
                .with(TemporalAdjusters.next(DayOfWeek.SATURDAY))
                .with(LocalTime.of(12, 0, 0, 0));
    }



}
