package com.accenture.challengecompanies.presentation.controllers.supplier;

import com.accenture.challengecompanies.application.usecases.supplier.UpdateSupplierUseCase;
import com.accenture.challengecompanies.domain.models.Supplier;
import com.accenture.challengecompanies.presentation.dto.UpdateSupplierRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class UpdateSupplierController {

    private final UpdateSupplierUseCase updateSupplierUseCase;

    public UpdateSupplierController(UpdateSupplierUseCase updateSupplierUseCase) {
        this.updateSupplierUseCase = updateSupplierUseCase;
    }

    @PutMapping("/supplier/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Supplier updateSupplier(@RequestBody @Valid UpdateSupplierRequest request, @PathVariable long id) {
        return this.updateSupplierUseCase.execute(request.toSupplierModel(), id);
    }


}
