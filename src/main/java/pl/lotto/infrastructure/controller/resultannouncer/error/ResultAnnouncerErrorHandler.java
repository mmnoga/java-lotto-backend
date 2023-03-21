package pl.lotto.infrastructure.controller.resultannouncer.error;


import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.lotto.infrastructure.controller.resultannouncer.ResultAnnouncerController;

@ControllerAdvice(basePackageClasses = {ResultAnnouncerController.class})
public class ResultAnnouncerErrorHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErorrResponseDto> f(RuntimeException exception) {
        return ResponseEntity.status(HttpStatusCode.valueOf(204))
                .body(new ErorrResponseDto(exception.getMessage()));
    }
//    @ExceptionHandler(RuntimeException.class)
//    public void f() {
//
//    }
//    @ExceptionHandler(RuntimeException.class)
//    public void f() {
//
//    }
//    @ExceptionHandler(RuntimeException.class)
//    public void f() {
//
//    }
}
