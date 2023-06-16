package com.accenture.challengecompanies.application.usecases.company;

import com.accenture.challengecompanies.domain.repositories.CompanyRepositoryInterface;
import org.springframework.stereotype.Service;


//This @Service annotation is intentionally breaking the Clean Architecture principles in order to reduce code verbosity.
//@Service annotation provide an automatic dependency injection for companyRepository
@Service
public class DeleteCompanyUseCase {

    private final CompanyRepositoryInterface companyRepository;

    public DeleteCompanyUseCase(CompanyRepositoryInterface companyRepository) {
        this.companyRepository = companyRepository;
    }

    public void execute(Long id) {
        companyRepository.delete(id);
    }

}
