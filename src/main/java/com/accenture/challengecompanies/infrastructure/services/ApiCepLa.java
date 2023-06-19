package com.accenture.challengecompanies.infrastructure.services;

import com.accenture.challengecompanies.domain.models.Address;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class ApiCepLa implements CepFetchInterface {

    @Autowired
    private final RestTemplateBuilder restTemplateBuilder;

    private final RestTemplate restTemplate;

    public ApiCepLa(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.restTemplate = restTemplateBuilder.build();
    }

    public Address fetch(String cep) {
        String url = "http://cep.la/" + cep;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        String response = responseEntity.getBody();


        Optional<Address> addresReurn = Optional.ofNullable(convertJsonToAddress(response));

        return addresReurn.orElse(null);

    }

    public Address convertJsonToAddress(String jsonResponse) {
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode responseNode = null;
        try {
            responseNode = objectMapper.readValue(jsonResponse, ObjectNode.class);
        } catch (Exception e) {
            return null;
        }


        String street = responseNode.get("logradouro").asText();
        int number = 0;
        String complement = "";
        String neighborhood = responseNode.get("bairro").asText();
        String city = responseNode.get("cidade").asText();
        String state = responseNode.get("uf").asText();
        String zipCode = responseNode.get("cep").asText();
        String country = "Brasil";

        return new Address(street, number, complement, neighborhood, city, state, zipCode, country);


    }
}
