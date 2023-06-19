package com.accenture.challengecompanies.infrastructure.persistence.repositories;

import com.accenture.challengecompanies.domain.enums.DocumentType;
import com.accenture.challengecompanies.domain.exceptions.ElementNotFoundException;
import com.accenture.challengecompanies.domain.models.Company;
import com.accenture.challengecompanies.domain.models.Supplier;
import com.accenture.challengecompanies.domain.repositories.CompanyRepositoryInterface;
import com.accenture.challengecompanies.infrastructure.persistence.mappings.company.CompanyMapping;
import com.accenture.challengecompanies.infrastructure.persistence.mappings.supplier.SupplierMapping;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
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

    private CompanyMapping internalGetById(long id) throws ElementNotFoundException {
        return companyDataBaseRepository.findById(id).orElseThrow(
                () -> new ElementNotFoundException("Id " + id + " não encontrado"));
    }

    @Override
    public Company getById(long id) throws ElementNotFoundException {
        var company = this.internalGetById(id);
        return company.toModel();
    }


    @Override
    public Company findByCnpj(String cnpj) {
        return companyDataBaseRepository.findByCnpj(cnpj)
                .map(CompanyMapping::toModel)
                .orElse(null);
    }


    @Override
    public List<Company> getAll() {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        return companyDataBaseRepository.findAll(sort).stream().map(CompanyMapping::toModel).collect(Collectors.toList());
    }

    @Override
    public void delete(long id) {
        companyDataBaseRepository.deleteById(id);
    }

    @Override
    public Company update(Company company) throws ElementNotFoundException {
        var companyStored = this.internalGetById(company.getId());
        BeanUtils.copyProperties(company, companyStored, "id", "cnpj");

        companyDataBaseRepository.saveAndFlush(companyStored);
        return companyStored.toModel();
    }

}
