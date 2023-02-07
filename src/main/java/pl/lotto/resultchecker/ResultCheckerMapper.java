package pl.lotto.resultchecker;

import pl.lotto.numberreceiver.dto.TicketDto;
import pl.lotto.resultchecker.dto.PlayerResultDto;

class ResultCheckerMapper {

    public static TicketEntity mapTicketDtoToTicket(TicketDto ticketDto){
        return new TicketEntity(ticketDto.lotteryId(),ticketDto.numbers(),ticketDto.drawDate());
    }

    public static PlayerResultDto mapTicketDtoToPlayerResultDto(TicketDto ticketDto){
        return PlayerResultDto.builder()
                .ticketId(ticketDto.lotteryId())
                .playerNumbers(ticketDto.numbers())
                .drawDate(ticketDto.drawDate())
                .build();
    }

}
