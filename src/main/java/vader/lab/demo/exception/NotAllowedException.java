package vader.lab.demo.exception;

import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.http.HttpHeaders;

public class NotAllowedException extends HttpRequestMethodNotSupportedException {

    public NotAllowedException() {
        super("");
    }

}
