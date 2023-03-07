package pl.lotto.resultannouncer;

import pl.lotto.resultannouncer.dto.ResultDto;
import pl.lotto.resultchecker.ResultCheckerFacade;
import pl.lotto.resultchecker.dto.PlayerResultDto;

import java.util.Optional;

public class ResultAnnouncerFacade {

    private final ResultCheckerFacade resultCheckerFacade;

    public ResultAnnouncerFacade(ResultCheckerFacade resultCheckerFacade) {
        this.resultCheckerFacade = resultCheckerFacade;
    }

    public Optional<ResultDto> checkWinner(String uniqueLotteryId) {
        System.out.println("checking in checkWinner at ResultAnnouncerFacade");
        PlayerResultDto ticket = resultCheckerFacade.checkWinner(uniqueLotteryId)
                .orElseThrow(() ->
                        new RuntimeException("Ticket not found"));
        return Optional.ofNullable(ResultAnnouncerMapper.mapPlayerResultDtoToResultDto(ticket));
    }

}
