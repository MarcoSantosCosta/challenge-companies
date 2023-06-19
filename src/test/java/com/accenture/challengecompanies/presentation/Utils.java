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
    public static ObjectNode generateCompanyDummyJson(Company company) {
        ObjectNode dummyRequest = objectMapper.createObjectNode();

        dummyRequest.put("cnpj", company.getCnpj());
        dummyRequest.put("tradeName", company.getTradeName());

        ObjectNode addressNode = objectMapper.createObjectNode();
        addressNode.put("street", company.getAddress().getStreet());
        addressNode.put("number", company.getAddress().getNumber());
        addressNode.put("complement", company.getAddress().getComplement());
        addressNode.put("neighborhood", company.getAddress().getNeighborhood());
        addressNode.put("city", company.getAddress().getCity());
        addressNode.put("state", company.getAddress().getState());
        addressNode.put("zipCode", company.getAddress().getZipCode());
        addressNode.put("country", company.getAddress().getCountry());

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
        addressNode.put("zipCode", "13561060");
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
                "13561060",
                "Dummy Contry");
    }

    public static Supplier generateDummySupplier() {
        return new Supplier(
                "76.936.476/0001-09",
                DocumentType.CNPJ,
                "Dummy Name",
                "Dummy Supplier",
                generateDummyAddres()
        );
    }

    public static Company generateDummyCompany() {
        return new Company(
                "76.936.476/0001-09",
                "Dummy Company",
                generateDummyAddres());
    }


}
