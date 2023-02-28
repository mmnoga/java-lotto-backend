package pl.lotto.infrastructure.controller.numberreceiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.TicketDto;

import java.util.List;

@RestController
@RequestMapping(path = "inputNumbers")
public class NumberReceiverController {

    private final NumberReceiverFacade numberReceiverFacade;

    @Autowired
    public NumberReceiverController(NumberReceiverFacade numberReceiverFacade) {
        this.numberReceiverFacade = numberReceiverFacade;
    }

    @PostMapping
    public TicketDto inputNumbers(@RequestParam List<Integer> numbers) {
        return numberReceiverFacade.inputNumbers(numbers);
    }

}
