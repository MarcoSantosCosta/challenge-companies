package com.accenture.challengecompanies.presentation.controllers.company;

import com.accenture.challengecompanies.application.usecases.company.GetCompanyByIdUseCase;
import com.accenture.challengecompanies.domain.exceptions.ElementNotFoundException;
import com.accenture.challengecompanies.domain.models.Company;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetByIdCompanyController {
    private final GetCompanyByIdUseCase getCompanyByIdUseCase;

    public GetByIdCompanyController(GetCompanyByIdUseCase getCompanyByIdUseCase) {
        this.getCompanyByIdUseCase = getCompanyByIdUseCase;
    }

    @GetMapping("/company/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Company getCompanyById(@PathVariable long id) throws ElementNotFoundException {
        return this.getCompanyByIdUseCase.execute(id);
    }
}