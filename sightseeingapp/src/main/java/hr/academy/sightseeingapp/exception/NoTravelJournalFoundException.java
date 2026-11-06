package hr.academy.sightseeingapp.exception;

import org.springframework.http.HttpStatus;

public class NoTravelJournalFoundException extends SightseeingException {
    public NoTravelJournalFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);;
    }
}
