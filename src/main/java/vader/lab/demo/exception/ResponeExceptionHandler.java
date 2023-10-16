package vader.lab.demo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

@Slf4j
@ControllerAdvice
@RestController
public class ResponeExceptionHandler {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public String handleMissingServletRequestParameterException(
            MissingServletRequestParameterException ex) {
        return "paramer is null : " + ex.getParameterName();
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String handleAllExceptions(Exception ex) {
        log.error("sfsfsdfsdfsdfsdf");
        System.out.println("sfdsdfsdfd");
        return "????"; // ex.getMessage();
    }
}
