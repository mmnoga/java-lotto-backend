package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.DrawDateDto;
import pl.lotto.numberreceiver.dto.TicketDto;

import java.time.LocalDateTime;
import java.util.List;

public class NumberReceiverFacade {

    public TicketDto inputNumbers(List<Integer> userNumbers) {
        NumberValidator numberValidator = new NumberValidator();
        TicketIdGenerator ticketIdGenerator = new TicketIdGenerator();
        DrawDateGenerator drawDateGenerator = new DrawDateGenerator();
        ValidationResult validationResult = numberValidator.validate(userNumbers);
        if (validationResult.isNotValid()) {
            return new TicketDto(null,
                    userNumbers,
                    null,
                    false,
                    validationResult.message());
        }
        return new TicketDto(ticketIdGenerator.getId(),
                userNumbers,
                drawDateGenerator.generateOrGetDrawDate(LocalDateTime.now()),
                true,
                null);
    }

    public DrawDateDto retrieveDrawDate(LocalDateTime ticketDate){
        DrawDateGenerator drawDateGenerator = new DrawDateGenerator();
        return drawDateGenerator.generateOrGetDrawDate(ticketDate);
    }

}
