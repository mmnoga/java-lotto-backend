package pl.lotto.numberreceiver.dto;

import pl.lotto.numberreceiver.TicketEntity;

import java.util.List;

public record TicketListDto(
        List<TicketEntity> ticketList
) {
}
