package com.accenture.challengecompanies.presentation.handlers;

import com.accenture.challengecompanies.domain.exceptions.ElementNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ElementNotFoundExceptionHandler {

    private static final String ERROR_MESSAGE = "Not found element";

    @ExceptionHandler(ElementNotFoundException.class)
    public NotFoundMessageError handleValidationException(ElementNotFoundException exception) {
        return new NotFoundMessageError(ERROR_MESSAGE, exception.getMessage());
    }

    public record NotFoundMessageError(String error, String message) {
    }
}
