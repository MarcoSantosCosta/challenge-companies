package com.accenture.challengecompanies.infrastructure.services;

import com.accenture.challengecompanies.domain.models.Address;

public interface CepFetchInterface {
    public Address fetch(String cep);
}
