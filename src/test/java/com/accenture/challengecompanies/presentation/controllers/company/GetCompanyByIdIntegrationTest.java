package com.accenture.challengecompanies.presentation.controllers.company;

import com.accenture.challengecompanies.domain.models.Company;
import com.accenture.challengecompanies.domain.repositories.CompanyRepositoryInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.accenture.challengecompanies.presentation.Utils.generateDummyCompany;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@AutoConfigureMockMvc
//Integration tests
class GetCompanyByIdIntegrationTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CompanyRepositoryInterface companyRepository;


    @Test
    public void shouldListAllExistingCompanies() throws Exception {
        Company existentCompany = companyRepository.create(generateDummyCompany());

        ResultActions result;
        result = mockMvc.perform(MockMvcRequestBuilders.get("/company/" + existentCompany.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk());

        String responseJson = result.andReturn().getResponse().getContentAsString();
        String companyJson = objectMapper.writeValueAsString(existentCompany);
        assertEquals(responseJson, companyJson);
    }
}