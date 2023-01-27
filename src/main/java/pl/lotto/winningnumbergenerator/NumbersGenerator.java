package pl.lotto.winningnumbergenerator;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class NumbersGenerator implements NumbersGeneratorInterface {

    private final int MIN_VALUE = 1;
    private final int MAX_VALUE = 99;
    private final int NUMBER_OF_DRAW = 6;

    @Override
    public List<Integer> generateRandomNumbers() {
        Stream<Integer> randStream = Stream.generate(
                        this::generateRandomNumber)
                .distinct()
                .limit(NUMBER_OF_DRAW);
        return randStream.collect(Collectors.toList());
    }

    private int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt((MAX_VALUE - MIN_VALUE) + 1) + MIN_VALUE;
    }

}
