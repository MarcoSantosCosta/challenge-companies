package com.accenture.challengecompanies.domain.repositories;

import com.accenture.challengecompanies.domain.exceptions.ElementNotFoundException;
import com.accenture.challengecompanies.domain.models.Company;
import com.accenture.challengecompanies.domain.models.Supplier;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface CompanyRepositoryInterface {
    Company create(Company company);

    Company getById(long id) throws ElementNotFoundException;

    List<Supplier> getSuppliers(long id);

    Company findByCnpj(String CNPJ);

    List<Company> getAll();

    void delete(long id);

    Company update(Company company) throws ElementNotFoundException;

//    Company addSupplier(long companyid, Supplier supplier);
}
