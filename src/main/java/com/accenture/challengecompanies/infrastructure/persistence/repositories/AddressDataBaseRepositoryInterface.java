package com.accenture.challengecompanies.infrastructure.persistence.repositories;

import com.accenture.challengecompanies.domain.enums.DocumentType;
import com.accenture.challengecompanies.infrastructure.persistence.mappings.address.AddressMapping;
import com.accenture.challengecompanies.infrastructure.persistence.mappings.supplier.SupplierMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressDataBaseRepositoryInterface extends JpaRepository<AddressMapping, Long> {
}
