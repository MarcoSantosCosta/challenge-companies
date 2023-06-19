package com.accenture.challengecompanies.domain.repositories;

import com.accenture.challengecompanies.domain.enums.DocumentType;
import com.accenture.challengecompanies.domain.exceptions.ElementNotFoundException;
import com.accenture.challengecompanies.domain.models.Supplier;

import java.util.List;

public interface SupplierRepositoryInterface {
    Supplier create(Supplier supplier);

    Supplier getById(long id) throws ElementNotFoundException;

    Supplier findByDocument(String document, DocumentType documentType);

    List<Supplier> getAll();

    void delete(long id);

    Supplier update(Supplier supplier) throws ElementNotFoundException;
}
