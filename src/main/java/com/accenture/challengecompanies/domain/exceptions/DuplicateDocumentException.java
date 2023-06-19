package com.accenture.challengecompanies.domain.exceptions;

public class DuplicateDocumentException extends RuntimeException {

    public DuplicateDocumentException() {
        super("O documento informado já está em uso");
    }

    public DuplicateDocumentException(String message) {
        super(message);
    }

    public DuplicateDocumentException(String message, Throwable cause) {
        super(message, cause);
    }
}
