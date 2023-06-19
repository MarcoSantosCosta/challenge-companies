package com.accenture.challengecompanies.application.usecases.address;

import com.accenture.challengecompanies.domain.exceptions.InvalidCepException;
import com.accenture.challengecompanies.domain.models.Address;
import com.accenture.challengecompanies.infrastructure.services.CepFetchInterface;
import org.springframework.stereotype.Component;

@Component
public class ValidateCepUseCase {

    private final CepFetchInterface cepFetch;

    public ValidateCepUseCase(CepFetchInterface cepFetchInterface) {
        this.cepFetch = cepFetchInterface;
    }

    public Address execute(String cep) {
        Address address = this.cepFetch.fetch(cep);
        if (address == null) {
            throw new InvalidCepException(String.format("O CEP %s não é válido", cep));
        }
        return address;
    }
}
