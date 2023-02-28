package pl.lotto.winningnumbergenerator;

import pl.lotto.winningnumbergenerator.dto.WinningNumbersDto;

class WinningNumbersMapper {

    public static WinningNumbersDto mapFromWinningNumbersToWinningNumbersDto(WinningNumbers winningNumbers) {
        return WinningNumbersDto.builder()
                .drawDate(winningNumbers.drawDate())
                .numbers(winningNumbers.number())
                .build();
    }

}
