package pl.lotto.winningnumbergenerator;

import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.DrawDateDto;
import pl.lotto.winningnumbergenerator.dto.WinningNumbersDto;

import java.util.List;

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
                .orElseThrow(() ->
                        new RuntimeException("Not found winning numbers for drawing date"));
        return WinningNumbersMapper
                .mapFromWinningNumbersToWinningNumbersDto(
                        winningNumbers);
    }

    //add scheduler...
    public WinningNumbersDto generateWonNumbersForNextDrawDate() {
        DrawDateDto drawDateDto = numberReceiverFacade.retrieveNextDrawDate();
        List<Integer> numbers = numbersGenerator.generateRandomNumbers();
        WinningNumbers save = numbersGeneratorRepository
                .save(new WinningNumbers(drawDateDto.drawDate(), numbers));
        return WinningNumbersMapper
                .mapFromWinningNumbersToWinningNumbersDto(save);
    }

}
