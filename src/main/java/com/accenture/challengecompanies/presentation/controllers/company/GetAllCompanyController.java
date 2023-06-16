package com.accenture.challengecompanies.presentation.controllers.company;

import com.accenture.challengecompanies.application.usecases.company.GetAllCompanyUseCase;
import com.accenture.challengecompanies.domain.models.Company;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetAllCompanyController {
    private final GetAllCompanyUseCase getAllCompanyUseCase;

    public GetAllCompanyController(GetAllCompanyUseCase getAllCompanyUseCase) {
        this.getAllCompanyUseCase = getAllCompanyUseCase;
    }

    @GetMapping("/company")
    @ResponseStatus(HttpStatus.OK)
    public List<Company>getAllCompanies() {
        return this.getAllCompanyUseCase.execute();
    }
}