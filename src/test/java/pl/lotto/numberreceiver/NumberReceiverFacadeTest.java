package pl.lotto.numberreceiver;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class NumberReceiverFacadeTest {

    @Test
    @DisplayName("Should return valid ticket when user gave six correct numbers")
    public void should_return_valid_ticket_when_user_gave_six_correct_numbers(){
        // given
        NumberReceiverFacade numberReceiver = new NumberReceiverFacade();
        // when
        Ticket result = numberReceiver.inputNumbers(List.of(1,2,3,4,5,6));
        // then
        assertThat(result.isValid()).isTrue();
    }

    @Test
    @DisplayName("Should return invalid ticket when user gave out of range number")
    public void should_return_invalid_ticket_when_user_gave_out_of_range_number(){
        // given
        NumberReceiverFacade numberReceiver = new NumberReceiverFacade();
        // when
        Ticket result = numberReceiver.inputNumbers(List.of(1,2,344,4,5,6));
        // then
        assertThat(result.isValid()).isFalse();
    }

    @Test
    @DisplayName("Should return invalid ticket when user gave duplicate number")
    public void should_return_invalid_ticket_when_user_gave_duplicate_number(){
        // given
        NumberReceiverFacade numberReceiver = new NumberReceiverFacade();
        // when
        Ticket result = numberReceiver.inputNumbers(List.of(1,2,2,4,5,6));
        // then
        assertThat(result.isValid()).isFalse();
    }

    @Test
    @DisplayName("Should return invalid ticket when user gave less than six numbers")
    public void should_return_invalid_ticket_when_user_gave_less_than_six_numbers(){
        // given
        NumberReceiverFacade numberReceiver = new NumberReceiverFacade();
        // when
        Ticket result = numberReceiver.inputNumbers(List.of(1,2,2,4,5));
        // then
        assertThat(result.isValid()).isFalse();
    }

    @Test
    @DisplayName("Should return invalid ticket when user gave more than six numbers")
    public void should_return_invalid_ticket_when_user_gave_more_than_six_numbers(){
        // given
        NumberReceiverFacade numberReceiver = new NumberReceiverFacade();
        // when
        Ticket result = numberReceiver.inputNumbers(List.of(1,2,2,4,5,6,7));
        // then
        assertThat(result.isValid()).isFalse();
    }

//    @Test
//    public void should_return_success_when_user_gave_six_correct_numbers() {
//        // given
//        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade();
//        // when
//        String result = numberReceiverFacade.inputNumbers(List.of(1, 2, 3, 4, 5, 6));
//        // then
//        assertThat(result).isEqualTo("success");
//    }
}
