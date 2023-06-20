package com.accenture.challengecompanies.infrastructure.persistence.repositories;

import com.accenture.challengecompanies.domain.exceptions.ElementNotFoundException;
import com.accenture.challengecompanies.domain.models.Supplier;
import com.accenture.challengecompanies.domain.repositories.CrossRepositoryInterface;
import com.accenture.challengecompanies.infrastructure.persistence.mappings.company.CompanyMapping;
import com.accenture.challengecompanies.infrastructure.persistence.mappings.supplier.SupplierMapping;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CrossDatabaseRepository implements CrossRepositoryInterface {

    private final CompanyDataBaseRepositoryInterface companyDataBaseRepository;
    private final SupplierDataBaseRepositoryInterface supplierDatabaseRepository;

    public CrossDatabaseRepository(CompanyDataBaseRepositoryInterface companyDataBaseRepository, SupplierDataBaseRepositoryInterface supplierDatabaseRepository) {
        this.companyDataBaseRepository = companyDataBaseRepository;
        this.supplierDatabaseRepository = supplierDatabaseRepository;
    }

    @Override
    public List<Supplier> addSupplier(long idCompany, long idSupplier) {
        Optional<CompanyMapping> optionalCompany = companyDataBaseRepository.findById(idCompany);
        Optional<SupplierMapping> optionalSupplier = supplierDatabaseRepository.findById(idSupplier);

        if(optionalCompany.isEmpty()){
            throw new ElementNotFoundException("Empresa não encontrada");
        } else if ( optionalSupplier.isEmpty()) {
            throw new ElementNotFoundException("Forncedor não entrado");
        }
        else {
            CompanyMapping company = optionalCompany.get();
            SupplierMapping supplier = optionalSupplier.get();

            company.getSuppliers().add(supplier);
            companyDataBaseRepository.save(company);

            return company.getSuppliers()
                    .stream()
                    .map(SupplierMapping::toModel)
                    .collect(Collectors.toList());
        }
    }
}
