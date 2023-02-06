package pl.lotto.resultchecker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.TicketDto;
import pl.lotto.resultchecker.dto.PlayerResultDto;
import pl.lotto.winningnumbergenerator.NumberGeneratorFacade;
import pl.lotto.winningnumbergenerator.dto.WinningNumbersDto;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ResultCheckerFacadeTest {

    ResultCheckerFacade resultCheckerFacade;
    NumberReceiverFacade numberReceiverFacade;
    NumberGeneratorFacade numberGeneratorFacade;

    @Test
    @DisplayName("Should return winning tickets from all tickets for draw date")
    void should_return_winning_tickets_from_all_tickets_for_draw_date() {
        // given
        LocalDateTime drawDate = LocalDateTime.of(2023, 2, 4, 12, 0);
        numberReceiverFacade = mock(NumberReceiverFacade.class);
        numberGeneratorFacade = mock(NumberGeneratorFacade.class);
        when(numberReceiverFacade.retrieveNumbersForNextDrawDate()).thenReturn(
                List.of(
                        new TicketDto(null, List.of(1, 2, 3, 4, 5, 6),
                                drawDate, true, null),
                        new TicketDto(null, List.of(1, 2, 23, 24, 25, 26),
                                drawDate, true, null),
                        new TicketDto(null, List.of(1, 2, 3, 34, 35, 36),
                                drawDate, true, null),
                        new TicketDto(null, List.of(41, 42, 43, 44, 45, 46),
                                drawDate, true, null)
                )
        );
        when(numberGeneratorFacade.generateWonNumbersForNextDrawDate()).thenReturn(
                new WinningNumbersDto(
                        drawDate,
                        List.of(1, 2, 3, 4, 5, 6)));
        resultCheckerFacade = new ResultCheckerFacade(numberReceiverFacade, numberGeneratorFacade);
        // when
        List<PlayerResultDto> playersResult = resultCheckerFacade.generateResults();
        // then
        assertThat(playersResult, hasSize(2));
    }

    @Test
    @DisplayName("Should return empty list when no winning tickets for draw date")
    void should_return_empty_list_when_no_winning_tickets_for_draw_date() {
        // given
        LocalDateTime drawDate = LocalDateTime.of(2023, 2, 4, 12, 0);
        numberReceiverFacade = mock(NumberReceiverFacade.class);
        numberGeneratorFacade = mock(NumberGeneratorFacade.class);
        when(numberReceiverFacade.retrieveNumbersForNextDrawDate()).thenReturn(
                List.of(
                        new TicketDto(null, List.of(50, 51, 52, 53, 54, 55),
                                drawDate, true, null),
                        new TicketDto(null, List.of(11, 12, 23, 24, 25, 26),
                                drawDate, true, null),
                        new TicketDto(null, List.of(61, 62, 63, 64, 65, 66),
                                drawDate, true, null),
                        new TicketDto(null, List.of(41, 42, 43, 44, 45, 46),
                                drawDate, true, null)
                )
        );
        when(numberGeneratorFacade.generateWonNumbersForNextDrawDate()).thenReturn(
                new WinningNumbersDto(
                        drawDate,
                        List.of(1, 2, 3, 4, 5, 6)));
        resultCheckerFacade = new ResultCheckerFacade(numberReceiverFacade, numberGeneratorFacade);
        // when
        List<PlayerResultDto> playersResult = resultCheckerFacade.generateResults();
        // then
        assertThat(playersResult, hasSize(0));
    }

}