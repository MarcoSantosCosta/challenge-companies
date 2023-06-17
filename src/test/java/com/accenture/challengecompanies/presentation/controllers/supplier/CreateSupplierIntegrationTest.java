package com.accenture.challengecompanies.presentation.controllers.supplier;

import com.accenture.challengecompanies.domain.repositories.SupplierRepositoryInterface;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.accenture.challengecompanies.presentation.Utils.generateSupplierDummyJson;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
//Integrations tests
class CreateSupplierIntegrationTest {


    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private SupplierRepositoryInterface supplierRepository;


    @Test
    public void shouldCreateSupplier() throws Exception {
        String requestBody = generateSupplierDummyJson().toString();
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
        assertEquals(initialSize +1, supplierRepository.getAll().size());
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
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").exists());
        assertEquals(initialSize, supplierRepository.getAll().size());

    }
}