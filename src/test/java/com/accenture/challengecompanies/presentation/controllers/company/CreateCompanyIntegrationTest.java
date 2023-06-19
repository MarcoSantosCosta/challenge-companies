package com.accenture.challengecompanies.presentation.controllers.company;

import com.accenture.challengecompanies.domain.models.Company;
import com.accenture.challengecompanies.domain.models.Supplier;
import com.accenture.challengecompanies.domain.repositories.CompanyRepositoryInterface;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.accenture.challengecompanies.presentation.Utils.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase
//Integrations tests
class CreateCompanyIntegrationTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CompanyRepositoryInterface companyRepository;

    @Test
    public void  shouldCreateCompany() throws Exception {
        String requestBody = generateCompanyDummyJson().toString();
        int initialSize = companyRepository.getAll().size();
        ResultActions result;
        result = mockMvc.perform(MockMvcRequestBuilders.post("/company")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());

        String jsonString = result.andReturn().getResponse().getContentAsString();

        JsonNode json = objectMapper.readTree(jsonString);
        long supplierId = json.get("id").asLong();
        assertDoesNotThrow(() -> assertNotNull(companyRepository.getById(supplierId)));
        assertEquals(initialSize +1,companyRepository.getAll().size());
    }

    @Test
    public void shouldReturnInvalidFieldsErrors() throws Exception {
        ObjectNode requestBuilder = generateCompanyDummyJson();
        requestBuilder.remove("cnpj");
        String requestBody = requestBuilder.toString();
        int initialSize = companyRepository.getAll().size();
        mockMvc.perform(MockMvcRequestBuilders.post("/company")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").exists());
        assertEquals(initialSize,companyRepository.getAll().size() );
    }

    @Test
    public void
    shouldReturnDuplicateDocumentError() throws  Exception{
        Company companyExisting = generateDummyCompany();
        companyExisting.setCnpj("25817332000194");

        companyRepository.create(companyExisting);

        ObjectNode requestBuilder = generateCompanyDummyJson();
        requestBuilder.put("cnpj","25817332000194");
        String requestBody = requestBuilder.toString();

        mockMvc.perform(MockMvcRequestBuilders.post("/company")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Duplicate document"));

        assertEquals(1, companyRepository.getAll().size());

    } 

}