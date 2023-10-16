package vader.lab.demo.exception;


import vader.lab.demo.domain.ApiError;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// reference
// https://auth0.com/blog/get-started-with-custom-error-handling-in-spring-boot-java/
// https://thepracticaldeveloper.com/custom-error-handling-rest-controllers-spring-boot/
// https://congsong.tistory.com/53
// https://zetcode.com/springboot/controlleradvice/

@Slf4j
@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    //    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    //    public ResponseEntity<Object> handleerror405(HttpRequestMethodNotSupportedException e) {
    //        Map<String, Object> body = new LinkedHashMap<>();
    //        body.put("timestamp", LocalDateTime.now());
    //        body.put("message", "No countries found");
    //
    //        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    //    }

//    @ExceptionHandler(NullPointerException.class) // exception handled
//    public ResponseEntity<ApiError> handleNullPointerExceptions(
//            Exception e
//    ) {
//
//        HttpStatus status = HttpStatus.NOT_FOUND; // 404
//        log.info("????? 1" + status);
//        return new ResponseEntity<>(
//                new ApiError(
//                        status,
//                        e.getMessage()
//                ),
//                status
//        );
//    }

//    @ExceptionHandler(NoDataFoundException.class)
//    public ResponseEntity<Object> handleNodataFoundException(
//            NoDataFoundException ex, WebRequest request) {
//
//        Map<String, Object> body = new LinkedHashMap<>();
//        body.put("timestamp", LocalDateTime.now());
//        body.put("message", "No countries found");
//
//        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
//    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        System.out.println("method errormethod errormethod errormethod error");
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDate.now());
        body.put("status", status.value());

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
