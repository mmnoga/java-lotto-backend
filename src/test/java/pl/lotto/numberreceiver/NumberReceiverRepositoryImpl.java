package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.DrawDateDto;
import pl.lotto.numberreceiver.dto.TicketListDto;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class NumberReceiverRepositoryImpl implements NumberReceiverRepository {

    Map<String, TicketEntity> tickets = new HashMap<>();

    @Override
    public void save(TicketEntity ticket) {
        tickets.put(ticket.lotteryId(), ticket);
    }

    @Override
    public TicketListDto findAll() {
        return new TicketListDto(tickets.values()
                .stream()
                .toList());
    }

    @Override
    public TicketListDto findByDrawDate(DrawDateDto drawDate) {
        return new TicketListDto(tickets.values()
                .stream()
                .filter(t -> t.drawDate().equals(drawDate))
                .collect(Collectors.toList()));
    }

}
