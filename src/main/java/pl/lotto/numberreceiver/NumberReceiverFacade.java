package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.DrawDateDto;
import pl.lotto.numberreceiver.dto.TicketDto;
import pl.lotto.numberreceiver.dto.TicketListDto;

import java.time.LocalDateTime;
import java.util.List;

public class NumberReceiverFacade {

    private final LocalDateTime date;
    private final InMemoryNumberGenerator inMemoryNumberGenerator;

    public NumberReceiverFacade(LocalDateTime date) {
        this.date = date;
        this.inMemoryNumberGenerator = new InMemoryNumberGenerator();
    }

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
        TicketDto ticket = new TicketDto(ticketIdGenerator.getId(),
                userNumbers,
                drawDateGenerator.generateOrGetDrawDate(date),
                true,
                null);
        inMemoryNumberGenerator.save(
                inMemoryNumberGenerator.mapTicket(ticket));
        return ticket;
    }

    public DrawDateDto retrieveDrawDate(){
        DrawDateGenerator drawDateGenerator = new DrawDateGenerator();
        return drawDateGenerator.generateOrGetDrawDate(this.date);
    }

    public TicketListDto retrieveNumbersForDate(DrawDateDto date){
        return inMemoryNumberGenerator.mapList(
                inMemoryNumberGenerator.findByDrawDate(date));
    }

}
