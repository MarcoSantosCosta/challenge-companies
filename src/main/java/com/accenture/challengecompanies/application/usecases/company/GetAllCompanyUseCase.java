package com.accenture.challengecompanies.application.usecases.company;

import com.accenture.challengecompanies.domain.models.Company;
import com.accenture.challengecompanies.domain.repositories.CompanyRepositoryInterface;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//This @Service annotation is intentionally breaking the Clean Architecture principles in order to reduce code verbosity.
//@Service annotation provide an automatic dependency injection for companyRepository
@Service
public class GetAllCompanyUseCase {

    private final CompanyRepositoryInterface companyRepository;

    public GetAllCompanyUseCase(CompanyRepositoryInterface companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> execute() {

        return companyRepository.getAll();

    }
}
