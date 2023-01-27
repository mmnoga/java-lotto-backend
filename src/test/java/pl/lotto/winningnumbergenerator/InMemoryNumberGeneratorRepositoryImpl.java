package pl.lotto.winningnumbergenerator;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryNumberGeneratorRepositoryImpl implements NumberGeneratorRepository {

    Map<LocalDateTime, WinningNumbers> winningNumbersByDate = new HashMap<>();

    @Override
    public WinningNumbers save(WinningNumbers numbersEntity) {
        winningNumbersByDate.put(numbersEntity.drawDate(), numbersEntity);
        return numbersEntity;
    }

    @Override
    public Optional<WinningNumbers> findWinningNumbersByDrawDate(LocalDateTime drawDate) {
        return Optional.ofNullable(winningNumbersByDate.get(drawDate));
    }

}
