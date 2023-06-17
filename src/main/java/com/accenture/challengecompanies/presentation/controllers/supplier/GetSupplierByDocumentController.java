package com.accenture.challengecompanies.presentation.controllers.supplier;

import com.accenture.challengecompanies.application.usecases.supplier.GetSupplierByDocumentUseCase;
import com.accenture.challengecompanies.domain.exceptions.ElementNotFoundException;
import com.accenture.challengecompanies.domain.models.Supplier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetSupplierByDocumentController {
    private final GetSupplierByDocumentUseCase getSupplierByDocumentUseCase;

    public GetSupplierByDocumentController(GetSupplierByDocumentUseCase getSupplierByDocumentUseCase) {
        this.getSupplierByDocumentUseCase = getSupplierByDocumentUseCase;
    }

    @GetMapping("/supplier/cnpj/{cnpj}")
    @ResponseStatus(HttpStatus.OK)
    public Supplier getSupplierByDocument(@PathVariable String cnpj) throws ElementNotFoundException {
        return this.getSupplierByDocumentUseCase.execute(cnpj);
    }
}