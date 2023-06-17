package com.accenture.challengecompanies.presentation.controllers.supplier;

import com.accenture.challengecompanies.application.usecases.supplier.GetSupplierByIdUseCase;
import com.accenture.challengecompanies.domain.exceptions.ElementNotFoundException;
import com.accenture.challengecompanies.domain.models.Supplier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetByIdSupplierController {
    private final GetSupplierByIdUseCase getSupplierByIdUseCase;

    public GetByIdSupplierController(GetSupplierByIdUseCase getSupplierByIdUseCase) {
        this.getSupplierByIdUseCase = getSupplierByIdUseCase;
    }

    @GetMapping("/supplier/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Supplier getSupplierById(@PathVariable long id) throws ElementNotFoundException {
        return this.getSupplierByIdUseCase.execute(id);
    }
}