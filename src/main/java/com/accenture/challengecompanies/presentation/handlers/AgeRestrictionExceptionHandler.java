package com.accenture.challengecompanies.presentation.handlers;

import com.accenture.challengecompanies.domain.exceptions.AgeRestrictionException;
import com.accenture.challengecompanies.domain.exceptions.DuplicateDocumentException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AgeRestrictionExceptionHandler {

    private static final String ERROR_MESSAGE = "state age restriction";

    @ExceptionHandler(AgeRestrictionException.class)
    public AgeRestrictionMessageError handleValidationException(AgeRestrictionException exception) {
        return new AgeRestrictionMessageError(ERROR_MESSAGE, exception.getMessage());
    }

    public record AgeRestrictionMessageError(String error, String message) {
    }
}
