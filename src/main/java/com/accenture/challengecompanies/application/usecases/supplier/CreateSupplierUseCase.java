package com.accenture.challengecompanies.application.usecases.supplier;

import com.accenture.challengecompanies.application.usecases.address.ValidateCepUseCase;
import com.accenture.challengecompanies.domain.exceptions.DuplicateDocumentException;
import com.accenture.challengecompanies.domain.models.Supplier;
import com.accenture.challengecompanies.domain.repositories.SupplierRepositoryInterface;
import org.springframework.stereotype.Service;


//This @Service annotation is intentionally breaking the Clean Architecture principles in order to reduce code verbosity.
//@Service annotation provide an automatic dependency injection for supplierRepository
@Service
public class CreateSupplierUseCase {

    private final SupplierRepositoryInterface supplierRepository;
    private final AgeValidationUseCase ageValidationUseCase;
    private final ValidateCepUseCase validateCepUseCase;

    public CreateSupplierUseCase(SupplierRepositoryInterface supplierRepository,
                                 AgeValidationUseCase ageValidationUseCase,
                                 ValidateCepUseCase validateCepUseCase) {
        this.supplierRepository = supplierRepository;
        this.validateCepUseCase = validateCepUseCase;
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
        this.validateCepUseCase.execute(supplier.getAddress().getZipCode());

        return supplierRepository.create(supplier);
    }

}
