package com.accenture.challengecompanies.presentation;

import com.accenture.challengecompanies.domain.enums.DocumentType;
import com.accenture.challengecompanies.domain.models.Address;
import com.accenture.challengecompanies.domain.models.Company;
import com.accenture.challengecompanies.domain.models.Supplier;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Utils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static ObjectNode generateCompanyDummyJson() throws JsonProcessingException {
        ObjectNode dummyRequest = objectMapper.createObjectNode();

        dummyRequest.put("cnpj", "73473759000165");
        dummyRequest.put("tradeName", "Nome Comercial da Empresa");

        ObjectNode addressNode = objectMapper.createObjectNode();
        addressNode.put("street", "Rua Exemplo");
        addressNode.put("number", "123");
        addressNode.put("complement", "Complemento do Endereço");
        addressNode.put("neighborhood", "Bairro Exemplo");
        addressNode.put("city", "Cidade Exemplo");
        addressNode.put("state", "Estado Exemplo");
        addressNode.put("zipCode", "12345-678");
        addressNode.put("country", "País Exemplo");

        dummyRequest.set("address", addressNode);

        return dummyRequest;
    }

    public static ObjectNode generateSupplierDummyJson() {
        ObjectNode dummyRequest = objectMapper.createObjectNode();

        dummyRequest.put("document", "73473759000165");
        dummyRequest.put("documentType", "CNPJ");
        dummyRequest.put("name", "Nome Comercial da Empresa");
        dummyRequest.put("email", "marco@email.com");

        ObjectNode addressNode = objectMapper.createObjectNode();
        addressNode.put("street", "Rua Exemplo");
        addressNode.put("number", "123");
        addressNode.put("complement", "Complemento do Endereço");
        addressNode.put("neighborhood", "Bairro Exemplo");
        addressNode.put("city", "Cidade Exemplo");
        addressNode.put("state", "Estado Exemplo");
        addressNode.put("zipCode", "12345-678");
        addressNode.put("country", "País Exemplo");

        dummyRequest.set("address", addressNode);

        return dummyRequest;
    }


    public static Address generateDummyAddres() {

        return new Address(
                "Dummy Street",
                13,
                "",
                "Dummy Neighborhood",
                "Dummy City",
                "Dummy State",
                "99999999",
                "Dummy Contry");
    }

    public static Supplier generateDummySupplier() {
        return new Supplier(
                "999999999999999",
                DocumentType.CNPJ,
                "Dummy Name",
                "Dummy Supplier",
                generateDummyAddres()
        );
    }

    public static Company generateDummyCompany() {
        return new Company(
                "999999999999999",
                "Dummy Company",
                generateDummyAddres());
    }


}
