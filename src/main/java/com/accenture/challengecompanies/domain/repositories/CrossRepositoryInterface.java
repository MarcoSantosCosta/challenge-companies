package com.accenture.challengecompanies.domain.repositories;

import com.accenture.challengecompanies.domain.models.Supplier;

import java.util.List;

public interface CrossRepositoryInterface {
    List<Supplier> addSupplier(long idCompany, long idSupplier);
}
