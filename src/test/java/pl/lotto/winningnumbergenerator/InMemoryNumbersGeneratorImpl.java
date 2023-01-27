package pl.lotto.winningnumbergenerator;

import java.util.List;

public class InMemoryNumbersGeneratorImpl extends NumbersGenerator implements NumbersGeneratorInterface {

    private final List<Integer> numbers;

    public InMemoryNumbersGeneratorImpl(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public List<Integer> generateRandomNumbers() {
        return numbers;
    }

}
