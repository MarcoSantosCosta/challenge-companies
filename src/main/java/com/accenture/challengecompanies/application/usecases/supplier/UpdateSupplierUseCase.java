package com.accenture.challengecompanies.application.usecases.supplier;

import com.accenture.challengecompanies.domain.exceptions.ElementNotFoundException;
import com.accenture.challengecompanies.domain.models.Supplier;
import com.accenture.challengecompanies.domain.repositories.SupplierRepositoryInterface;
import org.springframework.stereotype.Service;


//This @Service annotation is intentionally breaking the Clean Architecture principles in order to reduce code verbosity.
//@Service annotation provide an automatic dependency injection for supplierRepository
@Service
public class UpdateSupplierUseCase {

    private final SupplierRepositoryInterface supplierRepository;

    public UpdateSupplierUseCase(SupplierRepositoryInterface supplierRepository) {

        this.supplierRepository = supplierRepository;
    }

    public Supplier execute(Supplier supplier, long id) throws ElementNotFoundException {
        supplier.setId(id);
        return supplierRepository.update(supplier);
    }

}
