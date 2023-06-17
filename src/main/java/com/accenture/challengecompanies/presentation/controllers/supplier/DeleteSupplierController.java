package com.accenture.challengecompanies.presentation.controllers.supplier;

import com.accenture.challengecompanies.application.usecases.supplier.DeleteSupplierUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteSupplierController {

    private final DeleteSupplierUseCase deleteSupplierUseCase;

    public DeleteSupplierController(DeleteSupplierUseCase deleteSupplierUseCase) {
        this.deleteSupplierUseCase = deleteSupplierUseCase;
    }

    @DeleteMapping("/supplier/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSupplier(@PathVariable Long id) {
        this.deleteSupplierUseCase.execute(id);
    }

}
