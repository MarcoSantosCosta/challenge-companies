package com.accenture.challengecompanies.infrastructure.persistence.repositories;

import com.accenture.challengecompanies.domain.exceptions.ElementNotFoundException;
import com.accenture.challengecompanies.domain.models.Company;
import com.accenture.challengecompanies.domain.repositories.CompanyRepositoryInterface;
import com.accenture.challengecompanies.infrastructure.persistence.mappings.company.CompanyMapping;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CompanyDatabaseRepository implements CompanyRepositoryInterface {

    private final CompanyDataBaseRepositoryInterface companyDataBaseRepository;

    public CompanyDatabaseRepository(CompanyDataBaseRepositoryInterface companyDataBaseRepository) {
        this.companyDataBaseRepository = companyDataBaseRepository;
    }

    @Override
    public Company create(Company company) {
        return companyDataBaseRepository.save(new CompanyMapping(company)).toModel();
    }

    @Override
    public Company getById(long id) throws ElementNotFoundException {
        var company = companyDataBaseRepository.findById(id).orElseThrow(() -> new ElementNotFoundException("Id " + id + " não encontrado"));
        return company.toModel();
    }

    @Override
    public Company getByCnpj(String cnpj) throws ElementNotFoundException {
        var company = companyDataBaseRepository.findByCnpj(cnpj).orElseThrow(() -> new ElementNotFoundException("CNPJ: " + cnpj + " não encontrado"));
        return company.toModel();
    }

    @Override
    public List<Company> getAll() {
        return companyDataBaseRepository.findAll().stream().map(CompanyMapping::toModel).collect(Collectors.toList());
    }

    @Override
    public void delete(long id) {
        companyDataBaseRepository.deleteById(id);
    }
}
