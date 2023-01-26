package pl.lotto.winningnumbergenerator;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryNumberGeneratorRepositoryImpl implements NumberGeneratorRepository{

    Map<LocalDateTime, WinningNumbers> winningNumbers = new HashMap<>();

    @Override
    public WinningNumbers save(WinningNumbers numbersEntity) {
        return null;
    }

    @Override
    public Optional<WinningNumbers> findWinningNumbersByDrawDate(LocalDateTime drawDate) {
        return Optional.empty();
    }
}
