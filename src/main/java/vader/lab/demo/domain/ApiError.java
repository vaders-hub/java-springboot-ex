package vader.lab.demo.domain;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiError {
    private HttpStatus status;
    private String message;
    private Object errors;

    public ApiError(HttpStatus status, String message) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }
}
