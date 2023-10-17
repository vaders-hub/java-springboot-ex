package vader.lab.demo.exception;


import org.springframework.validation.ObjectError;
import vader.lab.demo.domain.ApiError;
import vader.lab.demo.domain.ResultModel;

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
import org.springframework.web.servlet.NoHandlerFoundException;

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
public class ControllerAdvisor {
    // extends ResponseEntityExceptionHandler

    @ExceptionHandler(CustomException.class)
    protected String handleHttpRequestMethodNotSupportedException(final CustomException e) {
        log.error("handleRuntimeException : {}", e.getMessage());
        return e.getMessage();
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Object> handleNodataFoundException(
            NoDataFoundException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "No countries found");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NullPointerException.class) // exception handled
    public ResponseEntity<ApiError> handleNullPointerExceptions(
            Exception e
    ) {

        HttpStatus status = HttpStatus.NOT_FOUND; // 404
        log.info("????? 1" + status);
        return new ResponseEntity<>(
                new ApiError(
                        status,
                        e.getMessage()
                ),
                status
        );
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResultModel> handleExceptions(
            Exception e
    ) {
        ResultModel resultModel = new ResultModel();

        HttpStatus status = HttpStatus.METHOD_NOT_ALLOWED;
        String defaultMessage = e.getMessage();

        resultModel.setResultCode("N000");
        resultModel.setResultMessage(defaultMessage);
        resultModel.setData(null);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultModel);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResultModel> handleMethodArgumentNotValid(
            MethodArgumentNotValidException e
    ) {
        ResultModel resultModel = new ResultModel();

        ObjectError errors = e.getBindingResult().getAllErrors().get(0);
        String defaultMessage = errors.getDefaultMessage();

        resultModel.setResultCode("N000");
        resultModel.setResultMessage(defaultMessage);
        resultModel.setData(null);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultModel);
    }

}
