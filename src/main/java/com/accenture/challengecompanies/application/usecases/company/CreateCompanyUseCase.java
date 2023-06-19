package com.accenture.challengecompanies.application.usecases.company;

import com.accenture.challengecompanies.domain.exceptions.DuplicateDocumentException;
import com.accenture.challengecompanies.domain.models.Company;
import com.accenture.challengecompanies.domain.repositories.CompanyRepositoryInterface;
import org.springframework.stereotype.Service;


//This @Service annotation is intentionally breaking the Clean Architecture principles in order to reduce code verbosity.
//@Service annotation provide an automatic dependency injection for companyRepository
@Service
public class CreateCompanyUseCase {

    private final CompanyRepositoryInterface companyRepository;

    public CreateCompanyUseCase(CompanyRepositoryInterface companyRepository) {

        this.companyRepository = companyRepository;
    }

    public Company execute(Company company) {
        if (companyRepository.findByCnpj(company.getCnpj()) != null){
            throw new DuplicateDocumentException(
                    String.format("O, CNPJ %s. já está em uso", company.getCnpj()));
        }
        return companyRepository.create(company);
    }

}
