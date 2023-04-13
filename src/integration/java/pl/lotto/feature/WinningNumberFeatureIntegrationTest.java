package pl.lotto.feature;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import pl.lotto.IntegrationTestConfiguration;
import pl.lotto.LottoApplication;
import pl.lotto.infrastructure.controller.resultannouncer.error.ErrorResponseDto;
import pl.lotto.numberreceiver.AdjustableClock;
import pl.lotto.numberreceiver.dto.DrawDateDto;
import pl.lotto.numberreceiver.dto.TicketDto;
import pl.lotto.resultannouncer.dto.ResultDto;
import pl.lotto.resultchecker.NotGeneratedResultsException;
import pl.lotto.resultchecker.ResultCheckerFacade;
import pl.lotto.winningnumbergenerator.NotFoundWinningNumbersException;
import pl.lotto.winningnumbergenerator.NumberGeneratorFacade;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {LottoApplication.class, IntegrationTestConfiguration.class})
@AutoConfigureMockMvc
@Testcontainers
@ActiveProfiles("integration")
public class WinningNumberFeatureIntegrationTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    NumberGeneratorFacade numberGeneratorFacade;
    @Autowired
    ResultCheckerFacade resultCheckerFacade;
    @Autowired
    AdjustableClock clock;

    @Container
    public static final MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.0.10"));

    @DynamicPropertySource
    public static void propertyOverride(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Test
    public void happy_path_user_winning() throws Exception {

        // step 1: user gave numbers and got response 201 CREATED on 4-4-2023 at 11.00
        // given
        // when
        ResultActions response = mockMvc.perform(post("/inputNumbers?numbers=1,2,3,4,5,6"));
        // then
        response.andExpect(status().isCreated());
        String json = response.andReturn().getResponse().getContentAsString();
        TicketDto ticketDto = objectMapper.readValue(json, TicketDto.class);
        LocalDateTime drawDate = ticketDto.drawDate();
        assertThat(drawDate).isEqualTo(LocalDateTime.of(2023, 4, 8, 12, 0));

        // step 2: user checked result before numbers were generated
        // given
        // when
        String ticketId = ticketDto.lotteryId();
        ResultActions responseCheckWinningBeforeDraw = mockMvc.perform(get("/winners/" + ticketId));
        // then
        responseCheckWinningBeforeDraw.andExpect(status().isNotFound());
        String jsonCheckWinningBeforeDraw = responseCheckWinningBeforeDraw.andReturn().getResponse().getContentAsString();
        ErrorResponseDto errorResponseDto = objectMapper.readValue(jsonCheckWinningBeforeDraw, ErrorResponseDto.class);
        assertThat(errorResponseDto.message()).isEqualTo("Ticket not found or numbers have not been drawn yet");

        // step 3: winning numbers were generated
        // given
        DrawDateDto dateDto = new DrawDateDto(drawDate);
        // when && then
        Awaitility.await()
                .atMost(Duration.ofSeconds(20))
                .pollInterval(Duration.ofSeconds(1))
                .until(() -> {
                    try {
                        return !numberGeneratorFacade.retrieveWonNumbersForDate(dateDto).numbers().isEmpty();
                    } catch (NotFoundWinningNumbersException exception) {
                        return false;
                    }
                });

        // step 4: results were checked
        // given
        // when
        // then
        Awaitility.await()
                .atMost(Duration.ofSeconds(20))
                .pollInterval(Duration.ofSeconds(1))
                .until(() -> {
                    try {
                        return !resultCheckerFacade.generateResults().isEmpty();
                    } catch (NotGeneratedResultsException exception) {
                        return false;
                    }
                });

        // step 5: user checked result on 9-4-2023
        // given
        // when
        clock.plusDays(5);
        // String ticketId = ticketDto.lotteryId();
        ResultActions responseCheckWinning = mockMvc.perform(get("/winners/" + ticketId));
        // then
        responseCheckWinning.andExpect(status().isOk());
        String jsonCheckWinning = responseCheckWinning.andReturn().getResponse().getContentAsString();
        ResultDto responseDto = objectMapper.readValue(jsonCheckWinning, ResultDto.class);
        assertThat(responseDto.hitNumber()).isEqualTo(6);

    }

}
