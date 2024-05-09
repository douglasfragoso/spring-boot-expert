package spring.boot.expert.curso.controller.exception;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import spring.boot.expert.curso.service.exception.DatabaseException;
import spring.boot.expert.curso.service.exception.ExceptionBusinessRules;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ExceptionBusinessRules.class)
    public ResponseEntity<StandartError> resourceNotFound(ExceptionBusinessRules e, HttpServletRequest request) {
        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandartError err = new StandartError(Instant.now(), status.value(), error, e.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandartError> database(DatabaseException e, HttpServletRequest request) {
        String error = "Database error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandartError err = new StandartError(Instant.now(), status.value(), error, e.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<StandartError> authentication(BadCredentialsException e, HttpServletRequest request) {
        String error = "Authentication error";
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        StandartError err = new StandartError(Instant.now(), status.value(), error, e.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ValidationError> ConstraintViolationException(ConstraintViolationException ex,
            HttpServletRequest request) {
        String error = "Validation Error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        List<String> errors = ex.getConstraintViolations().stream()
                .map(violation -> violation.getMessage())
                .collect(Collectors.toList());

        ValidationError err = new ValidationError(Instant.now(), status.value(), error, errors,
                request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

}