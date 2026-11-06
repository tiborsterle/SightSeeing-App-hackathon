package hr.academy.sightseeingapp.exception;

import org.springframework.http.HttpStatus;

public class NoTypeFoundException extends SightseeingException {
    public NoTypeFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
