package com.accenture.challengecompanies.domain.models;

import java.util.Objects;

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

    public Address() {
    }

    public Address(String street, int number, String complement, String neighborhood, String city, String state, String zipCode, String country) {
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode.replaceAll("\\D", "");
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
        this.zipCode = zipCode.replaceAll("\\D", "");
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode.replaceAll("\\D", "");
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Address other = (Address) obj;
        return Objects.equals(id, other.id) &&
                Objects.equals(street, other.street) &&
                number == other.number &&
                Objects.equals(complement, other.complement) &&
                Objects.equals(neighborhood, other.neighborhood) &&
                Objects.equals(city, other.city) &&
                Objects.equals(state, other.state) &&
                Objects.equals(zipCode, other.zipCode) &&
                Objects.equals(country, other.country);
    }
}
