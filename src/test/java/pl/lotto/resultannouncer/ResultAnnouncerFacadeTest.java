package pl.lotto.resultannouncer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.lotto.resultannouncer.dto.ResultDto;
import pl.lotto.resultchecker.ResultCheckerConfiguration;
import pl.lotto.resultchecker.ResultCheckerFacade;
import pl.lotto.resultchecker.dto.PlayerResultDto;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ResultAnnouncerFacadeTest {

    private final ResultAnnouncerConfiguration resultAnnouncerConfiguration = new ResultAnnouncerConfiguration();

    @Test
    void should_return_result_for_valid_ticket_id() {
        // given
        String uniqueLotteryId = "ticket_id1";
        ResultCheckerFacade resultCheckerFacade = mock(ResultCheckerFacade.class);

        // when

        // then

    }

}