package com.accenture.challengecompanies.presentation.handlers;

import com.accenture.challengecompanies.domain.exceptions.ElementNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ElementNotFoundExceptionHandler {

    private static final String ERROR_MESSAGE = "Not found element";

    @ExceptionHandler(ElementNotFoundException.class)
    public ErrorResponse handleValidationException(ElementNotFoundException exception) {
        List<ErrorMessage> errors = new ArrayList<>();
        errors.add(new ErrorMessage("id", exception.getMessage()));
        return new ErrorResponse(ERROR_MESSAGE, errors);
    }

}
