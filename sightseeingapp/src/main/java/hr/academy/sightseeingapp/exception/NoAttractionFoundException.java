package hr.academy.sightseeingapp.exception;

import org.springframework.http.HttpStatus;

public class NoAttractionFoundException extends SightseeingException {
    public NoAttractionFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
