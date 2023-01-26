package pl.lotto.winningnumbergenerator;

import java.time.LocalDateTime;

public interface NumberGeneratorRepository {

    NumbersEntity save(NumbersEntity numbersEntity);

    NumbersEntity generateWinningNumbers();

    NumbersEntity getWonNumbersForDate(LocalDateTime drawDate);

}
