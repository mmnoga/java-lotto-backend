package pl.lotto.winningnumbergenerator;

import java.time.LocalDateTime;
import java.util.Optional;

public interface NumberGeneratorRepository {

    WinningNumbers save(WinningNumbers numbersEntity);

    Optional<WinningNumbers> findWinningNumbersByDrawDate(LocalDateTime drawDate);

}
