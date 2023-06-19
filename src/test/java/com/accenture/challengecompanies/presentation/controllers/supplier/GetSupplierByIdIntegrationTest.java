package com.accenture.challengecompanies.presentation.controllers.supplier;

import com.accenture.challengecompanies.domain.models.Supplier;
import com.accenture.challengecompanies.domain.repositories.SupplierRepositoryInterface;
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

import static com.accenture.challengecompanies.presentation.Utils.generateDummySupplier;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase
class GetSupplierByIdIntegrationTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SupplierRepositoryInterface supplierRepository;

    @Test
    public void shouldGetExistingSupplierById() throws Exception {
        Supplier existentSupplier = supplierRepository.create(generateDummySupplier());

        ResultActions result;
        result = mockMvc.perform(MockMvcRequestBuilders.get("/supplier/" + existentSupplier.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk());

        String responseJson = result.andReturn().getResponse().getContentAsString();
        String supplierJson = objectMapper.writeValueAsString(existentSupplier);
        assertEquals(responseJson, supplierJson);
    }
}