package pl.lotto.numberreceiver;

import java.time.LocalDateTime;
import java.util.List;

public interface NumberReceiverRepository {

    void save(TicketEntity ticket);

    List<TicketEntity> findAll();

    List<TicketEntity> findByDrawDate(LocalDateTime drawDate);

}