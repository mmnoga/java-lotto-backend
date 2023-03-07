package pl.lotto.infrastructure.controller.numberreceiver;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.TicketDto;

import java.util.List;

@RestController
@RequestMapping(path = "inputNumbers")
@AllArgsConstructor
public class NumberReceiverController {

    private final NumberReceiverFacade numberReceiverFacade;

    @PostMapping
    public TicketDto inputNumbers(@RequestParam List<Integer> numbers) {
        return numberReceiverFacade.inputNumbers(numbers);
    }

}
