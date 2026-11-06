package hr.academy.sightseeingapp.configuration;

import hr.academy.sightseeingapp.dto.HttpErrorResponseMessage;
import hr.academy.sightseeingapp.exception.*;
import hr.academy.sightseeingapp.model.HTTPError;
import hr.academy.sightseeingapp.repository.HttpErrorRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = getLogger(ExceptionHandlerController.class);

    @Autowired
    private HttpErrorRepository httpErrorRepository;

    @Autowired
    private HttpServletRequest httpServletRequest;

    private void saveErrorToDB(String uuid, String message, String type, int httpCode) {
        String body = "";
        String headers = "";

        if (httpServletRequest instanceof ContentCachingRequestWrapper wrapper) {
            body = new String(wrapper.getContentAsByteArray());
        }

        StringBuilder sb =new StringBuilder();
        Collections.list(httpServletRequest.getHeaderNames())
                .forEach(h -> sb.append(h).append(": ").append(httpServletRequest.getHeader(h)).append("\n"));
        headers = sb.toString();

        httpErrorRepository.save(new HTTPError(
                Timestamp.valueOf(LocalDateTime.now()),
                message,
                type,
                httpCode,
                headers,
                body
        ));
    }

    @Override
    protected @Nullable ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var uuid = generateAndLogExceptionId(ex);
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.joining(", "));
        saveErrorToDB(uuid, message, ex.getClass().getSimpleName(), 400);
        HttpErrorResponseMessage response = new HttpErrorResponseMessage(message, Timestamp.valueOf(LocalDateTime.now()).toString(), uuid);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<HttpErrorResponseMessage> handleAnyException(Exception exception){
        var uuid = generateAndLogExceptionId(exception);
        String message = "An unexpected error ocurred.";
        saveErrorToDB(uuid, message, exception.getClass().getSimpleName(), 500);
        HttpErrorResponseMessage response = new HttpErrorResponseMessage(message, Timestamp.valueOf(LocalDateTime.now()).toString(), uuid);

        return ResponseEntity.internalServerError().body(response);
    }

    @ExceptionHandler(SightseeingException.class)
    public final ResponseEntity<HttpErrorResponseMessage> handleSightseeingException(SightseeingException exception){
        var uuid = generateAndLogExceptionId(exception);
        String message = exception.getMessage();
        saveErrorToDB(uuid, message, exception.getClass().getSimpleName(), exception.getHttpStatus().value());
        HttpErrorResponseMessage response = new HttpErrorResponseMessage(message, Timestamp.valueOf(LocalDateTime.now()).toString(), uuid);

        return ResponseEntity.status(exception.getHttpStatus()).body(response);
    }

    private String generateAndLogExceptionId(Exception exception){
        var uuid = UUID.randomUUID().toString();
        LOGGER.error("Unhandled exception uuid: '{}'", uuid, exception);
        return uuid;
    }
}
