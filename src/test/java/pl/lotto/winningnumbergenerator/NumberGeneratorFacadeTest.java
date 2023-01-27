package pl.lotto.winningnumbergenerator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.lotto.numberreceiver.*;
import pl.lotto.numberreceiver.dto.DrawDateDto;
import pl.lotto.winningnumbergenerator.dto.WinningNumbersDto;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class NumberGeneratorFacadeTest {

    private final NumberReceiverRepository numberReceiverRepository = new InMemoryNumberReceiverRepositoryTestImpl();
    private final NumberGeneratorRepository numberGeneratorRepository = new InMemoryNumberGeneratorRepositoryImpl();
    private final NumberGeneratorConfiguration numberGeneratorConfiguration =
            new NumberGeneratorConfiguration();
    private final Clock ticketDate = new AdjustableClock(
            LocalDateTime.of(2023, 1, 5, 11, 5)
                    .toInstant(ZoneOffset.UTC),
            ZoneId.systemDefault());
    private final NumbersGenerator numbersGenerator = new NumbersGenerator();

    @Test
    @DisplayName("Should return winning numbers for draw date when winning numbers was generated for that date")
    void should_return_winning_numbers_for_draw_date_when_winning_numbers_was_generated_for_that_date() {
        // given
        LocalDateTime drawDate1 = LocalDateTime.of(2023, 1, 7, 12, 0);
        LocalDateTime drawDate2 = LocalDateTime.of(2023, 1, 24, 12, 0);
        DrawDateDto drawDateResult = new DrawDateDto(drawDate1);
        WinningNumbers winningNumbersForDrawDate1 = new WinningNumbers(drawDate1, List.of(10, 11, 12, 13, 14, 15));
        WinningNumbers winningNumbersForDrawDate2 = new WinningNumbers(drawDate2, List.of(20, 21, 22, 23, 24, 25));
        numberGeneratorRepository.save(winningNumbersForDrawDate1);
        numberGeneratorRepository.save(winningNumbersForDrawDate2);
        NumberGeneratorFacade numberGeneratorFacade =
                numberGeneratorConfiguration.createdForTest(
                        ticketDate,
                        numberReceiverRepository,
                        numberGeneratorRepository,
                        numbersGenerator);
        // when
        WinningNumbersDto result = numberGeneratorFacade.retrieveWonNumbersForDate(drawDateResult);
        // then
        assertThat(result.numbers(), equalTo(winningNumbersForDrawDate1.numbers()));
    }

    @Test
    @DisplayName("Should throw runtime exception when winning numbers was not generated for given date")
    void should_throw_runtime_exception_when_winning_numbers_was_not_generated_for_given_date() {
        // given
        DrawDateDto drawDate = new DrawDateDto(LocalDateTime.of(2023, 1, 21, 12, 0));
        NumberGeneratorFacade numberGeneratorFacade =
                numberGeneratorConfiguration.createdForTest(
                        ticketDate,
                        numberReceiverRepository,
                        numberGeneratorRepository,
                        numbersGenerator);
        // when
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> numberGeneratorFacade.retrieveWonNumbersForDate(drawDate));
        // then
        assertEquals("Not found winning numbers for drawing date", exception.getMessage());
    }

    @Test
    @DisplayName("Should return six different winning numbers in range from 1 to 99 when generation went correct")
    void should_return_six_different_winning_numbers_in_range_from_1_to_99_when_generation_went_correct() {
        // given
        LocalDateTime drawDate = LocalDateTime.of(2023, 1, 21, 12, 0);
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        WinningNumbers winningNumbers = new WinningNumbers(drawDate, numbers);
        NumbersGeneratorInterface numbersGenerator = new InMemoryNumbersGeneratorTestImpl(numbers);
        NumberGeneratorFacade numberGeneratorFacade =
                numberGeneratorConfiguration.createdForTest(
                        ticketDate,
                        numberReceiverRepository,
                        numberGeneratorRepository,
                        numbersGenerator);
        // when
        WinningNumbersDto result = numberGeneratorFacade.generateWonNumbersForNextDrawDate();
        // then
        assertThat(result.numbers(), equalTo(winningNumbers.numbers()));
    }

}