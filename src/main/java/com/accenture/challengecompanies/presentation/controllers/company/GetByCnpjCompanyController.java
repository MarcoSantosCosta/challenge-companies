package com.accenture.challengecompanies.presentation.controllers.company;

import com.accenture.challengecompanies.application.usecases.company.GetCompanyByCnpjUseCase;
import com.accenture.challengecompanies.application.usecases.company.GetCompanyByIdUseCase;
import com.accenture.challengecompanies.domain.exceptions.ElementNotFoundException;
import com.accenture.challengecompanies.domain.models.Company;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetByCnpjCompanyController {
    private final GetCompanyByCnpjUseCase getCompanyByCnpjUseCase;

    public GetByCnpjCompanyController(GetCompanyByCnpjUseCase getCompanyByCnpjUseCase) {
        this.getCompanyByCnpjUseCase = getCompanyByCnpjUseCase;
    }

    @GetMapping("/company/cnpj/{cnpj}")
    @ResponseStatus(HttpStatus.OK)
    public Company getCompanyByCnpj(@PathVariable String cnpj) throws ElementNotFoundException {
        return this.getCompanyByCnpjUseCase.execute(cnpj);
    }
}