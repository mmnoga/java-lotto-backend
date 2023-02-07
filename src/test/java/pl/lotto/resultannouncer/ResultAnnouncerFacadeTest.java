package pl.lotto.resultannouncer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.lotto.resultchecker.ResultCheckerFacade;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ResultAnnouncerFacadeTest {

    ResultCheckerFacade resultCheckerFacade;
    ResultAnnouncerFacade resultAnnouncerFacade;

    @Test
    @DisplayName("Should return true for winning ticket for ticket id")
    void should_return_true_for_win_ticket_for_ticket_id() {
        String winningTicketId = "id1_ticket1";
        resultCheckerFacade = mock(ResultCheckerFacade.class);
        when(resultCheckerFacade.checkWinner(winningTicketId)).thenReturn(true);
        resultAnnouncerFacade = new ResultAnnouncerFacade(resultCheckerFacade);
        // when
        boolean playerResult = resultAnnouncerFacade.checkWinner(winningTicketId);
        // then
        assertTrue(playerResult);
    }

    @Test
    @DisplayName("Should return false for lost ticket for ticket id")
    void should_return_false_for_lost_ticket_for_ticket_id() {
        String winningTicketId = "id1_ticket1";
        resultCheckerFacade = mock(ResultCheckerFacade.class);
        when(resultCheckerFacade.checkWinner(winningTicketId)).thenReturn(false);
        resultAnnouncerFacade = new ResultAnnouncerFacade(resultCheckerFacade);
        // when
        boolean playerResult = resultAnnouncerFacade.checkWinner(winningTicketId);
        // then
        assertFalse(playerResult);
    }

}