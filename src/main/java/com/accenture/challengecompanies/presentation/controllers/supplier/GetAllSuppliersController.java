package com.accenture.challengecompanies.presentation.controllers.supplier;

import com.accenture.challengecompanies.application.usecases.supplier.GetAllSuppliersUseCase;
import com.accenture.challengecompanies.domain.models.Supplier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetAllSuppliersController {
    private final GetAllSuppliersUseCase getAllSuppliersUseCase;

    public GetAllSuppliersController(GetAllSuppliersUseCase getAllSuppliersUseCase) {
        this.getAllSuppliersUseCase = getAllSuppliersUseCase;
    }

    @GetMapping("/supplier")
    @ResponseStatus(HttpStatus.OK)
    public List<Supplier> getAllSuppliers() {
        return this.getAllSuppliersUseCase.execute();
    }
}