package pl.lotto.winningnumbergenerator;

import java.util.List;

public class InMemoryNumberGenerator implements NumberProvider{

    private final List<Integer> numbers;

    public InMemoryNumberGenerator(List<Integer> numbers){
        this.numbers = numbers;
    }

    @Override
    public List<Integer> getNumbers() {
        return this.numbers;
    }
}
