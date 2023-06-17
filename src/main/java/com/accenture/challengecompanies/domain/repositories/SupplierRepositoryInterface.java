package com.accenture.challengecompanies.domain.repositories;

import com.accenture.challengecompanies.domain.exceptions.ElementNotFoundException;
import com.accenture.challengecompanies.domain.models.Supplier;

import java.util.List;

public interface SupplierRepositoryInterface {
    Supplier create(Supplier supplier);

    Supplier getById(long id) throws ElementNotFoundException;

    Supplier getByDocument(String document);

    List<Supplier> getAll();

    void delete(long id);

    Supplier update(Supplier supplier) throws ElementNotFoundException;
}
