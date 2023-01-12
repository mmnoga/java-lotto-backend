package pl.lotto.winningnumbergenerator;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class NumberGenerator implements NumberProvider {

    @Override
    public List<Integer> getNumbers() {
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
