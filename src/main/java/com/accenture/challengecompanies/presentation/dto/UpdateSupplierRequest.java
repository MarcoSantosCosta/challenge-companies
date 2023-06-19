package com.accenture.challengecompanies.presentation.dto;

import com.accenture.challengecompanies.domain.enums.DocumentType;
import com.accenture.challengecompanies.domain.models.Supplier;
import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSupplierRequest {

    @NotBlank(message = "O CPF ou CNPJ não pode estar em branco")
    private String document;

    @NotBlank(message = "O tipo de documento não pode estar em branco")
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

    public Date getBirthDate() {
        return birthDate;
    }

    public String getRg() {
        return rg;
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


}
