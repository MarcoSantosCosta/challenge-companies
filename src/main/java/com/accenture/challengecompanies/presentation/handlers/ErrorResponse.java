package com.accenture.challengecompanies.presentation.handlers;


import java.util.List;


public record ErrorResponse(String errorType, List<ErrorMessage> errorMessages) {
}
