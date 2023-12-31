package com.accenture.challengecompanies.infrastructure.persistence.repositories;

import com.accenture.challengecompanies.domain.enums.DocumentType;
import com.accenture.challengecompanies.infrastructure.persistence.mappings.supplier.SupplierMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SupplierDataBaseRepositoryInterface extends JpaRepository<SupplierMapping, Long> {
  Optional<SupplierMapping> findByDocumentAndDocumentType(String document, DocumentType documentType);
}
