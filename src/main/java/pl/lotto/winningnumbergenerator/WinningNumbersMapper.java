package pl.lotto.winningnumbergenerator;

import pl.lotto.winningnumbergenerator.dto.WinningNumbersDto;

public class WinningNumbersMapper {

    public static WinningNumbersDto mapFromWinningNumbersToWinningNumbersDto(WinningNumbers winningNumbers) {
        return WinningNumbersDto.builder()
                .drawDate(winningNumbers.drawDate())
                .numbers(winningNumbers.numbers())
                .build();
    }

}
