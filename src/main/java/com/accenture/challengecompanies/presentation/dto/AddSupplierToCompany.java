package com.accenture.challengecompanies.presentation.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public class AddSupplierToCompany {

    @NotBlank(message = "O Id o Forncedor não pode estar em branco")
    private long supplierId;

    @JsonCreator
    public AddSupplierToCompany(@JsonProperty("supplierId") long supplierId) {
        this.supplierId = supplierId;
    }

    public long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(long supplierId) {
        this.supplierId = supplierId;
    }
}
