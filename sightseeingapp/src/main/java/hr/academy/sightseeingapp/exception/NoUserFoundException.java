package hr.academy.sightseeingapp.exception;

import org.springframework.http.HttpStatus;

public class NoUserFoundException extends SightseeingException {
    public NoUserFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
