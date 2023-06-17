package com.accenture.challengecompanies.presentation.dto;

import com.accenture.challengecompanies.domain.enums.DocumentType;
import com.accenture.challengecompanies.domain.models.Company;
import com.accenture.challengecompanies.domain.models.Supplier;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;

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


    public Supplier toSupplierModel() {
        return new Supplier(
                this.getDocument(),
                this.documentType,
                this.getName(),
                this.getEmail(),
                this.getAddress().toAddressModel());
    }

}
