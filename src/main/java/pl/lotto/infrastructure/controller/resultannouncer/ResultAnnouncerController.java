package pl.lotto.infrastructure.controller.resultannouncer;

import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.lotto.resultannouncer.ResultAnnouncerFacade;
import pl.lotto.resultannouncer.dto.ResultDto;

@RestController
@RequestMapping(path = "winners")
@AllArgsConstructor
public class ResultAnnouncerController {

    private final ResultAnnouncerFacade resultAnnouncerFacade;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResultDto> checkWinner(@PathVariable("id") String id) {
        Optional<ResultDto> resultDto1 = resultAnnouncerFacade.checkWinner(id);
        System.out.println(".... " + resultDto1);
        Optional<ResultDto> resultDto = resultDto1;
        return new ResponseEntity<>(resultDto.get(), HttpStatus.OK);
    }

}
