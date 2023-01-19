package pl.lotto.infrastructure;

import pl.lotto.numberreceiver.dto.DrawDateDto;
import pl.lotto.winningnumbergenerator.NumberGeneratorFacade;
import pl.lotto.winningnumbergenerator.dto.WinningNumbersDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class NumberGeneratorConsoleApplication {

    static int MIN_VALUE = 1;
    static int MAX_VALUE = 99;
    static int NUMBER_OF_DRAW = 6;

    public static void main(String[] args) {
        NumberGeneratorFacade numberGeneratorFacade = new NumberGeneratorFacade();
        List<Integer> lottoNumbers = getNumbers();
        DrawDateDto drawDate = new DrawDateDto(LocalDateTime.of(2023,1,21,12,0));
        WinningNumbersDto numbers = numberGeneratorFacade.retrieveWonNumbersForDate(drawDate);
    }

    private static List<Integer> getNumbers() {
        Stream<Integer> randStream = Stream.generate(
                        () -> generateRandomNumber())
                .distinct()
                .limit(NUMBER_OF_DRAW);
        return randStream.collect(Collectors.toList());
    }

    private static int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt((MAX_VALUE - MIN_VALUE) + 1) + MIN_VALUE;
    }

}
