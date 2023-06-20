package com.accenture.challengecompanies.application.usecases.company;

import com.accenture.challengecompanies.domain.models.Supplier;
import com.accenture.challengecompanies.domain.repositories.CompanyRepositoryInterface;
import org.springframework.stereotype.Service;

import java.util.List;

//This @Service annotation is intentionally breaking the Clean Architecture principles in order to reduce code verbosity.
//@Service annotation provide an automatic dependency injection for companyRepository
@Service
public class GetSuppliersUseCase {

    private final CompanyRepositoryInterface companyRepository;

    public GetSuppliersUseCase(CompanyRepositoryInterface companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Supplier> execute(long companyId) {
        return companyRepository.getSuppliers(companyId);
    }
}
