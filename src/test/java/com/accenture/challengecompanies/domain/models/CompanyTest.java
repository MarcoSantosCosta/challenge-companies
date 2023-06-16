package com.accenture.challengecompanies.domain.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CompanyTest {

    @Test
    public void testCompanyConstruction() {
        String cnpj = "123321123100012";
        String tradeName = "Teste Company";
        Address address = new Address("Teste", 4321, "Apt 04", "Bairro Teste", "Rua Teste", "State", "13321250", "Teste");

        // When
        Company company = new Company(cnpj, tradeName, address);

        // Then
        Assertions.assertEquals(cnpj, company.getCnpj());
        Assertions.assertEquals(tradeName, company.getTradeName());
        Assertions.assertEquals(address, company.getAddress());
    }

    @Test
    public void testCompanyEquality() {
        String cnpj = "123456789";
        String tradeName = "My Company";
        Address address = new Address("Street", 123, "Apt 4B", "Neighborhood", "City", "State", "12345-678", "Country");

        Company company1 = new Company(cnpj, tradeName, address);
        Company company2 = new Company(cnpj, tradeName, address);
        Company company3 = new Company("987654321", "Another Company", address);

        Assertions.assertEquals(company1, company2);
        Assertions.assertNotEquals(company1, company3);
    }
}
