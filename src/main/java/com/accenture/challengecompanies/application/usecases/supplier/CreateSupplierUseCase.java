package com.accenture.challengecompanies.application.usecases.supplier;

import com.accenture.challengecompanies.application.usecases.address.UpdateAddressUseCase;
import com.accenture.challengecompanies.domain.exceptions.DuplicateDocumentException;
import com.accenture.challengecompanies.domain.models.Address;
import com.accenture.challengecompanies.domain.models.Supplier;
import com.accenture.challengecompanies.domain.repositories.SupplierRepositoryInterface;
import org.springframework.stereotype.Service;


//This @Service annotation is intentionally breaking the Clean Architecture principles in order to reduce code verbosity.
//@Service annotation provide an automatic dependency injection for supplierRepository
@Service
public class CreateSupplierUseCase {

    private final SupplierRepositoryInterface supplierRepository;
    private final UpdateAddressUseCase updateAddressUseCase;
    private final AgeValidationUseCase ageValidationUseCase;

    public CreateSupplierUseCase(SupplierRepositoryInterface supplierRepository,
                                 UpdateAddressUseCase updateAddressUseCase,
                                 AgeValidationUseCase ageValidationUseCase) {
        this.supplierRepository = supplierRepository;
        this.updateAddressUseCase = updateAddressUseCase;
        this.ageValidationUseCase = ageValidationUseCase;
    }

    public Supplier execute(Supplier supplier) {

        if (supplierRepository.findByDocument(
                supplier.getDocument(),
                supplier.getDocumentType()) != null) {
            throw new DuplicateDocumentException(
                    String.format("O, %s %s. já está em uso", supplier.getDocumentType(), supplier.getDocument()));
        }
        this.ageValidationUseCase.execute(supplier);
        Address addressCreated = updateAddressUseCase.execute(supplier.getAddress());
        supplier.setAddress(addressCreated);
        return supplierRepository.create(supplier);
    }

}
