package pl.lotto.numberreceiver;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NumberReceiverRepository extends MongoRepository<TicketEntity, String> {

//    TicketEntity save(TicketEntity ticket);

    List<TicketEntity> findAll();

    List<TicketEntity> findByDrawDate(LocalDateTime drawDate);

}
