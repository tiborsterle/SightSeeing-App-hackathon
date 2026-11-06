package hr.academy.sightseeingapp.exception;

import org.springframework.http.HttpStatus;

public class NoImageFoundException extends SightseeingException {
    public NoImageFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
