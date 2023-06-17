package com.accenture.challengecompanies.application.usecases.supplier;

import com.accenture.challengecompanies.domain.models.Supplier;
import com.accenture.challengecompanies.domain.repositories.SupplierRepositoryInterface;
import org.springframework.stereotype.Service;

import java.util.List;

//This @Service annotation is intentionally breaking the Clean Architecture principles in order to reduce code verbosity.
//@Service annotation provide an automatic dependency injection for supplierRepository
@Service
public class GetAllSuppliersUseCase {

    private final SupplierRepositoryInterface supplierRepository;

    public GetAllSuppliersUseCase(SupplierRepositoryInterface supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public List<Supplier> execute() {

        return supplierRepository.getAll();

    }
}
