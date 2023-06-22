package com.accenture.challengecompanies.presentation.controllers.company;

import com.accenture.challengecompanies.domain.models.Company;
import com.accenture.challengecompanies.domain.models.Supplier;
import com.accenture.challengecompanies.domain.repositories.CompanyRepositoryInterface;
import com.accenture.challengecompanies.domain.repositories.CrossRepositoryInterface;
import com.accenture.challengecompanies.domain.repositories.SupplierRepositoryInterface;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.hamcrest.Matchers;
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
// Integration tests
class CompanyIntegrationTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CompanyRepositoryInterface companyRepository;

    @Autowired
    private SupplierRepositoryInterface supplierRepository;

    @Autowired
    private CrossRepositoryInterface crossRepository;

    @Test
    public void shouldCreateCompany() throws Exception {
        ObjectNode requestBuilder = generateCompanyDummyJson();
        ObjectNode addressNode = (ObjectNode) requestBuilder.get("address");
        addressNode.put("zipCode", "13561060");
        requestBuilder.set("address", addressNode);
        String requestBody = requestBuilder.toString();

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/company")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());

        String jsonString = result.andReturn().getResponse().getContentAsString();

        JsonNode json = objectMapper.readTree(jsonString);
        long supplierId = json.get("id").asLong();
        assertDoesNotThrow(() -> assertNotNull(companyRepository.getById(supplierId)));
        assertEquals(1, companyRepository.getAll().size());
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
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorType").exists());
        assertEquals(initialSize, companyRepository.getAll().size());
    }

    @Test
    public void shouldReturnDuplicateDocumentError() throws Exception {
        Company companyExisting = generateDummyCompany();
        companyExisting.setCnpj("25817332000194");

        companyRepository.create(companyExisting);

        ObjectNode requestBuilder = generateCompanyDummyJson();
        requestBuilder.put("cnpj", "25817332000194");
        String requestBody = requestBuilder.toString();

        mockMvc.perform(MockMvcRequestBuilders.post("/company")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorType").value("Duplicate document"));

        assertEquals(1, companyRepository.getAll().size());
    }

    @Test
    public void shouldDeleteExistingCompany() throws Exception {
        Company companyExistent = companyRepository.create(generateDummyCompany());
        int initialSize = companyRepository.getAll().size();

        mockMvc.perform(MockMvcRequestBuilders.delete("/company/" + companyExistent.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk());

        assertEquals(initialSize - 1, companyRepository.getAll().size());
    }

    @Test
    public void shouldListAllExistingCompanies() throws Exception {
        int initialSize = companyRepository.getAll().size();
        Company company1 = generateDummyCompany();
        Company company2 = generateDummyCompany();
        company1.setCnpj("08935025000199");
        company2.setCnpj("25817332000194");
        companyRepository.create(company1);
        companyRepository.create(company2);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/company"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        String jsonString = result.andReturn().getResponse().getContentAsString();
        JsonNode json = objectMapper.readTree(jsonString);
        assertEquals(initialSize + 2, json.size());
    }

    @Test
    public void shouldGetCompanyById() throws Exception {
        Company existentCompany = companyRepository.create(generateDummyCompany());

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/company/" + existentCompany.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk());

        String responseJson = result.andReturn().getResponse().getContentAsString();
        String companyJson = objectMapper.writeValueAsString(existentCompany);
        assertEquals(responseJson, companyJson);
    }

    @Test
    public void shouldUpdateAddressCompany() throws Exception {
        Company existentCompany = companyRepository.create(generateDummyCompany());


        existentCompany.getAddress().setStreet("Rua Suriname");
        existentCompany.getAddress().setNumber(92);
        existentCompany.getAddress().setNeighborhood("Bairro Novo Mundo");
        existentCompany.getAddress().setCity("Passos");
        existentCompany.getAddress().setState("MG");
        existentCompany.getAddress().setZipCode("37901082");


        String requestBody = generateCompanyDummyJson(existentCompany).toString();

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.put(
                                String.format("/company/%s", existentCompany.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());

        String jsonString = result.andReturn().getResponse().getContentAsString();
        JsonNode json = objectMapper.readTree(jsonString);
        long supplierId = json.get("id").asLong();
        assertDoesNotThrow(() -> {
            Company updatedCompany = companyRepository.getById(supplierId);
            assertNotNull(updatedCompany);
            assertEquals(existentCompany.getAddress().getStreet(),
                    updatedCompany.getAddress().getStreet());
            assertEquals(existentCompany.getAddress().getNumber(),
                    updatedCompany.getAddress().getNumber());
            assertEquals(existentCompany.getAddress().getNeighborhood(),
                    updatedCompany.getAddress().getNeighborhood());
            assertEquals(existentCompany.getAddress().getCity(),
                    updatedCompany.getAddress().getCity());
            assertEquals(existentCompany.getAddress().getState(),
                    updatedCompany.getAddress().getState());
            assertEquals(existentCompany.getAddress().getZipCode(),
                    updatedCompany.getAddress().getZipCode());
        });
        assertEquals(1, companyRepository.getAll().size());
    }

    @Test
    public void shouldReturnSuppliers() throws Exception {
        Company company = companyRepository.create(generateDummyCompany());

        Supplier supplier1 = generateDummySupplier();
        supplier1.setDocument("07.994.131/0001-80");

        Supplier supplier2 = generateDummySupplier();
        supplier2.setDocument("98.154.401/0001-09");

        supplier1.setId(supplierRepository.create((supplier1)).getId());

        supplier2.setId(supplierRepository.create((supplier2)).getId());

        crossRepository.addSupplier(company.getId(), supplier1.getId());
        crossRepository.addSupplier(company.getId(), supplier2.getId());

        assertEquals(2, companyRepository.getSuppliers(company.getId()).size());


        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get(
                                String.format("/company/%s/suppliers", company.getId()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.equalTo((int) supplier1.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.equalTo((int) supplier2.getId())));

    }

}
