package com.accenture.challengecompanies.domain.repositories;

import com.accenture.challengecompanies.domain.exceptions.ElementNotFoundException;
import com.accenture.challengecompanies.domain.models.Company;

import java.util.List;

public interface CompanyRepositoryInterface {
    Company create(Company company);

    Company getById(long id) throws ElementNotFoundException;

    Company getByCnpj(String CNPJ);

    List<Company> getAll();

    void delete(long id);
}
