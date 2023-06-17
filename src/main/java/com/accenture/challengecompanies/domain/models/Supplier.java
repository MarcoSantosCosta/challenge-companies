package com.accenture.challengecompanies.domain.models;

import com.accenture.challengecompanies.domain.enums.DocumentType;

public class Supplier {

    private long id;
    private String document;
    private DocumentType documentType;
    private String name;
    private String email;
    private Address address;

    public Supplier() {
    }

    public Supplier(String document, DocumentType documentType, String name, String email, Address address) {
        this.document = document;
        this.documentType = documentType;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public Supplier(long id, String document, DocumentType documentType, String name, String email, Address address) {
        this.id = id;
        this.document = document;
        this.documentType = documentType;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
