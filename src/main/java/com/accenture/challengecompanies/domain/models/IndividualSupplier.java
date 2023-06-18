package com.accenture.challengecompanies.domain.models;

import com.accenture.challengecompanies.domain.enums.DocumentType;

import java.util.Date;
import java.util.Objects;

public class IndividualSupplier extends Supplier {

    private String rg;
    private Date birthdate;


    public IndividualSupplier() {

    }

    public IndividualSupplier(String document, DocumentType documentType, String name, String email, Address address, String rg, Date birthdate) {
        super(document, documentType, name, email, address);
        this.rg = rg;
        this.birthdate = birthdate;
    }

    public IndividualSupplier(long id, String document, DocumentType documentType, String name, String email, Address address, String rg, Date birthdate) {
        super(id, document, documentType, name, email, address);
        this.rg = rg;
        this.birthdate = birthdate;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), rg, birthdate);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        IndividualSupplier other = (IndividualSupplier) obj;
        return Objects.equals(rg, other.rg) &&
                Objects.equals(birthdate, other.birthdate);
    }


}
