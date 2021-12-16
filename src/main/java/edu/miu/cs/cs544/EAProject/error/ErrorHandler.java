package edu.miu.cs.cs544.EAProject.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorMessage> handleStatusException(ResponseStatusException ex) {

        var message = new ErrorMessage(ex.getRawStatusCode(), LocalDateTime.now(), ex.getMessage());
        return new ResponseEntity<>(message, ex.getStatus());
    }

    @ExceptionHandler(ClientException.class)
    public ResponseEntity<ErrorMessage> handleClientException(ClientException ex) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        var message = new ErrorMessage(status.value(), LocalDateTime.now(), ex.getMessage());
        return new ResponseEntity<>(message, status);
    }

    @ExceptionHandler(ServerException.class)
    public ResponseEntity<ErrorMessage> handleServerException(ServerException ex) {

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        var message = new ErrorMessage(status.value(), LocalDateTime.now(), ex.getMessage());
        return new ResponseEntity<>(message, status);
    }

    @ResponseBody
    @ExceptionHandler(EventNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleEventNotFoundException(EventNotFoundException ex) {

        return new ErrorMessage(HttpStatus.NO_CONTENT.value(), LocalDateTime.now(), ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage errorMessage(RuntimeException ex) {

        return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now(), ex.getMessage());
    }
}
