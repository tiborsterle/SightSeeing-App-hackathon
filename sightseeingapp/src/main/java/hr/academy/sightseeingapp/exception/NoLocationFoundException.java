package hr.academy.sightseeingapp.exception;

import org.springframework.http.HttpStatus;

public class NoLocationFoundException extends SightseeingException {
    public NoLocationFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);;
    }
}
