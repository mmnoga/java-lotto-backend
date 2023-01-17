package pl.lotto.numberreceiver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static pl.lotto.numberreceiver.ValidationError.*;

class NumberValidator {

    private final int MIN_VALUE = 1;
    private final int MAX_VALUE = 99;
    private final int NUMBER_OF_DRAW = 6;

    ValidationResult validate(List<Integer> userNumbers) {
        List<ValidationError> errors = new ArrayList<>();
        if (isAtLeastOneNumberOutOfRange(userNumbers)) {
            errors.add(OUT_OF_RANGE);
        }
        Set<Integer> collect = new HashSet<>(userNumbers);
        if (collect.size() != NUMBER_OF_DRAW) {
            errors.add(REPEATED);
        }
        if (userNumbers.size() > NUMBER_OF_DRAW) {
            errors.add(MORE_THAN_SIX_NUMBERS);
        }
        if (userNumbers.size() < NUMBER_OF_DRAW) {
            errors.add(LESS_THAN_SIX_NUMBERS);
        }
        if (errors.isEmpty()) {
            return new ValidationResult(true, "all good");
        }
        String errorsMessage = errors.stream()
                .map(enumError -> enumError.message)
                .collect(Collectors.joining(","));
        return new ValidationResult(false, errorsMessage);
    }

    private boolean isAtLeastOneNumberOutOfRange(List<Integer> userNumbers) {
        return userNumbers.stream()
                .anyMatch(number -> number < MIN_VALUE || number > MAX_VALUE);
    }

}
