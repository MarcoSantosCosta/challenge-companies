package com.accenture.challengecompanies.presentation.controllers.company;

import com.accenture.challengecompanies.application.usecases.company.CreateCompanyUseCase;
import com.accenture.challengecompanies.domain.models.Company;
import com.accenture.challengecompanies.presentation.dto.AddCompanyRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateCompanyController {

    private final CreateCompanyUseCase createCompanyUseCase;

    public CreateCompanyController(CreateCompanyUseCase createCompanyUseCase) {
        this.createCompanyUseCase = createCompanyUseCase;
    }

    @PostMapping("/company")
    @ResponseStatus(HttpStatus.CREATED)
    public Company createCompany(@RequestBody @Valid AddCompanyRequest request) {

        return this.createCompanyUseCase.execute(request.toCompanyModel());
    }


}
