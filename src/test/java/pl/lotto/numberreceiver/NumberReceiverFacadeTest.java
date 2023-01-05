package pl.lotto.numberreceiver;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class NumberReceiverFacadeTest {

    @Test
    public void should_return_success_when_user_gave_six_correct_numbers() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade();
        // when
        String result = numberReceiverFacade.inputNumbers(List.of(1, 2, 3, 4, 5, 6));
        // then
        assertThat(result).isEqualTo("success");
    }
}
