package com.accenture.challengecompanies.presentation.handlers;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class InputDataValidationExceptionHandler {

    private static final String ERROR_MESSAGE = "Invalid field(s)";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public InvalidFieldsErrorsResponse handleValidationException(MethodArgumentNotValidException ex) {
        List<InvalidFieldMessageError> errors = new ArrayList<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(new InvalidFieldMessageError(error.getField(), error.getDefaultMessage()));
        }

        return new InvalidFieldsErrorsResponse(ERROR_MESSAGE, errors);
    }

    public record InvalidFieldsErrorsResponse(String error, List<InvalidFieldMessageError> errorsMessages) {
    }

    public record InvalidFieldMessageError(String field, String message) {
    }
}
