package com.accenture.challengecompanies.presentation.handlers;

import com.accenture.challengecompanies.domain.exceptions.AgeRestrictionException;
import com.accenture.challengecompanies.domain.exceptions.DuplicateDocumentException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AgeRestrictionExceptionHandler {

    private static final String ERROR_MESSAGE = "state age restriction";

    @ExceptionHandler(AgeRestrictionException.class)
    public ErrorResponse handleValidationException(AgeRestrictionException exception) {
        List<ErrorMessage> errors = new ArrayList<>();
        errors.add(new ErrorMessage("birthDate", exception.getMessage()));
        return new ErrorResponse(ERROR_MESSAGE,errors);
    }
}
