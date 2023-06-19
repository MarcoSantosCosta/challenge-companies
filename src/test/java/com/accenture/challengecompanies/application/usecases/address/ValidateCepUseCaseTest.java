package com.accenture.challengecompanies.application.usecases.address;

import com.accenture.challengecompanies.domain.exceptions.InvalidCepException;
import com.accenture.challengecompanies.domain.models.Address;
import com.accenture.challengecompanies.infrastructure.services.CepFetchInterface;
import com.accenture.challengecompanies.presentation.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class ValidateCepUseCaseTest {

    @Mock
    private CepFetchInterface cepFetch;

    private ValidateCepUseCase validateCepUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        validateCepUseCase = new ValidateCepUseCase(cepFetch);
    }

    @Test
    void shouldReturnAValidAddress() {
        String cep = "12345678";
        Address expectedAddress = Utils.generateDummyAddres();
        expectedAddress.setZipCode(cep);
        Mockito.when(cepFetch.fetch(cep)).thenReturn(expectedAddress);

        Address resultAddress = validateCepUseCase.execute(cep);

        Assertions.assertEquals(expectedAddress, resultAddress);
        Mockito.verify(cepFetch, Mockito.times(1)).fetch(cep);
    }

    @Test
    void shouldThrowsInvalidCepException() {
        String cep = "99999999";
        Mockito.when(cepFetch.fetch(cep)).thenReturn(null);

        InvalidCepException exception = Assertions.assertThrows(InvalidCepException.class,
                () -> validateCepUseCase.execute(cep));

        String expectedMessage = String.format("O CEP %s não é válido", cep);
        Assertions.assertEquals(expectedMessage, exception.getMessage());
        Mockito.verify(cepFetch, Mockito.times(1)).fetch(cep);
    }
}
