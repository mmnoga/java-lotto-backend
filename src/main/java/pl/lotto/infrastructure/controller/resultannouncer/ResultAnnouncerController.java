package pl.lotto.infrastructure.controller.resultannouncer;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.lotto.resultannouncer.ResultAnnouncerFacade;
import pl.lotto.resultannouncer.dto.ResultDto;

import java.util.Optional;

@RestController
@RequestMapping(path = "winners")
@AllArgsConstructor
public class ResultAnnouncerController {

    private final ResultAnnouncerFacade resultAnnouncerFacade;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResultDto> checkWinner(@PathVariable("id") String id) {
        try {
            System.out.println(".... "+resultAnnouncerFacade.checkWinner(id));
            Optional<ResultDto> resultDto = resultAnnouncerFacade.checkWinner(id);
            return new ResponseEntity<>(resultDto.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

}
