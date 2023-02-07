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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
                        new TicketDto("id1", List.of(1, 2, 3, 4, 5, 6),
                                drawDate, true, null),
                        new TicketDto("id2", List.of(1, 2, 23, 24, 25, 26),
                                drawDate, true, null),
                        new TicketDto("id3", List.of(1, 2, 3, 34, 35, 36),
                                drawDate, true, null),
                        new TicketDto("id4", List.of(41, 42, 43, 44, 45, 46),
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
                        new TicketDto("id1", List.of(50, 51, 52, 53, 54, 55),
                                drawDate, true, null),
                        new TicketDto("id2", List.of(11, 12, 23, 24, 25, 26),
                                drawDate, true, null),
                        new TicketDto("id3", List.of(61, 62, 63, 64, 65, 66),
                                drawDate, true, null),
                        new TicketDto("id4", List.of(41, 42, 43, 44, 45, 46),
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

    @Test
    @DisplayName("Should return true for winning ticket for ticket id")
    void should_return_true_for_winning_ticket_for_draw_date() {
        // given
        LocalDateTime drawDate = LocalDateTime.of(2023, 2, 4, 12, 0);
        String winningTicketId = "id1_ticket1";
        numberReceiverFacade = mock(NumberReceiverFacade.class);
        numberGeneratorFacade = mock(NumberGeneratorFacade.class);
        when(numberReceiverFacade.retrieveNumbersForNextDrawDate()).thenReturn(
                List.of(
                        new TicketDto(winningTicketId, List.of(1, 2, 3, 10, 11, 12),
                                drawDate, true, null),
                        new TicketDto("id2_ticket2", List.of(11, 12, 23, 24, 25, 26),
                                drawDate, true, null)
                )
        );
        when(numberGeneratorFacade.generateWonNumbersForNextDrawDate()).thenReturn(
                new WinningNumbersDto(
                        drawDate,
                        List.of(1, 2, 3, 4, 5, 6)));
        resultCheckerFacade = new ResultCheckerFacade(numberReceiverFacade, numberGeneratorFacade);
        // when
        boolean playerResult = resultCheckerFacade.checkWinner(winningTicketId);
        // then
        assertTrue(playerResult);
    }

    @Test
    @DisplayName("Should return false for lost ticket for ticket id")
    void should_return_false_for_lost_ticket_for_draw_date() {
        LocalDateTime drawDate = LocalDateTime.of(2023, 2, 4, 12, 0);
        String winningTicketId = "id1_ticket1";
        numberReceiverFacade = mock(NumberReceiverFacade.class);
        numberGeneratorFacade = mock(NumberGeneratorFacade.class);
        when(numberReceiverFacade.retrieveNumbersForNextDrawDate()).thenReturn(
                List.of(
                        new TicketDto(winningTicketId, List.of(21, 22, 23, 24, 25, 26),
                                drawDate, true, null),
                        new TicketDto("id2_ticket2", List.of(11, 12, 23, 24, 25, 26),
                                drawDate, true, null)
                )
        );
        when(numberGeneratorFacade.generateWonNumbersForNextDrawDate()).thenReturn(
                new WinningNumbersDto(
                        drawDate,
                        List.of(1, 2, 3, 4, 5, 6)));
        resultCheckerFacade = new ResultCheckerFacade(numberReceiverFacade, numberGeneratorFacade);
        // when
        boolean playerResult = resultCheckerFacade.checkWinner(winningTicketId);
        // then
        assertFalse(playerResult);
    }

}