package com.accenture.challengecompanies.application.usecases.company;

import com.accenture.challengecompanies.application.usecases.address.ValidateCepUseCase;
import com.accenture.challengecompanies.domain.exceptions.ElementNotFoundException;
import com.accenture.challengecompanies.domain.models.Company;
import com.accenture.challengecompanies.domain.repositories.CompanyRepositoryInterface;
import org.springframework.stereotype.Service;


//This @Service annotation is intentionally breaking the Clean Architecture principles in order to reduce code verbosity.
//@Service annotation provide an automatic dependency injection for companyRepository
@Service
public class UpdateCompanyUseCase {

    private final CompanyRepositoryInterface companyRepository;
    private final ValidateCepUseCase validateCepUseCase;

    public UpdateCompanyUseCase(CompanyRepositoryInterface companyRepository,
                                ValidateCepUseCase validateCepUseCase) {

        this.companyRepository = companyRepository;
        this.validateCepUseCase = validateCepUseCase;
    }

    public Company execute(Company company, long id) throws ElementNotFoundException {
        company.setId(id);
        validateCepUseCase.execute(company.getAddress().getZipCode());
        return companyRepository.update(company);
    }

}
