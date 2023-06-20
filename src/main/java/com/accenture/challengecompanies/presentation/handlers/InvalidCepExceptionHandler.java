package com.accenture.challengecompanies.presentation.handlers;

import com.accenture.challengecompanies.domain.exceptions.DuplicateDocumentException;
import com.accenture.challengecompanies.domain.exceptions.InvalidCepException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidCepExceptionHandler {

    private static final String ERROR_MESSAGE = "Invalid CEP";

    @ExceptionHandler(InvalidCepException.class)
    public ErrorResponse handlenException(InvalidCepException exception) {
        List<ErrorMessage> errors = new ArrayList<>();
        errors.add(new ErrorMessage("cep",exception.getMessage()));
        return new ErrorResponse(ERROR_MESSAGE, errors);
    }



}
