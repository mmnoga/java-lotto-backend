package pl.lotto.winningnumbergenerator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.lotto.numberreceiver.*;
import pl.lotto.numberreceiver.dto.DrawDateDto;
import pl.lotto.winningnumbergenerator.dto.WinningNumbersDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class NumberGeneratorFacadeMockitoTest {

    private final NumberGeneratorRepository numberGeneratorRepository = new InMemoryNumberGeneratorRepositoryImpl();
    private NumberGeneratorFacade numberGeneratorFacade;
    private final NumberGeneratorConfiguration numberGeneratorConfiguration =
            new NumberGeneratorConfiguration();

    @Test
    @DisplayName("Should return winning userNumbers for draw date when winning userNumbers was generated for that date")
    void should_return_winning_numbers_for_draw_date_when_winning_numbers_was_generated_for_that_date() {
        // given
        LocalDateTime drawDate = LocalDateTime.of(2023, 1, 7, 12, 0);
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        DrawDateDto drawDateForResult = mock(DrawDateDto.class);
        when(drawDateForResult.drawDate()).thenReturn(drawDate);

        WinningNumbers winningNumbers = mock(WinningNumbers.class);
        when(winningNumbers.numbers()).thenReturn(numbers);

        NumberGeneratorRepository numberGeneratorRepository = mock(NumberGeneratorRepository.class);
        when(numberGeneratorRepository
                .findWinningNumbersByDrawDate(drawDate))
                .thenReturn(Optional.of(winningNumbers));

        numberGeneratorFacade = numberGeneratorConfiguration
                .createdForTest(
                        null,
                        numberGeneratorRepository,
                        null
                );
        // when
        WinningNumbersDto result = numberGeneratorFacade.retrieveWonNumbersForDate(drawDateForResult);
        // then
        assertEquals(result.numbers(), numbers);
    }

    @Test
    @DisplayName("Should throw runtime exception when winning userNumbers was not generated for given date")
    void should_throw_runtime_exception_when_winning_numbers_was_not_generated_for_given_date() {
        // given
        LocalDateTime drawDate = LocalDateTime.of(2023, 1, 7, 12, 0);

        DrawDateDto drawDateDto = mock(DrawDateDto.class);
        when(drawDateDto.drawDate()).thenReturn(drawDate);

        NumberGeneratorRepository numberGeneratorRepository = mock(NumberGeneratorRepository.class);
        when(numberGeneratorRepository
                .findWinningNumbersByDrawDate(drawDate))
                .thenThrow(new RuntimeException("Not found winning userNumbers for drawing date"));
        numberGeneratorFacade =
                numberGeneratorConfiguration.createdForTest(
                        null,
                        numberGeneratorRepository,
                        null);
        // when
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> numberGeneratorFacade.retrieveWonNumbersForDate(drawDateDto));
        // then
        assertEquals("Not found winning userNumbers for drawing date", exception.getMessage());
    }

    @Test
    @DisplayName("Should return six different winning userNumbers in range from 1 to 99 when generation went correct")
    void should_return_six_different_winning_numbers_in_range_from_1_to_99_when_generation_went_correct() {
        // given
        LocalDateTime drawDate = LocalDateTime.of(2023, 1, 7, 12, 0);
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        NumbersGenerator numbersGenerator = mock(NumbersGenerator.class);
        when(numbersGenerator.generateRandomNumbers()).thenReturn(numbers);

        NumberReceiverFacade numberReceiverFacade = mock(NumberReceiverFacade.class);
        when(numberReceiverFacade.retrieveNextDrawDate()).thenReturn(new DrawDateDto(drawDate));

        NumberGeneratorFacade numberGeneratorFacade = new NumberGeneratorConfiguration()
                .createdForTest(
                        numberReceiverFacade,
                        numberGeneratorRepository,
                        numbersGenerator);

        // when
        WinningNumbersDto result = numberGeneratorFacade.generateWonNumbersForNextDrawDate();
        // then
        assertThat(result.numbers(), equalTo(numbers));
        assertThat(result.drawDate(), equalTo(drawDate));
    }

}
