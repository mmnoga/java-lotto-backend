//package pl.lotto.resultchecker;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import pl.lotto.numberreceiver.NumberReceiverFacade;
//import pl.lotto.numberreceiver.dto.TicketDto;
//import pl.lotto.resultchecker.dto.PlayerResultDto;
//import pl.lotto.winningnumbergenerator.NumberGeneratorFacade;
//import pl.lotto.winningnumbergenerator.dto.WinningNumbersDto;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.*;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//class ResultCheckerFacadeTest {
//
//    private final PlayerResultRepository playerResultRepository = new InMemoryResultCheckerRepositoryImpl();
//    private final ResultCheckerConfiguration resultCheckerConfiguration = new ResultCheckerConfiguration();
//    NumberReceiverFacade numberReceiverFacade;
//    NumberGeneratorFacade numberGeneratorFacade;
//
//    @Test
//    @DisplayName("Should return all tickets for draw date")
//    void should_return_all_tickets_for_draw_date() {
//        // given
//        LocalDateTime drawDate = LocalDateTime.of(2023, 2, 4, 12, 0);
//        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
//        numberReceiverFacade = mock(NumberReceiverFacade.class);
//        numberGeneratorFacade = mock(NumberGeneratorFacade.class);
//        when(numberReceiverFacade.retrieveNumbersForNextDrawDate()).thenReturn(List.of(
//                new TicketDto("id1", List.of(1, 2, 3, 4, 5, 6), drawDate, true, null),
//                new TicketDto("id2", List.of(1, 2, 23, 24, 25, 26), drawDate, true, null),
//                new TicketDto("id3", List.of(1, 2, 3, 24, 25, 26), drawDate, true, null),
//                new TicketDto("id4", List.of(21, 22, 23, 24, 25, 26), drawDate, true, null)
//        ));
//        when(numberGeneratorFacade.generateWonNumbersForNextDrawDate()).thenReturn(
//                new WinningNumbersDto(drawDate, winningNumbers));
//        ResultCheckerFacade resultCheckerFacade = resultCheckerConfiguration.createdForTest(
//                numberReceiverFacade,
//                numberGeneratorFacade,
//                playerResultRepository);
//        // when
//        List<PlayerResultDto> playersResult = resultCheckerFacade.generateResults();
//        // then
//        assertThat(playersResult, hasSize(4));
//    }
//
//    @Test
//    @DisplayName("Should return winning ticket list for draw date")
//    void should_return_winning_ticket_list_for_draw_date() {
//        // given
//        LocalDateTime drawDate = LocalDateTime.of(2023, 2, 4, 12, 0);
//        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
//        numberReceiverFacade = mock(NumberReceiverFacade.class);
//        numberGeneratorFacade = mock(NumberGeneratorFacade.class);
//        playerResultRepository.saveAll(List.of(
//                new PlayerResult("id1", drawDate, List.of(1, 2, 3, 4, 5, 6), winningNumbers, 6),
//                new PlayerResult("id2", drawDate, List.of(1, 2, 23, 24, 25, 26), winningNumbers, 2),
//                new PlayerResult("id3", drawDate, List.of(1, 2, 3, 44, 45, 46), winningNumbers, 3),
//                new PlayerResult("id4", drawDate, List.of(21, 22, 23, 24, 25, 26), winningNumbers, 0)
//        ));
//        ResultCheckerFacade resultCheckerFacade = resultCheckerConfiguration.createdForTest(
//                numberReceiverFacade,
//                numberGeneratorFacade,
//                playerResultRepository);
//        // when
//        List<PlayerResultDto> playersResult = resultCheckerFacade.retrieveWonTickets();
//        // then
//        assertThat(playersResult, hasSize(2));
//    }
//
//    @Test
//    @DisplayName("Should return empty list when no winning tickets for draw date")
//    void should_return_empty_list_when_no_winning_tickets_for_draw_date() {
//        // given
//        LocalDateTime drawDate = LocalDateTime.of(2023, 2, 4, 12, 0);
//        numberReceiverFacade = mock(NumberReceiverFacade.class);
//        numberGeneratorFacade = mock(NumberGeneratorFacade.class);
//        when(numberReceiverFacade.retrieveNumbersForNextDrawDate()).thenReturn(
//                List.of(
//                        new TicketDto("id1", List.of(50, 51, 52, 53, 54, 55),
//                                drawDate, true, null),
//                        new TicketDto("id2", List.of(11, 12, 23, 24, 25, 26),
//                                drawDate, true, null),
//                        new TicketDto("id3", List.of(61, 62, 63, 64, 65, 66),
//                                drawDate, true, null),
//                        new TicketDto("id4", List.of(41, 42, 43, 44, 45, 46),
//                                drawDate, true, null)
//                )
//        );
//        when(numberGeneratorFacade.generateWonNumbersForNextDrawDate()).thenReturn(
//                new WinningNumbersDto(
//                        drawDate,
//                        List.of(1, 2, 3, 4, 5, 6)));
//        ResultCheckerFacade resultCheckerFacade = resultCheckerConfiguration.createdForTest(
//                numberReceiverFacade,
//                numberGeneratorFacade,
//                playerResultRepository);
//        // when
//        List<PlayerResultDto> playersResult = resultCheckerFacade.retrieveWonTickets();
//        // then
//        assertThat(playersResult, hasSize(0));
//    }
//
//    @Test
//    @DisplayName("Should return player result for winning ticket for ticket id")
//    void should_return_player_result_for_winning_ticket_for_draw_date() {
//        // given
//        LocalDateTime drawDate = LocalDateTime.of(2023, 2, 4, 12, 0);
//        String winningTicketId = "id1_ticket1";
//        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
//        numberReceiverFacade = mock(NumberReceiverFacade.class);
//        numberGeneratorFacade = mock(NumberGeneratorFacade.class);
//        playerResultRepository.saveAll(List.of(
//                new PlayerResult("id1", drawDate, List.of(1, 2, 3, 4, 5, 6), winningNumbers, 6),
//                new PlayerResult("id2", drawDate, List.of(1, 2, 23, 24, 25, 26), winningNumbers, 2),
//                new PlayerResult(winningTicketId, drawDate, List.of(1, 2, 3, 44, 45, 46), winningNumbers, 3),
//                new PlayerResult("id4", drawDate, List.of(21, 22, 23, 24, 25, 26), winningNumbers, 0)
//        ));
//        ResultCheckerFacade resultCheckerFacade = resultCheckerConfiguration.createdForTest(
//                numberReceiverFacade,
//                numberGeneratorFacade,
//                playerResultRepository);
//        // when
//        Optional<PlayerResultDto> result = resultCheckerFacade.checkWinner(winningTicketId);
//        // then
//        assertThat(result.get().ticketId(), is(winningTicketId));
//        assertThat(result.get().hitNumber(), is(3));
//        assertThat(result.get().winningNumbers(), is(winningNumbers));
//    }
//
//    @Test
//    @DisplayName("Should throw exception when no ticket was found for given ticket id")
//    void should_throw_exception_when_no_ticket_was_found_for_given_ticket_id() {
//        // given
//        LocalDateTime drawDate = LocalDateTime.of(2023, 2, 4, 12, 0);
//        String winningTicketId = "id1_ticket1";
//        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
//        numberReceiverFacade = mock(NumberReceiverFacade.class);
//        numberGeneratorFacade = mock(NumberGeneratorFacade.class);
//        playerResultRepository.saveAll(List.of(
//                new PlayerResult("id1", drawDate, List.of(1, 2, 3, 4, 5, 6), winningNumbers, 6),
//                new PlayerResult("id2", drawDate, List.of(1, 2, 23, 24, 25, 26), winningNumbers, 2),
//                new PlayerResult("id3", drawDate, List.of(1, 2, 3, 44, 45, 46), winningNumbers, 3),
//                new PlayerResult("id4", drawDate, List.of(21, 22, 23, 24, 25, 26), winningNumbers, 0)
//        ));
//        ResultCheckerFacade resultCheckerFacade = resultCheckerConfiguration.createdForTest(
//                numberReceiverFacade,
//                numberGeneratorFacade,
//                playerResultRepository);
//        // when
//        RuntimeException exception = assertThrows(
//                RuntimeException.class,
//                () -> resultCheckerFacade.checkWinner(winningTicketId));
//        // then
//        assertEquals("Ticket not found", exception.getMessage());
//    }
//
//    @Test
//    @DisplayName("Should return player result empty object for lose ticket for ticket id")
//    void should_return_player_result_empty_object_for_winning_ticket_for_draw_date() {
//        // given
//        LocalDateTime drawDate = LocalDateTime.of(2023, 2, 4, 12, 0);
//        String winningTicketId = "id1_ticket1";
//        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
//        numberReceiverFacade = mock(NumberReceiverFacade.class);
//        numberGeneratorFacade = mock(NumberGeneratorFacade.class);
//        playerResultRepository.saveAll(List.of(
//                new PlayerResult("id1", drawDate, List.of(1, 2, 3, 4, 5, 6), winningNumbers, 6),
//                new PlayerResult("id2", drawDate, List.of(1, 2, 23, 24, 25, 26), winningNumbers, 2),
//                new PlayerResult(winningTicketId, drawDate, List.of(1, 2, 33, 44, 45, 46), winningNumbers, 2),
//                new PlayerResult("id4", drawDate, List.of(21, 22, 23, 24, 25, 26), winningNumbers, 0)
//        ));
//        ResultCheckerFacade resultCheckerFacade = resultCheckerConfiguration.createdForTest(
//                numberReceiverFacade,
//                numberGeneratorFacade,
//                playerResultRepository);
//        // when
//        Optional<PlayerResultDto> result = resultCheckerFacade.checkWinner(winningTicketId);
//        // then
//        assertFalse(result.isPresent());
//    }
//
//}
