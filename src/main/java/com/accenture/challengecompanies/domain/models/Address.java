package com.accenture.challengecompanies.domain.models;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Address {

    private Long id;
    private String street;
    private int number;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;
    private String zipCode;
    private String country;

    public Address(String street, int number, String complement, String neighborhood, String city, String state, String zipCode, String country) {
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
    }

    public Address(Long id, String street, int number, String complement, String neighborhood, String city, String state, String zipCode, String country) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
    }
}
