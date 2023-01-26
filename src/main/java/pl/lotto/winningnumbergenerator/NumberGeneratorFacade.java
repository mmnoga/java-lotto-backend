package pl.lotto.winningnumbergenerator;

import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.DrawDateDto;
import pl.lotto.winningnumbergenerator.dto.WinningNumbersDto;

import java.util.List;

public class NumberGeneratorFacade {

    private NumberGeneratorRepository numbersGeneratorRepository;
    private NumberReceiverFacade numberReceiverFacade;
    private NumbersGenerator numbersGenerator;

    public NumberGeneratorFacade(NumberGeneratorRepository numbersGeneratorRepository,
                                 NumberReceiverFacade numberReceiverFacade,
                                 NumbersGenerator numbersGenerator) {
        this.numbersGeneratorRepository = numbersGeneratorRepository;
        this.numberReceiverFacade = numberReceiverFacade;
        this.numbersGenerator = numbersGenerator;
    }

    public WinningNumbersDto retrieveWonNumbersForDate(DrawDateDto date) {
        WinningNumbers winningNumbers = numbersGeneratorRepository
                .findWinningNumbersByDrawDate(date.drawDate())
                .orElseThrow(() ->
                        new RuntimeException("Not found winning numbers for drawing date"));
        return new WinningNumbersDto(winningNumbers.drawDate(), winningNumbers.numbers());
    }

    //add scheduler...
    public WinningNumbersDto generateWonNumbersForNextDrawDate() {
        DrawDateDto drawDateDto = numberReceiverFacade.retrieveNextDrawDate();
        List<Integer> numbers = numbersGenerator.generateRandomNumbers();
        WinningNumbers save = numbersGeneratorRepository.save(new WinningNumbers(drawDateDto.drawDate(), numbers));
        return new WinningNumbersDto(save.drawDate(),save.numbers());
    }

}
