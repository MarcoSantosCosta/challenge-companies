package com.accenture.challengecompanies.infrastructure.persistence.repositories;

import com.accenture.challengecompanies.domain.exceptions.ElementNotFoundException;
import com.accenture.challengecompanies.domain.models.Address;
import com.accenture.challengecompanies.domain.models.Company;
import com.accenture.challengecompanies.infrastructure.persistence.mappings.company.CompanyMapping;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.accenture.challengecompanies.presentation.Utils.generateDummyCompany;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CompanyDatabaseRepositoryTest {

    @Mock
    private CompanyDataBaseRepositoryInterface companyDataBaseRepository;

    @InjectMocks
    private CompanyDatabaseRepository companyDatabaseRepository;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createValidCompanyReturnsCreatedCompany() {

        Company company = generateDummyCompany();
        CompanyMapping companyMapping = new CompanyMapping(company);
        when(companyDataBaseRepository.save(companyMapping)).thenReturn(companyMapping);

        Company createdCompany = companyDatabaseRepository.create(company);

        assertNotNull(createdCompany);
        assertEquals(company, createdCompany);
        verify(companyDataBaseRepository, times(1)).save(companyMapping);
    }

    @Test
    void shouldReturnCompanyWhenCompanyWhenPassExistingId() throws ElementNotFoundException {

        long companyId = 1;
        CompanyMapping companyMapping = new CompanyMapping(generateDummyCompany());
        when(companyDataBaseRepository.findById(companyId)).thenReturn(Optional.of(companyMapping));

        Company retrievedCompany = companyDatabaseRepository.getById(companyId);

        assertNotNull(retrievedCompany);
        verify(companyDataBaseRepository, times(1)).findById(companyId);
    }

    @Test
    void shouldThrowsExceptionWhenPassNonExistingID() {

        long companyId = 1;
        when(companyDataBaseRepository.findById(companyId)).thenReturn(Optional.empty());

        assertThrows(ElementNotFoundException.class, () -> companyDatabaseRepository.getById(companyId));
        verify(companyDataBaseRepository, times(1)).findById(companyId);
    }

    @Test
    void shouldReturnCompanyWhenCompanyWhenPassExistingCNPJ() throws ElementNotFoundException {

        String cnpj = "123456789";
        Company company = generateDummyCompany();
        company.setCnpj(cnpj);

        CompanyMapping companyMapping = new CompanyMapping(company);
        when(companyDataBaseRepository.findByCnpj(cnpj)).thenReturn(Optional.of(companyMapping));

        Company retrievedCompany = companyDatabaseRepository.getByCnpj(cnpj);

        assertNotNull(retrievedCompany);
        verify(companyDataBaseRepository, times(1)).findByCnpj(cnpj);
    }

    @Test
    void shouldThrowsExceptionWhenPassNonExistingCNPJ() {

        String cnpj = "123456789";
        when(companyDataBaseRepository.findByCnpj(cnpj)).thenReturn(Optional.empty());

        assertThrows(ElementNotFoundException.class, () -> companyDatabaseRepository.getByCnpj(cnpj));
        verify(companyDataBaseRepository, times(1)).findByCnpj(cnpj);
    }

    @Test
    void shouldReturnAllCompaniesExistents() {

        List<CompanyMapping> companyMappings = new ArrayList<>();
        companyMappings.add(new CompanyMapping(generateDummyCompany()));
        companyMappings.add(new CompanyMapping(generateDummyCompany()));
        when(companyDataBaseRepository.findAll(Sort.by(Sort.Direction.ASC, "id"))).thenReturn(companyMappings);


        List<Company> companies = companyDatabaseRepository.getAll();


        assertNotNull(companies);
        assertEquals(companyMappings.size(), companies.size());
        verify(companyDataBaseRepository, times(1)).findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Test
    void shouldDeleteCompanyById() {

        long companyId = 1;

        companyDatabaseRepository.delete(companyId);

        verify(companyDataBaseRepository, times(1)).deleteById(companyId);
    }

    @Test
    public void shouldUpadteCompanyByExistingId() {

        Company updatedCompany = generateDummyCompany();
        updatedCompany.setId(1L);
        updatedCompany.setTradeName("New Dummy Company");

        CompanyMapping existingCompany = new CompanyMapping(generateDummyCompany());

        long companyId = 1;
        when(companyDataBaseRepository.findById(companyId)).thenReturn(Optional.of(existingCompany));

        Company result = companyDatabaseRepository.update(updatedCompany);

        verify(companyDataBaseRepository, times(1)).saveAndFlush(existingCompany);

        assertEquals(updatedCompany.getCnpj(), existingCompany.getCnpj());
        assertEquals(updatedCompany.getTradeName(), existingCompany.getTradeName());

        //TODO
        //Create test to verify address update
    }


    @Test
    public void shouldThrowsExpectionWhenUpadteNonExistentCompanyId() {

        long companyId = 1;
        Company updatedCompany = generateDummyCompany();
        updatedCompany.setId(companyId);
        when(companyDataBaseRepository.findById(companyId)).thenReturn(Optional.empty());

        assertThrows(ElementNotFoundException.class, () -> companyDatabaseRepository.update(updatedCompany));
        verify(companyDataBaseRepository, times(0)).save(new CompanyMapping(updatedCompany));

    }

}