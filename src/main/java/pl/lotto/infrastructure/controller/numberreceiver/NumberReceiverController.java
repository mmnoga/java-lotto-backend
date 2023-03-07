package pl.lotto.infrastructure.controller.numberreceiver;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.TicketDto;

@RestController
@RequestMapping(path = "inputNumbers")
@AllArgsConstructor
public class NumberReceiverController {

    private final NumberReceiverFacade numberReceiverFacade;

    @PostMapping
    public ResponseEntity<TicketDto> inputNumbers(@RequestParam List<Integer> numbers) {
        TicketDto ticketDto = numberReceiverFacade.inputNumbers(numbers);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketDto);
    }

}
