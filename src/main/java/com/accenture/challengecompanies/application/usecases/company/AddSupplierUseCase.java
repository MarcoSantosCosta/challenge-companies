package com.accenture.challengecompanies.application.usecases.company;

import com.accenture.challengecompanies.domain.models.Supplier;
import com.accenture.challengecompanies.domain.repositories.CrossRepositoryInterface;
import org.springframework.stereotype.Service;

import java.util.List;

//This @Service annotation is intentionally breaking the Clean Architecture principles in order to reduce code verbosity.
//@Service annotation provide an automatic dependency injection for companyRepository
@Service
public class AddSupplierUseCase {

    private final CrossRepositoryInterface relatedDatabaseRepository;

    public AddSupplierUseCase(CrossRepositoryInterface relatedDatabaseRepository) {
        this.relatedDatabaseRepository = relatedDatabaseRepository;
    }

    public List<Supplier> execute(long companyId, long supplierId) {
        return relatedDatabaseRepository.addSupplier(companyId,supplierId);
    }
}
