package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.DrawDateDto;
import pl.lotto.numberreceiver.dto.TicketDto;
import pl.lotto.numberreceiver.dto.TicketListDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryNumberGenerator implements NumberReceiverRepository {

    Map<String, TicketEntity> tickets = new HashMap<>();

    public TicketEntity mapTicket(TicketDto ticket) {
        return new TicketEntity(ticket.lotteryId(), ticket.numbers(), ticket.drawDate());
    }

    public TicketListDto mapList(List<TicketEntity> list){
        return new TicketListDto(list);
    }

    @Override
    public void save(TicketEntity ticket) {
        tickets.put(ticket.lotteryId(), ticket);
    }

    @Override
    public List<TicketEntity> findAll() {
        return tickets.values()
                .stream()
                .toList();
    }

    @Override
    public List<TicketEntity> findByDrawDate(DrawDateDto drawDate) {
        return tickets.values()
                .stream()
                .filter(t -> t.drawDate().equals(drawDate))
                .collect(Collectors.toList());
    }

}
