package pl.lotto.winningnumbergenerator;

import java.util.List;

public class InMemoryNumbersGeneratorTestImpl implements NumbersGeneratorInterface {

    private final List<Integer> numbers;

    public InMemoryNumbersGeneratorTestImpl(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public List<Integer> generateRandomNumbers() {
        return numbers;
    }

}
