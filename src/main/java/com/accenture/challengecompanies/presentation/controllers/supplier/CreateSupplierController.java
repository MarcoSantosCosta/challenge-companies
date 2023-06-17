package com.accenture.challengecompanies.presentation.controllers.supplier;

import com.accenture.challengecompanies.application.usecases.supplier.CreateSupplierUseCase;
import com.accenture.challengecompanies.domain.models.Supplier;

import com.accenture.challengecompanies.presentation.dto.AddSupplierRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateSupplierController {

    private final CreateSupplierUseCase createSupplierUseCase;

    public CreateSupplierController(CreateSupplierUseCase createSupplierUseCase) {
        this.createSupplierUseCase = createSupplierUseCase;
    }

    @PostMapping("/supplier")
    @ResponseStatus(HttpStatus.CREATED)
    public Supplier createSupplier(@RequestBody @Valid AddSupplierRequest request) {
        return this.createSupplierUseCase.execute(request.toSupplierModel());
    }


}
