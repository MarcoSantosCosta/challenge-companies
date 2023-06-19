package com.accenture.challengecompanies.presentation.controllers.company;

import com.accenture.challengecompanies.domain.models.Company;
import com.accenture.challengecompanies.domain.repositories.CompanyRepositoryInterface;
import com.fasterxml.jackson.databind.JsonNode;
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
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase
//Integrations tests
class GetAllCompanyIntegrationTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CompanyRepositoryInterface companyRepository;


    @Test
    public void shouldListAllExistingCompanies() throws Exception {
        int initialSize = companyRepository.getAll().size();
        Company company1 = generateDummyCompany();
        Company company2 = generateDummyCompany();
        company1.setCnpj("08935025000199");
        company1.setCnpj("25817332000194");
        companyRepository.create(company1);
        companyRepository.create(company2);

        ResultActions result;
        result = mockMvc.perform(MockMvcRequestBuilders.get("/company"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        String jsonString = result.andReturn().getResponse().getContentAsString();
        JsonNode json = objectMapper.readTree(jsonString);
        assertEquals(initialSize + 2, json.size());
    }
}