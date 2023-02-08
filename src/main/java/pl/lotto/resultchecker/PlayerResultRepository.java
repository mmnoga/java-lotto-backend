package pl.lotto.resultchecker;

import java.util.List;
import java.util.Optional;

public interface PlayerResultRepository {

    List<PlayerResult> saveAll(List<PlayerResult> playerResults);

    List<PlayerResult> findAll();

    Optional<PlayerResult> findById(String lotteryId);

}
