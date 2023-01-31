package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.DrawDateDto;
import pl.lotto.numberreceiver.dto.TicketDto;

import java.time.LocalDateTime;

class TicketMapper {

    public static TicketDto mapFromTicketEntityToTicketDto(TicketEntity ticket) {
        return TicketDto.builder()
                .numbers(ticket.numbers())
                .drawDate(ticket.drawDate())
                .lotteryId(ticket.lotteryId())
                .isValid(true)
                .message(null)
                .build();
    }

    public static DrawDateDto mapFromLocalDateTimeToDrawDateDto(LocalDateTime date){
        return new DrawDateDto(date);
    }

}
