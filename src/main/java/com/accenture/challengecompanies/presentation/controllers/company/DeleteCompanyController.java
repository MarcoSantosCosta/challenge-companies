package com.accenture.challengecompanies.presentation.controllers.company;

import com.accenture.challengecompanies.application.usecases.company.DeleteCompanyUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteCompanyController {

    private final DeleteCompanyUseCase deleteCompanyUseCase;

    public DeleteCompanyController(DeleteCompanyUseCase deleteCompanyUseCase) {
        this.deleteCompanyUseCase = deleteCompanyUseCase;
    }

    @DeleteMapping("/company/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCompany(@PathVariable Long id) {
        this.deleteCompanyUseCase.execute(id);
    }

}
