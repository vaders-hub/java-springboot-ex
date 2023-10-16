package vader.lab.demo.exception;

public class NoDataFoundException extends RuntimeException {

    public NoDataFoundException(String country) {

        super("No data found" + country);
    }
}
