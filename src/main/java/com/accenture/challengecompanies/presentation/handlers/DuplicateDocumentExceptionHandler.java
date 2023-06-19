package com.accenture.challengecompanies.presentation.handlers;

import com.accenture.challengecompanies.domain.exceptions.DuplicateDocumentException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicateDocumentExceptionHandler {

    private static final String ERROR_MESSAGE = "Duplicate document";

    @ExceptionHandler(DuplicateDocumentException.class)
    public DuplicateDocumentMessageError handleValidationException(DuplicateDocumentException exception) {
        return new DuplicateDocumentMessageError(ERROR_MESSAGE, exception.getMessage());
    }

    public record DuplicateDocumentMessageError(String error, String message) {
    }
}
