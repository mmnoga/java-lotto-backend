package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.DrawDateDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NumberReceiverRepositoryImpl implements NumberReceiverRepository {

    Map<String, TicketEntity> tickets = new HashMap<>();

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
