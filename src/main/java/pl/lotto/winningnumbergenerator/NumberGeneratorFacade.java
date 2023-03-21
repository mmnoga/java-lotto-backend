package pl.lotto.winningnumbergenerator;

import java.util.List;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.DrawDateDto;
import pl.lotto.winningnumbergenerator.dto.WinningNumbersDto;
import static pl.lotto.winningnumbergenerator.WinningNumbersMapper.mapFromWinningNumbersToWinningNumbersDto;

public class NumberGeneratorFacade {

    private final NumberGeneratorRepository numbersGeneratorRepository;
    private final NumberReceiverFacade numberReceiverFacade;
    private final NumbersGeneratorInterface numbersGenerator;

    public NumberGeneratorFacade(NumberGeneratorRepository numbersGeneratorRepository,
                                 NumberReceiverFacade numberReceiverFacade,
                                 NumbersGeneratorInterface numbersGenerator) {
        this.numbersGeneratorRepository = numbersGeneratorRepository;
        this.numberReceiverFacade = numberReceiverFacade;
        this.numbersGenerator = numbersGenerator;
    }

    public WinningNumbersDto retrieveWonNumbersForDate(DrawDateDto date) {
        WinningNumbers winningNumbers = numbersGeneratorRepository
                .findWinningNumbersByDrawDate(date.drawDate())
                .orElseThrow(() -> new NotFoundWinningNumbersException("Not found winning numbers for drawing date"));
        return mapFromWinningNumbersToWinningNumbersDto(winningNumbers);
    }

    public WinningNumbersDto generateWonNumbersForNextDrawDate() {
        DrawDateDto drawDateDto = numberReceiverFacade.retrieveNextDrawDate();
        try {
            WinningNumbersDto winningNumbersDto = retrieveWonNumbersForDate(drawDateDto);
            return mapFromWinningNumbersToWinningNumbersDto(new WinningNumbers(winningNumbersDto.drawDate(), winningNumbersDto.numbers()));
        } catch (NotFoundWinningNumbersException runtimeException) {
            List<Integer> numbers = numbersGenerator.generateRandomNumbers();
            WinningNumbers save = numbersGeneratorRepository
                    .save(new WinningNumbers(drawDateDto.drawDate(), numbers));
            return mapFromWinningNumbersToWinningNumbersDto(save);
        }
    }
}
