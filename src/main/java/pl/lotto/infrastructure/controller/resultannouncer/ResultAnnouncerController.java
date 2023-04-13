package pl.lotto.infrastructure.controller.resultannouncer;

import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lotto.resultannouncer.ResultAnnouncerFacade;
import pl.lotto.resultannouncer.dto.ResultDto;

@RestController
@RequestMapping(path = "winners")
@AllArgsConstructor
public class ResultAnnouncerController {
    private final ResultAnnouncerFacade resultAnnouncerFacade;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResultDto> checkWinner(@PathVariable("id") String id) {
        ResultDto resultDto = resultAnnouncerFacade.checkWinner(id);
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }

}
