package com.accenture.challengecompanies.presentation.controllers.company;

import com.accenture.challengecompanies.domain.models.Company;
import com.accenture.challengecompanies.domain.repositories.CompanyRepositoryInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.accenture.challengecompanies.presentation.Utils.generateDummyCompany;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase
//Integrations tests
class DeleteCompanyIntegrationTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CompanyRepositoryInterface companyRepository;


    @Test
    public void shouldDeleteExistingCompany() throws Exception {
        Company companyExistent = companyRepository.create(generateDummyCompany());
        int initialSize= companyRepository.getAll().size();
        ResultActions result;
        result = mockMvc.perform(MockMvcRequestBuilders.delete("/company/"+companyExistent.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk());
        assertEquals(initialSize -1 , companyRepository.getAll().size());
    }
}