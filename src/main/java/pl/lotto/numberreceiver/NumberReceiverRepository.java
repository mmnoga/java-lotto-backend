package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.DrawDateDto;

import java.util.List;


public interface NumberReceiverRepository {

    void save(TicketEntity ticket);

    List<TicketEntity> findAll();

    List<TicketEntity> findByDrawDate(DrawDateDto drawDate);

}
