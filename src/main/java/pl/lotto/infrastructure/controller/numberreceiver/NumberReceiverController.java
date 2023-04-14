package pl.lotto.infrastructure.controller.numberreceiver;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
    @Operation(summary = "Create user ticket")
    public ResponseEntity<TicketDto> inputNumbers(@Parameter(description = "6 different numbers in range from 1 to 99") @RequestParam List<Integer> numbers) {
        TicketDto ticketDto = numberReceiverFacade.inputNumbers(numbers);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketDto);
    }

}
