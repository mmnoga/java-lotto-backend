package pl.lotto.infrastructure.controller.resultannouncer.error;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.lotto.infrastructure.controller.resultannouncer.ResultAnnouncerController;
import pl.lotto.resultchecker.TicketNotFoundException;

@ControllerAdvice(basePackageClasses = {ResultAnnouncerController.class})
public class ResultAnnouncerErrorHandler {
    @ExceptionHandler(TicketNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleTicketNotFound(TicketNotFoundException exception) {
        return ResponseEntity.status(HttpStatusCode.valueOf(404))
                .body(new ErrorResponseDto(exception.getMessage()));
    }

}
