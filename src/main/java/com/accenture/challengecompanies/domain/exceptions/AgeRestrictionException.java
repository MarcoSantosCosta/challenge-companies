package com.accenture.challengecompanies.domain.exceptions;

public class AgeRestrictionException extends RuntimeException {

    public AgeRestrictionException() {
        super("No estado do Paraná não é possível cadastrar fornecedores pessoa física menores de 18 anos ");
    }

    public AgeRestrictionException(String message) {
        super(message);
    }

    public AgeRestrictionException(String message, Throwable cause) {
        super(message, cause);
    }
}
