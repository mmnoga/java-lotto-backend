package pl.lotto.winningnumbergenerator;

import java.time.LocalDateTime;

public class InMemoryNumberGeneratorRepositoryImpl implements NumberGeneratorRepository{

    @Override
    public NumbersEntity save(NumbersEntity numbersEntity) {
        return null;
    }

    @Override
    public NumbersEntity generateWinningNumbers() {
        return null;
    }

    @Override
    public NumbersEntity getWonNumbersForDate(LocalDateTime drawDate) {
        return null;
    }

}
