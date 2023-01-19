package pl.lotto.numberreceiver;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.lotto.numberreceiver.dto.DrawDateDto;
import pl.lotto.numberreceiver.dto.TicketDto;
import pl.lotto.numberreceiver.dto.TicketListDto;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberReceiverFacadeTest {

    private final LocalDateTime date = LocalDateTime.of(2023, 1, 5, 11, 5);

    @Test
    @DisplayName("Should return valid ticket when user gave six correct numbers")
    public void should_return_valid_ticket_when_user_gave_six_correct_numbers() {
        // given
        NumberReceiverFacade numberReceiver = new NumberReceiverFacade(date);
        // when
        TicketDto result = numberReceiver.inputNumbers(List.of(1, 2, 3, 4, 5, 6));
        // then
        assertThat(result.isValid()).isTrue();
    }

    @Test
    @DisplayName("Should return valid ticket with no error message when user gave six correct numbers")
    public void should_return_valid_ticket_with_no_error_message_when_user_gave_six_correct_numbers() {
        // given
        NumberReceiverFacade numberReceiver = new NumberReceiverFacade(date);
        // when
        TicketDto result = numberReceiver.inputNumbers(List.of(1, 2, 3, 4, 5, 6));
        // then
        assertThat(result.isValid()).isTrue();
        assertThat(result.message()).isNull();
    }

    @Test
    @DisplayName("Should return invalid ticket when user gave out of range number")
    public void should_return_invalid_ticket_when_user_gave_out_of_range_number() {
        // given
        NumberReceiverFacade numberReceiver = new NumberReceiverFacade(date);
        // when
        TicketDto result = numberReceiver.inputNumbers(List.of(1, 2, 344, 4, 5, 6));
        // then
        assertThat(result.isValid()).isFalse();
    }

    @Test
    @DisplayName("Should return invalid ticket with 'out of range number' error message when user gave out of range number")
    public void should_return_invalid_ticket_with_error_message_when_user_gave_out_of_range_number() {
        // given
        NumberReceiverFacade numberReceiver = new NumberReceiverFacade(date);
        // when
        TicketDto result = numberReceiver.inputNumbers(List.of(1, 2, 344, 4, 5, 6));
        // then
        assertThat(result.isValid()).isFalse();
        assertThat(result.message()).isEqualTo("out of range number");
    }

    @Test
    @DisplayName("Should return invalid ticket when user gave duplicate number")
    public void should_return_invalid_ticket_when_user_gave_duplicate_number() {
        // given
        NumberReceiverFacade numberReceiver = new NumberReceiverFacade(date);
        // when
        TicketDto result = numberReceiver.inputNumbers(List.of(1, 2, 2, 4, 5, 6));
        // then
        assertThat(result.isValid()).isFalse();
    }

    @Test
    @DisplayName("Should return invalid ticket with 'repeated number' error message when user gave duplicate number")
    public void should_return_invalid_ticket_with_error_message_when_user_gave_duplicate_number() {
        // given
        NumberReceiverFacade numberReceiver = new NumberReceiverFacade(date);
        // when
        TicketDto result = numberReceiver.inputNumbers(List.of(1, 2, 2, 4, 5, 6));
        // then
        assertThat(result.isValid()).isFalse();
        assertThat(result.message()).isEqualTo("repeated number");
    }

    @Test
    @DisplayName("Should return invalid ticket when user gave less than six numbers")
    public void should_return_invalid_ticket_when_user_gave_less_than_six_numbers() {
        // given
        NumberReceiverFacade numberReceiver = new NumberReceiverFacade(date);
        // when
        TicketDto result = numberReceiver.inputNumbers(List.of(1, 2, 2, 4, 5));
        // then
        assertThat(result.isValid()).isFalse();
    }

    @Test
    @DisplayName("Should return invalid ticket when user gave more than six numbers")
    public void should_return_invalid_ticket_when_user_gave_more_than_six_numbers() {
        // given
        NumberReceiverFacade numberReceiver = new NumberReceiverFacade(date);
        // when
        TicketDto result = numberReceiver.inputNumbers(List.of(1, 2, 3, 4, 5, 6, 7));
        // then
        assertThat(result.isValid()).isFalse();
    }

    @Test
    @DisplayName("Should return correct draw date for valid ticket")
    public void should_return_correct_draw_date_for_valid_ticket() {
        // given
        LocalDateTime ticketDate = LocalDateTime.of(2023,1,10,11,15);
        NumberReceiverFacade numberReceiver = new NumberReceiverFacade(ticketDate);
        LocalDateTime drawDate = LocalDateTime.of(2023,1,14,12,0);
        // when
        TicketDto ticket = numberReceiver.inputNumbers(List.of(1, 2, 3, 4, 5, 6));
        DrawDateDto result = new DrawDateDto(drawDate);
        // then
        assertThat(ticket.drawDate()).isEqualTo(result);
    }

    @Test
    @DisplayName("Should return correct draw date for 2023/1/18")
    public void should_return_correct_draw_date() {
        // given
        LocalDateTime ticketDate = LocalDateTime.of(2023, 1, 18, 11, 15);
        LocalDateTime drawDate = LocalDateTime.of(2023, 1, 21, 12, 0);
        NumberReceiverFacade numberReceiver = new NumberReceiverFacade(ticketDate);
        // when
        DrawDateDto result = numberReceiver.retrieveDrawDate();
        // then
        assertThat(result.drawDate()).isEqualTo(drawDate);
    }

    @Test
    @DisplayName("Should return correct draw date for ticket from 2023/1/21 before 12:00")
    public void should_return_correct_draw_date_for_ticket_from_2023_1_21_before_12_00() {
        // given
        LocalDateTime ticketDate = LocalDateTime.of(2023,1,21,10,13);
        LocalDateTime drawDate = LocalDateTime.of(2023, 1, 21, 12, 0);
        NumberReceiverFacade numberReceiver = new NumberReceiverFacade(ticketDate);
        // when
        DrawDateDto result = numberReceiver.retrieveDrawDate();
        // then
        assertThat(result.drawDate()).isEqualTo(drawDate);
    }

    @Test
    @DisplayName("Should return correct draw date for ticket from 2023/1/21 after 11:59")
    public void should_return_correct_draw_date_for_ticket_from_2023_1_21_after_12_00() {
        // given
        LocalDateTime ticketDate = LocalDateTime.of(2023,1,21,13,0);
        LocalDateTime drawDate = LocalDateTime.of(2023, 1, 28, 12, 0);
        NumberReceiverFacade numberReceiver = new NumberReceiverFacade(ticketDate);
        // when
        DrawDateDto result = numberReceiver.retrieveDrawDate();
        // then
        assertThat(result.drawDate()).isEqualTo(drawDate);
    }

    @Test
    @DisplayName("Should return correct draw date for ticket from 2023/1/21 12:00")
    public void should_return_correct_draw_date_for_ticket_from_2023_1_21_12_00() {
        // given
        LocalDateTime ticketDate = LocalDateTime.of(2023,1,21,12,0);
        LocalDateTime drawDate = LocalDateTime.of(2023, 1, 28, 12, 0);
        NumberReceiverFacade numberReceiver = new NumberReceiverFacade(ticketDate);
        // when
        DrawDateDto result = numberReceiver.retrieveDrawDate();
        // then
        assertThat(result.drawDate()).isEqualTo(drawDate);
    }

    @Test
    @DisplayName("Should return all tickets")
    public void should_return_all_tickets() {
        // given
        NumberReceiverRepositoryImpl numberReceiverRepository = new NumberReceiverRepositoryImpl();
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(date);
        TicketDto ticket1 = numberReceiverFacade.inputNumbers(List.of(1, 2, 3, 4, 5, 6));
        TicketDto ticket2 = numberReceiverFacade.inputNumbers(List.of(11, 12, 13, 14, 15, 16));
        numberReceiverRepository.save(mapTicket(ticket1));
        numberReceiverRepository.save(mapTicket(ticket2));
        // when
        List<TicketEntity> result = numberReceiverRepository.findAll();
        // then
        assertThat(result).isNotEmpty();
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Should return all tickets for given date")
    public void should_return_all_tickets_for_given_date() {
        // given
        LocalDateTime ticketDate1 = LocalDateTime.of(2023, 1, 3, 10, 5);
        LocalDateTime ticketDate2 = LocalDateTime.of(2023, 1, 5, 11, 15);
        LocalDateTime ticketDate3 = LocalDateTime.of(2023, 1, 10, 12, 33);
        DrawDateDto drawDate = new DrawDateDto(LocalDateTime.of(2023, 1, 7, 12, 0));
        NumberReceiverFacade numberReceiverFacade1 = new NumberReceiverFacade(ticketDate1);
        NumberReceiverFacade numberReceiverFacade2 = new NumberReceiverFacade(ticketDate2);
        NumberReceiverFacade numberReceiverFacade3 = new NumberReceiverFacade(ticketDate3);
        TicketDto ticket1 = numberReceiverFacade1.inputNumbers(List.of(1, 2, 3, 4, 5, 6));
        TicketDto ticket2 = numberReceiverFacade2.inputNumbers(List.of(11, 12, 13, 14, 15, 16));
        TicketDto ticket3 = numberReceiverFacade3.inputNumbers(List.of(21, 22, 23, 24, 25, 26));
        NumberReceiverRepositoryImpl numberReceiverRepository = new NumberReceiverRepositoryImpl();
        numberReceiverRepository.save(mapTicket(ticket1));
        numberReceiverRepository.save(mapTicket(ticket2));
        numberReceiverRepository.save(mapTicket(ticket3));
        // when
        List<TicketEntity> result = numberReceiverRepository.findByDrawDate(drawDate);
        // then
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Should return all tickets for given date")
    public void should_return_tickets_for_given_date(){
        // given
        LocalDateTime ticketDate1 = LocalDateTime.of(2023, 1, 3, 10, 5);
        LocalDateTime ticketDate2 = LocalDateTime.of(2023, 1, 5, 11, 15);
        LocalDateTime ticketDate3 = LocalDateTime.of(2023, 1, 10, 12, 33);
        DrawDateDto drawDate = new DrawDateDto(LocalDateTime.of(2023, 1, 7, 12, 0));
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(ticketDate1);
        TicketDto ticket1 = numberReceiverFacade.inputNumbers(List.of(1, 2, 3, 4, 5, 6));
        TicketDto ticket2 = numberReceiverFacade.inputNumbers(List.of(11, 12, 13, 14, 15, 16));
        TicketDto ticket3 = numberReceiverFacade.inputNumbers(List.of(21, 22, 23, 24, 25, 26));
        // when
        TicketListDto tickets = numberReceiverFacade.retrieveNumbersForDate(drawDate);
        // then
        assertThat(tickets.ticketList().size()).isEqualTo(3);
    }

    private TicketEntity mapTicket(TicketDto ticket) {
        return new TicketEntity(ticket.lotteryId(), ticket.numbers(), ticket.drawDate());
    }

}
