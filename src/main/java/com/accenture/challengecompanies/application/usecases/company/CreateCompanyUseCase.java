package com.accenture.challengecompanies.application.usecases.company;

import com.accenture.challengecompanies.application.usecases.address.UpdateAddressUseCase;
import com.accenture.challengecompanies.application.usecases.address.ValidateCepUseCase;
import com.accenture.challengecompanies.domain.exceptions.DuplicateDocumentException;
import com.accenture.challengecompanies.domain.models.Address;
import com.accenture.challengecompanies.domain.models.Company;
import com.accenture.challengecompanies.domain.repositories.CompanyRepositoryInterface;
import org.springframework.stereotype.Service;


//This @Service annotation is intentionally breaking the Clean Architecture principles in order to reduce code verbosity.
//@Service annotation provide an automatic dependency injection for companyRepository
@Service
public class CreateCompanyUseCase {

    private final CompanyRepositoryInterface companyRepository;
    private final UpdateAddressUseCase updateAddressUseCase;

    public CreateCompanyUseCase(CompanyRepositoryInterface companyRepository, UpdateAddressUseCase updateAddressUseCase) {

        this.companyRepository = companyRepository;
        this.updateAddressUseCase = updateAddressUseCase;
    }

    public Company execute(Company company) {
        if (companyRepository.findByCnpj(company.getCnpj()) != null){
            throw new DuplicateDocumentException(
                    String.format("O, CNPJ %s. já está em uso", company.getCnpj()));
        }
        Address addressCreated = updateAddressUseCase.execute(company.getAddress());
        company.setAddress(addressCreated);
        return companyRepository.create(company);
    }

}
