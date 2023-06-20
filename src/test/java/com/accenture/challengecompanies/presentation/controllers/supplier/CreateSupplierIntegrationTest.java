package com.accenture.challengecompanies.presentation.controllers.supplier;

import com.accenture.challengecompanies.domain.enums.DocumentType;
import com.accenture.challengecompanies.domain.models.Supplier;
import com.accenture.challengecompanies.domain.repositories.SupplierRepositoryInterface;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
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

import static com.accenture.challengecompanies.presentation.Utils.generateDummySupplier;
import static com.accenture.challengecompanies.presentation.Utils.generateSupplierDummyJson;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase
//Integrations tests
class CreateSupplierIntegrationTest {


    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private SupplierRepositoryInterface supplierRepository;

    @Test
    public void shouldCreateSupplier() throws Exception {

        ObjectNode requestBuilder = generateSupplierDummyJson();
        ObjectNode addressNode = (ObjectNode) requestBuilder.get("address");
        addressNode.put("zipCode", "13561060");
        requestBuilder.set("address", addressNode);
        String requestBody = requestBuilder.toString();

        int initialSize = supplierRepository.getAll().size();
        ResultActions result;
        result = mockMvc.perform(MockMvcRequestBuilders.post("/supplier")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());

        String jsonString = result.andReturn().getResponse().getContentAsString();

        JsonNode json = objectMapper.readTree(jsonString);
        long supplierId = json.get("id").asLong();
        assertDoesNotThrow(() -> assertNotNull(supplierRepository.getById(supplierId)));
        assertEquals(initialSize + 1, supplierRepository.getAll().size());
    }

    @Test
    public void shouldReturnInvalidFieldsErrors() throws Exception {
        ObjectNode requestBuilder = generateSupplierDummyJson();
        requestBuilder.remove("document");
        String requestBody = requestBuilder.toString();
        int initialSize = supplierRepository.getAll().size();
        mockMvc.perform(MockMvcRequestBuilders.post("/supplier")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorType").exists());
        assertEquals(initialSize, supplierRepository.getAll().size());

    }

    @Test
    public void
    shouldReturnDuplicateDocumentError() throws Exception {
        Supplier supplierExistent = generateDummySupplier();
        supplierExistent.setDocument("25817332000194");

        supplierRepository.create(supplierExistent);

        ObjectNode requestBuilder = generateSupplierDummyJson();
        requestBuilder.put("document", "25817332000194");
        String requestBody = requestBuilder.toString();
        int initialSize = supplierRepository.getAll().size();

        mockMvc.perform(MockMvcRequestBuilders.post("/supplier")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorType").value("Duplicate document"));

        assertEquals(initialSize, supplierRepository.getAll().size());

    }

    @Test
    public void shouldCreateIndividualSupplier() throws Exception {

        ObjectNode requestBuilder = generateSupplierDummyJson();
        ObjectNode addressNode = (ObjectNode) requestBuilder.get("address");
        addressNode.put("zipCode", "13561060");
        requestBuilder.put("birthDate", "1997-03-04");
        requestBuilder.put("rg", "114666090");
        requestBuilder.set("address", addressNode);
        String requestBody = requestBuilder.toString();
        ResultActions result;
        result = mockMvc.perform(MockMvcRequestBuilders.post("/supplier")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());

        String jsonString = result.andReturn().getResponse().getContentAsString();

        JsonNode json = objectMapper.readTree(jsonString);
        long supplierId = json.get("id").asLong();
        assertDoesNotThrow(() -> {
                    Supplier createdSupplier = supplierRepository.getById(supplierId);
                    assertNotNull(createdSupplier);
                    assertEquals(requestBuilder.get("rg").asText(), createdSupplier.getRg());
                }

        );
    }

    @Test
    public void sholdThrowsExepctWhenNotProvideRgToIndividualSupplier() throws Exception {
        ObjectNode requestBuilder = generateSupplierDummyJson();
        requestBuilder.put("documentType", DocumentType.CPF.toString());
        requestBuilder.put("birthDate", "1997-03-04");
        String requestBody = requestBuilder.toString();
        int initialSize = supplierRepository.getAll().size();

        mockMvc.perform(MockMvcRequestBuilders.post("/supplier")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorType").value("Invalid field(s)"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorMessages[0].field").value("rg"));
        ;

        assertEquals(initialSize, supplierRepository.getAll().size());
    }

    @Test
    public void sholdThrowsExepctWhenNotProvideBirthDateToIndividualSupplier() throws Exception {
        ObjectNode requestBuilder = generateSupplierDummyJson();
        requestBuilder.put("documentType", DocumentType.CPF.toString());
        requestBuilder.put("rg", "114666090");
        String requestBody = requestBuilder.toString();
        int initialSize = supplierRepository.getAll().size();

        mockMvc.perform(MockMvcRequestBuilders.post("/supplier")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorType").value("Invalid field(s)"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorMessages[0].field").value("birthDate"));
        ;

        assertEquals(initialSize, supplierRepository.getAll().size());

    }

    @Test
    public void sholdThrowsExepctAgeRestrictionParana() throws Exception {
        ObjectNode requestBuilder = generateSupplierDummyJson();
        requestBuilder.put("documentType", DocumentType.CPF.toString());
        requestBuilder.put("rg", "114666090");
        requestBuilder.put("birthDate", "1997-03-04");
        ObjectNode addressNode = (ObjectNode) requestBuilder.get("address");
        addressNode.put("state", "PR");
        requestBuilder.put("birthDate", "2010-03-04");
        requestBuilder.put("rg", "114666090");
        requestBuilder.set("address", addressNode);

        String requestBody = requestBuilder.toString();
        int initialSize = supplierRepository.getAll().size();

        mockMvc.perform(MockMvcRequestBuilders.post("/supplier")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorType").value("state age restriction"));


        assertEquals(initialSize, supplierRepository.getAll().size());

    }
}