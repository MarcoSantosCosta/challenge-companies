package com.accenture.challengecompanies.infrastructure.persistence.repositories;

import com.accenture.challengecompanies.domain.exceptions.ElementNotFoundException;
import com.accenture.challengecompanies.infrastructure.persistence.mappings.company.CompanyMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyDataBaseRepositoryInterface extends JpaRepository<CompanyMapping, Long> {
    Optional<CompanyMapping> findByCnpj(String cnpj);
}
