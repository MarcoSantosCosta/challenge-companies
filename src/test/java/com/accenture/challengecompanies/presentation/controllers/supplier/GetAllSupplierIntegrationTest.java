package com.accenture.challengecompanies.presentation.controllers.supplier;

import com.accenture.challengecompanies.domain.repositories.SupplierRepositoryInterface;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.accenture.challengecompanies.presentation.Utils.generateDummySupplier;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
class GetAllSupplierIntegrationTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SupplierRepositoryInterface supplierRepository;

    @Test
    public void shouldListAllExistingSuppliers() throws Exception {
        int initialSize = supplierRepository.getAll().size();
        supplierRepository.create(generateDummySupplier());
        supplierRepository.create(generateDummySupplier());

        ResultActions result;
        result = mockMvc.perform(MockMvcRequestBuilders.get("/supplier"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        String jsonString = result.andReturn().getResponse().getContentAsString();
        JsonNode json = objectMapper.readTree(jsonString);
        assertEquals(initialSize + 2, json.size());
    }
}
