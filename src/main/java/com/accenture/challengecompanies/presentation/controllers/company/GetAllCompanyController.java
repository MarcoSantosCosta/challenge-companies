package com.accenture.challengecompanies.presentation.controllers.company;

import com.accenture.challengecompanies.application.usecases.company.GetAllCompaniesUseCase;
import com.accenture.challengecompanies.domain.models.Company;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetAllCompanyController {
    private final GetAllCompaniesUseCase getAllCompaniesUseCase;

    public GetAllCompanyController(GetAllCompaniesUseCase getAllCompaniesUseCase) {
        this.getAllCompaniesUseCase = getAllCompaniesUseCase;
    }

    @GetMapping("/company")
    @ResponseStatus(HttpStatus.OK)
    public List<Company>getAllCompanies() {
        return this.getAllCompaniesUseCase.execute();
    }
}