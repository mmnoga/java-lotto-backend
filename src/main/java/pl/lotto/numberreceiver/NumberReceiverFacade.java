package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.DrawDateDto;
import pl.lotto.numberreceiver.dto.TicketDto;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class NumberReceiverFacade {

    private final Clock clock;
    private final NumberReceiverRepository numberReceiverRepository;
    private final NumberValidator numberValidator;
    private final DrawDateGenerator drawDateGenerator;
    private final TicketIdGenerator ticketIdGenerator;

    NumberReceiverFacade(Clock clock,
                         NumberReceiverRepository numberReceiverRepository,
                         NumberValidator numberValidator,
                         DrawDateGenerator drawDateGenerator,
                         TicketIdGenerator ticketIdGenerator) {
        this.clock = clock;
        this.numberReceiverRepository = numberReceiverRepository;
        this.numberValidator = numberValidator;
        this.drawDateGenerator = drawDateGenerator;
        this.ticketIdGenerator = ticketIdGenerator;
    }

    public TicketDto inputNumbers(List<Integer> userNumbers) {
        ValidationResult validationResult = numberValidator.validate(userNumbers);
        if (validationResult.isNotValid()) {
            return new TicketDto(null,
                    userNumbers,
                    null,
                    false,
                    validationResult.message());
        }
        TicketEntity ticketEntity = TicketEntity.builder()
                .numbers(userNumbers)
                .drawDate(drawDateGenerator.generateOrGetDrawDate(LocalDateTime.now(clock)))
                .lotteryId(ticketIdGenerator.getId())
                .build();
        TicketEntity ticket = numberReceiverRepository.save(ticketEntity);

        return TicketMapper.mapFromTicketEntityToTicketDto(ticket);
    }

    public DrawDateDto retrieveNextDrawDate() {
        DrawDateGenerator drawDateGenerator = new DrawDateGenerator();
        LocalDateTime dateTime = drawDateGenerator.generateOrGetDrawDate(LocalDateTime.now(clock));
        return TicketMapper.mapFromLocalDateTimeToDrawDateDto(dateTime);
    }

    public List<TicketDto> retrieveNumbersForDate(DrawDateDto date) {
        List<TicketEntity> byDrawDate = numberReceiverRepository.findByDrawDate(date.drawDate());
        return byDrawDate.stream()
                .map(TicketMapper::mapFromTicketEntityToTicketDto)
                .collect(Collectors.toList());
    }

    public List<TicketDto> retrieveNumbersForNextDrawDate() {
        return retrieveNumbersForDate(retrieveNextDrawDate());
    }

}
