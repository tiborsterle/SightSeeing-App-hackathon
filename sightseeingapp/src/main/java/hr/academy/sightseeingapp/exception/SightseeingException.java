package hr.academy.sightseeingapp.exception;

import org.springframework.http.HttpStatus;

public class SightseeingException extends RuntimeException {
    private final HttpStatus httpStatus;

    public SightseeingException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
