package com.accenture.challengecompanies.domain.exceptions;

public class ElementNotFoundException extends RuntimeException {

    public ElementNotFoundException() {
        super("Elemento não encontrado");
    }

    public ElementNotFoundException(String message) {
        super(message);
    }

    public ElementNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
