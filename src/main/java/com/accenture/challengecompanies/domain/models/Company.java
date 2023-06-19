package com.accenture.challengecompanies.domain.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Company {
    private Long id;
    private String cnpj;
    private String tradeName;
    private Address address;
    private List<Supplier> suppliers;

    public Company(String cnpj, String tradeName, Address address) {
        this.cnpj = cnpj;
        this.tradeName = tradeName;
        this.address = address;
        this.suppliers = new ArrayList<Supplier>();
    }

    public Company(Long id, String cnpj, String tradeName, Address address) {
        this.id = id;
        this.cnpj = cnpj.replaceAll("\\D", "");
        ;
        this.tradeName = tradeName;
        this.address = address;
        this.suppliers = new ArrayList<Supplier>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj.replaceAll("\\D", "");
        ;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Company other = (Company) obj;

        return Objects.equals(id, other.id) &&
                Objects.equals(cnpj, other.cnpj) &&
                Objects.equals(tradeName, other.tradeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cnpj, tradeName);
    }


}
