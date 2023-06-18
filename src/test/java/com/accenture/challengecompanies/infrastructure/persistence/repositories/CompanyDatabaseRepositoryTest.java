package com.accenture.challengecompanies.infrastructure.persistence.repositories;

import com.accenture.challengecompanies.domain.exceptions.ElementNotFoundException;
import com.accenture.challengecompanies.domain.models.Company;
import com.accenture.challengecompanies.infrastructure.persistence.mappings.company.CompanyMapping;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
    private CompanyDatabaseRepository companyRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createValidCompanyReturnsCreatedCompany() {

        Company company = generateDummyCompany();
        CompanyMapping companyMapping = new CompanyMapping(company);
        when(companyDataBaseRepository.save(companyMapping)).thenReturn(companyMapping);

        Company createdCompany = companyRepository.create(company);
        company.setId(createdCompany.getId());
        assertNotNull(createdCompany);

        assertEquals(company.getId(), createdCompany.getId());
        verify(companyDataBaseRepository, times(1)).save(companyMapping);
    }

    @Test
    void shouldReturnCompanyWhenCompanyWhenPassExistingId() throws ElementNotFoundException {

        long companyId = 1;
        CompanyMapping companyMapping = new CompanyMapping(generateDummyCompany());
        when(companyDataBaseRepository.findById(companyId)).thenReturn(Optional.of(companyMapping));

        Company retrievedCompany = companyRepository.getById(companyId);

        assertNotNull(retrievedCompany);
        verify(companyDataBaseRepository, times(1)).findById(companyId);
    }

    @Test
    void shouldThrowsExceptionWhenPassNonExistingID() {

        long companyId = 1;
        when(companyDataBaseRepository.findById(companyId)).thenReturn(Optional.empty());

        assertThrows(ElementNotFoundException.class, () -> companyRepository.getById(companyId));
        verify(companyDataBaseRepository, times(1)).findById(companyId);
    }

    @Test
    void shouldReturnCompanyWhenCompanyWhenPassExistingCNPJ() throws ElementNotFoundException {

        String cnpj = "123456789";
        Company company = generateDummyCompany();
        company.setCnpj(cnpj);

        CompanyMapping companyMapping = new CompanyMapping(company);
        when(companyDataBaseRepository.findByCnpj(cnpj)).thenReturn(Optional.of(companyMapping));

        Company retrievedCompany = companyRepository.getByCnpj(cnpj);

        assertNotNull(retrievedCompany);
        verify(companyDataBaseRepository, times(1)).findByCnpj(cnpj);
    }

    @Test
    void shouldThrowsExceptionWhenPassNonExistingCNPJ() {

        String cnpj = "123456789";
        when(companyDataBaseRepository.findByCnpj(cnpj)).thenReturn(Optional.empty());

        assertThrows(ElementNotFoundException.class, () -> companyRepository.getByCnpj(cnpj));
        verify(companyDataBaseRepository, times(1)).findByCnpj(cnpj);
    }

    @Test
    void shouldReturnAllCompaniesExistents() {

        List<CompanyMapping> companyMappings = new ArrayList<>();
        companyMappings.add(new CompanyMapping(generateDummyCompany()));
        companyMappings.add(new CompanyMapping(generateDummyCompany()));
        when(companyDataBaseRepository.findAll(Sort.by(Sort.Direction.ASC, "id"))).thenReturn(companyMappings);


        List<Company> companies = companyRepository.getAll();


        assertNotNull(companies);
        assertEquals(companyMappings.size(), companies.size());
        verify(companyDataBaseRepository, times(1)).findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Test
    void shouldDeleteCompanyById() {

        long companyId = 1;

        companyRepository.delete(companyId);

        verify(companyDataBaseRepository, times(1)).deleteById(companyId);
    }

    @Test
    public void shouldUpadteCompanyByExistingId() {

        long companyId = 1;

        CompanyMapping existingCompany = new CompanyMapping(generateDummyCompany());
        existingCompany.setId(companyId);

        Company updatedCompany = existingCompany.toModel();
        updatedCompany.setTradeName("New Dummy Company");

        //Prepare Mock CompanydatabaseRepository
        when(companyDataBaseRepository.findById(companyId)).thenReturn(Optional.of(existingCompany));
        when(companyDataBaseRepository.saveAndFlush(Mockito.any(CompanyMapping.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));


        Company retunedCompany = companyRepository.update(updatedCompany);

        verify(companyDataBaseRepository, times(1)).saveAndFlush(existingCompany);
        verify(companyDataBaseRepository, times(1)).findById(updatedCompany.getId());

        assertEquals(updatedCompany.getId(), retunedCompany.getId());
        assertEquals(updatedCompany.getCnpj(), retunedCompany.getCnpj());
        assertEquals(updatedCompany.getTradeName(), retunedCompany.getTradeName());
        assertEquals(updatedCompany.getAddress(), retunedCompany.getAddress());
        assertEquals(updatedCompany.getSuppliers(), retunedCompany.getSuppliers());

    }


    @Test
    public void shouldThrowsExpectionWhenUpadteNonExistentCompanyId() {

        long companyId = 1;
        Company updatedCompany = generateDummyCompany();
        updatedCompany.setId(companyId);
        when(companyDataBaseRepository.findById(companyId)).thenReturn(Optional.empty());

        assertThrows(ElementNotFoundException.class, () -> companyRepository.update(updatedCompany));
        verify(companyDataBaseRepository, times(0)).save(new CompanyMapping(updatedCompany));

    }

}