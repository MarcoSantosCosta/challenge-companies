package com.accenture.challengecompanies.presentation.dto;

import com.accenture.challengecompanies.domain.enums.DocumentType;
import com.accenture.challengecompanies.domain.models.Supplier;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public class AddSupplierRequest {

    @NotBlank(message = "O CPF ou CNPJ não pode estar em branco")
    private String document;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private DocumentType documentType;

    @NotBlank(message = "O Nome não pode estar em branco")
    private String name;

    @NotBlank(message = "O Nome não pode estar em branco")
    @Email
    private String email;

    @Valid
    private AddressRequest address;

    public AddSupplierRequest(String document, DocumentType documentType, String name, String email, AddressRequest address) {
        this.document = document;
        this.documentType = documentType;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public void setDocument(String document) {
        this.document = document.toUpperCase();
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public String getDocument() {
        return document;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public AddressRequest getAddress() {
        return address;
    }

    public Supplier toSupplierModel() {
        return new Supplier(
                this.getDocument(),
                this.documentType,
                this.getName(),
                this.getEmail(),
                this.getAddress().toAddressModel());
    }
}
