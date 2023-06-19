package com.accenture.challengecompanies.domain.exceptions;

public class InvalidCepException extends RuntimeException {

    public InvalidCepException() {
        super("O CEP informado não é válido");
    }

    public InvalidCepException(String message) {
        super(message);
    }

    public InvalidCepException(String message, Throwable cause) {
        super(message, cause);
    }
}
