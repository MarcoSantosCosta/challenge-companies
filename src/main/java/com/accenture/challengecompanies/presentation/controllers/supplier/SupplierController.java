package com.accenture.challengecompanies.presentation.controllers.supplier;

import com.accenture.challengecompanies.application.usecases.supplier.*;
import com.accenture.challengecompanies.domain.enums.DocumentType;
import com.accenture.challengecompanies.domain.exceptions.ElementNotFoundException;
import com.accenture.challengecompanies.domain.models.Supplier;
import com.accenture.challengecompanies.presentation.dto.AddSupplierRequest;
import com.accenture.challengecompanies.presentation.dto.UpdateSupplierRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    private final GetSupplierByDocumentUseCase getSupplierByDocumentUseCase;
    private final GetSupplierByIdUseCase getSupplierByIdUseCase;
    private final GetAllSuppliersUseCase getAllSuppliersUseCase;
    private final DeleteSupplierUseCase deleteSupplierUseCase;
    private final CreateSupplierUseCase createSupplierUseCase;
    private final UpdateSupplierUseCase updateSupplierUseCase;

    public SupplierController(GetSupplierByDocumentUseCase getSupplierByDocumentUseCase,
                              GetSupplierByIdUseCase getSupplierByIdUseCase,
                              GetAllSuppliersUseCase getAllSuppliersUseCase,
                              DeleteSupplierUseCase deleteSupplierUseCase,
                              CreateSupplierUseCase createSupplierUseCase,
                              UpdateSupplierUseCase updateSupplierUseCase) {
        this.getSupplierByDocumentUseCase = getSupplierByDocumentUseCase;
        this.getSupplierByIdUseCase = getSupplierByIdUseCase;
        this.getAllSuppliersUseCase = getAllSuppliersUseCase;
        this.deleteSupplierUseCase = deleteSupplierUseCase;
        this.createSupplierUseCase = createSupplierUseCase;
        this.updateSupplierUseCase = updateSupplierUseCase;
    }

    @GetMapping("/{documentType}/{document}")
    @ResponseStatus(HttpStatus.OK)
    public Supplier getSupplierByDocument(@PathVariable DocumentType documentType,
                                          @PathVariable String document) throws ElementNotFoundException {
        return getSupplierByDocumentUseCase.execute(document, documentType);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Supplier getSupplierById(@PathVariable long id) throws ElementNotFoundException {
        return getSupplierByIdUseCase.execute(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Supplier> getAllSuppliers() {
        return getAllSuppliersUseCase.execute();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSupplier(@PathVariable Long id) {
        deleteSupplierUseCase.execute(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Supplier createSupplier(@RequestBody @Valid AddSupplierRequest request) {
        return createSupplierUseCase.execute(request.toSupplierModel());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Supplier updateSupplier(@RequestBody @Valid UpdateSupplierRequest request,
                                   @PathVariable long id) {
        return updateSupplierUseCase.execute(request.toSupplierModel(), id);
    }
}
