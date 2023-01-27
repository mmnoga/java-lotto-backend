package pl.lotto.winningnumbergenerator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.lotto.numberreceiver.AdjustableClock;
import pl.lotto.numberreceiver.InMemoryNumberReceiverRepositoryTestImpl;
import pl.lotto.numberreceiver.NumberReceiverRepository;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NumberGeneratorFacadeMockitoTest {

    private final NumberGeneratorConfiguration numberGeneratorConfiguration =
            new NumberGeneratorConfiguration();
    private final Clock ticketDate = new AdjustableClock(
            LocalDateTime.of(2023, 1, 5, 11, 5)
                    .toInstant(ZoneOffset.UTC),
            ZoneId.systemDefault());
    private final NumberReceiverRepository numberReceiverRepository = new InMemoryNumberReceiverRepositoryTestImpl();
    private final NumberGeneratorRepository numberGeneratorRepository = new InMemoryNumberGeneratorRepositoryImpl();

    @Test
    @DisplayName("Should return six different winning numbers in range from 1 to 99 when generation went correct")
    void should_return_six_different_winning_numbers_in_range_from_1_to_99_when_generation_went_correct() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        NumbersGenerator numbersGenerator = mock(NumbersGenerator.class);
        NumberGeneratorFacade numberGeneratorFacade =
                numberGeneratorConfiguration.createdForTest(
                        ticketDate,
                        numberReceiverRepository,
                        numberGeneratorRepository,
                        numbersGenerator);
        // when
        when(numbersGenerator.generateRandomNumbers()).thenReturn(numbers);
        // then
        assertEquals(numbers, numberGeneratorFacade.generateWonNumbersForNextDrawDate().numbers());
    }

}
