package pl.lotto.resultchecker;

import pl.lotto.numberreceiver.dto.TicketDto;

public class ResultCheckerMapper {

    public static TicketEntity mapTicketDtoToTicket(TicketDto ticketDto){
        return new TicketEntity(ticketDto.lotteryId(),ticketDto.numbers(),ticketDto.drawDate());
    }

}
