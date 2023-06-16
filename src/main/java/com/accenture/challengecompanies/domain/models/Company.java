package com.accenture.challengecompanies.domain.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "cnpj")
public class Company {
    private Long id;
    private String cnpj;
    private String tradeName;
    private Address address;

    public Company(String cnpj, String tradeName, Address address) {
        this.cnpj = cnpj;
        this.tradeName = tradeName;
        this.address = address;
    }

    public Company(Long id, String cnpj, String tradeName, Address address) {
        this.id = id;
        this.cnpj = cnpj;
        this.tradeName = tradeName;
        this.address = address;
    }
}
