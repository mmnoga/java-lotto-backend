package pl.lotto.resultannouncer;

import pl.lotto.resultannouncer.dto.ResultDto;
import pl.lotto.resultchecker.dto.PlayerResultDto;

class ResultAnnouncerMapper {

    public static ResultDto mapPlayerResultDtoToResultDto(PlayerResultDto playerResult) {
        return ResultDto.builder()
                .ticketId(playerResult.ticketId())
                .drawDate(playerResult.drawDate())
                .playerNumbers(playerResult.playerNumbers())
                .winningNumbers(playerResult.winningNumbers())
                .hitNumber(playerResult.hitNumber())
                .build();
    }
}
