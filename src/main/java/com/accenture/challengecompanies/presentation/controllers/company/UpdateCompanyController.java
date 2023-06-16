package com.accenture.challengecompanies.presentation.controllers.company;

import com.accenture.challengecompanies.application.usecases.company.UpdateCompanyUseCase;
import com.accenture.challengecompanies.domain.models.Company;
import com.accenture.challengecompanies.presentation.dto.UpdateCompanyRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class UpdateCompanyController {

    private final UpdateCompanyUseCase updateCompanyUseCase;

    public UpdateCompanyController(UpdateCompanyUseCase updateCompanyUseCase) {
        this.updateCompanyUseCase = updateCompanyUseCase;
    }

    @PutMapping("/company/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Company updateCompany(@RequestBody @Valid UpdateCompanyRequest request, @PathVariable long id) {
        return this.updateCompanyUseCase.execute(request.toCompanyModel(), id);
    }


}
