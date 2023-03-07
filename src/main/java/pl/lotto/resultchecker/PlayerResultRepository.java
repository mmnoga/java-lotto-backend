package pl.lotto.resultchecker;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerResultRepository extends MongoRepository<PlayerResult, String> {

    Optional<PlayerResult> findById(String lotteryId);

}
