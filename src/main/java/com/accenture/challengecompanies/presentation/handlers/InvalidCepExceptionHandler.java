package com.accenture.challengecompanies.presentation.handlers;

import com.accenture.challengecompanies.domain.exceptions.DuplicateDocumentException;
import com.accenture.challengecompanies.domain.exceptions.InvalidCepException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidCepExceptionHandler {

    private static final String ERROR_MESSAGE = "Invalid cep";

    @ExceptionHandler(InvalidCepException.class)
    public InvalidCepMessageError handlenException(InvalidCepException exception) {
        return new InvalidCepMessageError(ERROR_MESSAGE, exception.getMessage());
    }

    public record InvalidCepMessageError(String error, String message) {
    }
}
