package com.accenture.challengecompanies.domain.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddressTest {

    @Test
    public void testAddressConstruction() {
        String street = "Rua teste";
        int number = 1234;
        String complement = "Apt 04";
        String neighborhood = "Teste Bairro";
        String city = "Passos";
        String state = "Minas";
        String zipCode = "12345-678";
        String country = "Brasil";

        Address address = new Address(street, number, complement, neighborhood, city, state, zipCode, country);

        Assertions.assertEquals(street, address.getStreet());
        Assertions.assertEquals(number, address.getNumber());
        Assertions.assertEquals(complement, address.getComplement());
        Assertions.assertEquals(neighborhood, address.getNeighborhood());
        Assertions.assertEquals(city, address.getCity());
        Assertions.assertEquals(state, address.getState());
        Assertions.assertEquals(zipCode.replaceAll("\\D",""), address.getZipCode());
        Assertions.assertEquals(country, address.getCountry());
    }
}
