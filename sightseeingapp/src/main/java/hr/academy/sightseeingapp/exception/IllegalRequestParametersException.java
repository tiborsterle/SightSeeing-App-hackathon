package hr.academy.sightseeingapp.exception;

import org.springframework.http.HttpStatus;

public class IllegalRequestParametersException extends SightseeingException {
    public IllegalRequestParametersException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
