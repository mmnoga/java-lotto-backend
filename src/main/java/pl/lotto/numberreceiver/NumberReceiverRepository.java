package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.DrawDateDto;
import pl.lotto.numberreceiver.dto.TicketListDto;


public interface NumberReceiverRepository {

    void save(TicketEntity ticket);

    TicketListDto findAll();

    TicketListDto findByDrawDate(DrawDateDto drawDate);

}
