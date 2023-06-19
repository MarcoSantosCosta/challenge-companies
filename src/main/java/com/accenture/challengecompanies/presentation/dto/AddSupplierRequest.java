package com.accenture.challengecompanies.presentation.dto;

import com.accenture.challengecompanies.domain.enums.DocumentType;
import com.accenture.challengecompanies.domain.models.Supplier;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.GroupSequence;
import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;

import java.util.Date;


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
    private Date birthDate;
    private String rg;

    public AddSupplierRequest(String document, DocumentType documentType, String name, String email, AddressRequest address, @NotNull(groups = CPFGroup.class, message = "A data de nascimento é obrigatória para CPF") Date birthDate, @NotNull(groups = CPFGroup.class, message = "O RG é obrigatório para CPF") String rg) {
        this.document = document;
        this.documentType = documentType;
        this.name = name;
        this.email = email;
        this.address = address;
        this.birthDate = birthDate;
        this.rg = rg;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document.toUpperCase();
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getRg() {
        return rg;
    }

    public AddressRequest getAddress() {
        return address;
    }

    public void setAddress(AddressRequest address) {
        this.address = address;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @AssertTrue(message = "A data de nascimento é obrigatória para pessoas físicas")
    private boolean isBirthDate() {
        if (documentType == DocumentType.CPF) {
            return birthDate != null;
        }
        return true;
    }



    @AssertTrue(message = "O RG é campo obrigatório para pessoas físicas")
    private boolean isRg() {
        if (documentType == DocumentType.CPF) {
            return rg != null;
        }
        return true;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Supplier toSupplierModel() {
        return new Supplier(
                this.getDocument(),
                this.getDocumentType(),
                this.getName(),
                this.getEmail(),
                this.getRg(),
                this.getBirthDate(),
                this.getAddress().toAddressModel());
    }

    @GroupSequence({Default.class, CPFGroup.class})
    public interface ValidationOrder {
    }

    public interface CPFGroup {
    }
}
