package pl.lotto.numberreceiver;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;

class DrawDateGenerator {

    private final DayOfWeek drawDay = DayOfWeek.SATURDAY;
    private final LocalTime drawTime = LocalTime.of(12, 0, 0, 0);

    public LocalDateTime generateOrGetDrawDate(LocalDateTime ticketDate) {
        if (isDrawDay(ticketDate) && isBeforeDrawTime(ticketDate)) {
            return ticketDate
                    .with(drawTime)
                    .with(TemporalAdjusters.nextOrSame(drawDay));
        }
        return ticketDate
                .with(drawTime)
                .with(TemporalAdjusters.next(drawDay));
    }

    private boolean isDrawDay(LocalDateTime ticketDateAndTime) {
        return ticketDateAndTime.getDayOfWeek().equals(drawDay);
    }

    private boolean isBeforeDrawTime(LocalDateTime ticketDateAndTime) {
        return ticketDateAndTime.getHour() < drawTime.getHour();
    }

}
