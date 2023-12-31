package com.accenture.challengecompanies.presentation.controllers.company;

import com.accenture.challengecompanies.application.usecases.company.*;
import com.accenture.challengecompanies.domain.exceptions.ElementNotFoundException;
import com.accenture.challengecompanies.domain.models.Company;
import com.accenture.challengecompanies.domain.models.Supplier;
import com.accenture.challengecompanies.presentation.dto.AddCompanyRequest;
import com.accenture.challengecompanies.presentation.dto.AddSupplierToCompany;
import com.accenture.challengecompanies.presentation.dto.UpdateCompanyRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CreateCompanyUseCase createCompanyUseCase;
    private final UpdateCompanyUseCase updateCompanyUseCase;
    private final GetCompanyByIdUseCase getCompanyByIdUseCase;
    private final GetCompanyByCnpjUseCase getCompanyByCnpjUseCase;
    private final GetAllCompaniesUseCase getAllCompaniesUseCase;
    private final DeleteCompanyUseCase deleteCompanyUseCase;
    private final AddSupplierUseCase addSupplierUseCase;
    private final GetSuppliersUseCase getSuppliersUseCase;

    public CompanyController(
            CreateCompanyUseCase createCompanyUseCase,
            UpdateCompanyUseCase updateCompanyUseCase,
            GetCompanyByIdUseCase getCompanyByIdUseCase,
            GetCompanyByCnpjUseCase getCompanyByCnpjUseCase,
            GetAllCompaniesUseCase getAllCompaniesUseCase,
            DeleteCompanyUseCase deleteCompanyUseCase,
            AddSupplierUseCase addSupplierUseCase, GetSuppliersUseCase getSuppliersUseCase) {
        this.createCompanyUseCase = createCompanyUseCase;
        this.updateCompanyUseCase = updateCompanyUseCase;
        this.getCompanyByIdUseCase = getCompanyByIdUseCase;
        this.getCompanyByCnpjUseCase = getCompanyByCnpjUseCase;
        this.getAllCompaniesUseCase = getAllCompaniesUseCase;
        this.deleteCompanyUseCase = deleteCompanyUseCase;
        this.addSupplierUseCase = addSupplierUseCase;
        this.getSuppliersUseCase = getSuppliersUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Company createCompany(@RequestBody @Valid AddCompanyRequest request) {
        return createCompanyUseCase.execute(request.toCompanyModel());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Company updateCompany(
            @RequestBody @Valid UpdateCompanyRequest request,
            @PathVariable long id
    ) {
        return updateCompanyUseCase.execute(request.toCompanyModel(), id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Company getCompanyById(@PathVariable long id) throws ElementNotFoundException {
        return getCompanyByIdUseCase.execute(id);
    }

    @GetMapping("/cnpj/{cnpj}")
    @ResponseStatus(HttpStatus.OK)
    public Company getCompanyByCnpj(@PathVariable String cnpj) throws ElementNotFoundException {
        return getCompanyByCnpjUseCase.execute(cnpj);
    }

    @GetMapping("/{id}/suppliers")
    @ResponseStatus(HttpStatus.OK)
    public List<Supplier> getSuppliers(@PathVariable long id){
        return getSuppliersUseCase.execute(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Company> getAllCompanies() {
        return getAllCompaniesUseCase.execute();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCompany(@PathVariable Long id) {
        deleteCompanyUseCase.execute(id);
    }

    @PostMapping("/{companyId}/supplier")
    @ResponseStatus(HttpStatus.OK)
    public void addSupplier(@PathVariable Long companyId, @RequestBody AddSupplierToCompany request) {
        addSupplierUseCase.execute(companyId, request.getSupplierId());
    }
}
