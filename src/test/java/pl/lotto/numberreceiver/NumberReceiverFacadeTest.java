package pl.lotto.numberreceiver;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.lotto.numberreceiver.dto.DrawDateDto;
import pl.lotto.numberreceiver.dto.TicketDto;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberReceiverFacadeTest {

    @Test
    @DisplayName("Should return valid ticket when user gave six correct numbers")
    public void should_return_valid_ticket_when_user_gave_six_correct_numbers(){
        // given
        NumberReceiverFacade numberReceiver = new NumberReceiverFacade();
        // when
        TicketDto result = numberReceiver.inputNumbers(List.of(1,2,3,4,5,6));
        // then
        assertThat(result.isValid()).isTrue();
    }

    @Test
    @DisplayName("Should return valid ticket with no error message when user gave six correct numbers")
    public void should_return_valid_ticket_with_no_error_message_when_user_gave_six_correct_numbers(){
        // given
        NumberReceiverFacade numberReceiver = new NumberReceiverFacade();
        // when
        TicketDto result = numberReceiver.inputNumbers(List.of(1,2,3,4,5,6));
        // then
        assertThat(result.isValid()).isTrue();
        assertThat(result.message()).isNull();
    }

    @Test
    @DisplayName("Should return invalid ticket when user gave out of range number")
    public void should_return_invalid_ticket_when_user_gave_out_of_range_number(){
        // given
        NumberReceiverFacade numberReceiver = new NumberReceiverFacade();
        // when
        TicketDto result = numberReceiver.inputNumbers(List.of(1,2,344,4,5,6));
        // then
        assertThat(result.isValid()).isFalse();
    }

    @Test
    @DisplayName("Should return invalid ticket with 'out of range number' error message when user gave out of range number")
    public void should_return_invalid_ticket_with_error_message_when_user_gave_out_of_range_number(){
        // given
        NumberReceiverFacade numberReceiver = new NumberReceiverFacade();
        // when
        TicketDto result = numberReceiver.inputNumbers(List.of(1,2,344,4,5,6));
        // then
        assertThat(result.isValid()).isFalse();
        assertThat(result.message()).isEqualTo("out of range number");
    }

    @Test
    @DisplayName("Should return invalid ticket when user gave duplicate number")
    public void should_return_invalid_ticket_when_user_gave_duplicate_number(){
        // given
        NumberReceiverFacade numberReceiver = new NumberReceiverFacade();
        // when
        TicketDto result = numberReceiver.inputNumbers(List.of(1,2,2,4,5,6));
        // then
        assertThat(result.isValid()).isFalse();
    }

    @Test
    @DisplayName("Should return invalid ticket with 'repeated number' error message when user gave duplicate number")
    public void should_return_invalid_ticket_with_error_message_when_user_gave_duplicate_number(){
        // given
        NumberReceiverFacade numberReceiver = new NumberReceiverFacade();
        // when
        TicketDto result = numberReceiver.inputNumbers(List.of(1,2,2,4,5,6));
        // then
        assertThat(result.isValid()).isFalse();
        assertThat(result.message()).isEqualTo("repeated number");
    }

    @Test
    @DisplayName("Should return invalid ticket when user gave less than six numbers")
    public void should_return_invalid_ticket_when_user_gave_less_than_six_numbers(){
        // given
        NumberReceiverFacade numberReceiver = new NumberReceiverFacade();
        // when
        TicketDto result = numberReceiver.inputNumbers(List.of(1,2,2,4,5));
        // then
        assertThat(result.isValid()).isFalse();
    }

    @Test
    @DisplayName("Should return invalid ticket when user gave more than six numbers")
    public void should_return_invalid_ticket_when_user_gave_more_than_six_numbers(){
        // given
        NumberReceiverFacade numberReceiver = new NumberReceiverFacade();
        // when
        TicketDto result = numberReceiver.inputNumbers(List.of(1,2,3,4,5,6,7));
        // then
        assertThat(result.isValid()).isFalse();
    }

    @Test
    @DisplayName("Should return correct draw date for valid ticket")
    public void should_return_correct_draw_date_for_valid_ticket(){
        // given
        NumberReceiverFacade numberReceiver = new NumberReceiverFacade();
        // when
        TicketDto ticket = numberReceiver.inputNumbers(List.of(1,2,3,4,5,6));
        DrawDateDto result = numberReceiver.retrieveDrawDate(LocalDateTime.now());
        // then
        assertThat(result).isEqualTo(ticket.drawDate());
    }

    @Test
    @DisplayName("Should return correct draw date for 2023/1/18")
    public void should_return_correct_draw_date(){
        // given
        LocalDateTime ticketDate = LocalDateTime.of(2023,1,18,11,15);
        LocalDateTime drawDate = LocalDateTime.of(2023,1,21,12,0);
        NumberReceiverFacade numberReceiver = new NumberReceiverFacade();
        // when
        DrawDateDto result = numberReceiver.retrieveDrawDate(ticketDate);
        // then
        assertThat(result.drawDate()).isEqualTo(drawDate);
    }

    @Test
    @DisplayName("Should return correct draw date for ticket from 2023/1/21 before 12:00")
    public void should_return_correct_draw_date_for_ticket_from_2023_1_21_before_12_00(){
        // given
        LocalDateTime ticketDate = LocalDateTime.of(2023,1,21,11,15);
        LocalDateTime drawDate = LocalDateTime.of(2023,1,21,12,0);
        NumberReceiverFacade numberReceiver = new NumberReceiverFacade();
        // when
        DrawDateDto result = numberReceiver.retrieveDrawDate(ticketDate);
        System.out.println(result);
        // then
        assertThat(result.drawDate()).isEqualTo(drawDate);
    }

    @Test
    @DisplayName("Should return correct draw date for ticket from 2023/1/21 after 11:59")
    public void should_return_correct_draw_date_for_ticket_from_2023_1_21_after_12_00(){
        // given
        LocalDateTime ticketDate = LocalDateTime.of(2023,1,21,14,45);
        LocalDateTime drawDate = LocalDateTime.of(2023,1,28,12,0);
        NumberReceiverFacade numberReceiver = new NumberReceiverFacade();
        // when
        DrawDateDto result = numberReceiver.retrieveDrawDate(ticketDate);
        System.out.println(result);
        // then
        assertThat(result.drawDate()).isEqualTo(drawDate);
    }

    @Test
    @DisplayName("Should return correct draw date for ticket from 2023/1/21 12:00")
    public void should_return_correct_draw_date_for_ticket_from_2023_1_21_12_00(){
        // given
        LocalDateTime ticketDate = LocalDateTime.of(2023,1,21,12,0);
        LocalDateTime drawDate = LocalDateTime.of(2023,1,28,12,0);
        NumberReceiverFacade numberReceiver = new NumberReceiverFacade();
        // when
        DrawDateDto result = numberReceiver.retrieveDrawDate(ticketDate);
        System.out.println(result);
        // then
        assertThat(result.drawDate()).isEqualTo(drawDate);
    }

}
