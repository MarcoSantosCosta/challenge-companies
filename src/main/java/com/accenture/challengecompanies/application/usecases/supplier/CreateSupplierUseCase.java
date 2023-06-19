package com.accenture.challengecompanies.application.usecases.supplier;

import com.accenture.challengecompanies.domain.exceptions.DuplicateDocumentException;
import com.accenture.challengecompanies.domain.models.Supplier;
import com.accenture.challengecompanies.domain.repositories.SupplierRepositoryInterface;
import org.springframework.stereotype.Service;


//This @Service annotation is intentionally breaking the Clean Architecture principles in order to reduce code verbosity.
//@Service annotation provide an automatic dependency injection for supplierRepository
@Service
public class CreateSupplierUseCase {

    private final SupplierRepositoryInterface supplierRepository;

    public CreateSupplierUseCase(SupplierRepositoryInterface supplierRepository) {

        this.supplierRepository = supplierRepository;
    }

    public Supplier execute(Supplier supplier) {

        if (supplierRepository.findByDocument(
                supplier.getDocument(),
                supplier.getDocumentType()) != null){
            throw new DuplicateDocumentException(
                    String.format("O, %s %s. já está em uso", supplier.getDocumentType(),supplier.getDocument()));
        }

        return supplierRepository.create(supplier);
    }

}
