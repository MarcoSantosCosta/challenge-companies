package com.accenture.challengecompanies.presentation.dto;

import com.accenture.challengecompanies.domain.models.Company;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCompanyRequest {

    @NotBlank(message = "O CNPJ não pode estar em branco")
    @Size(min = 14, max = 14, message = "O CNPJ deve ter exatamente 14 caracteres")
    @CNPJ
    private String cnpj;

    @NotBlank(message = "O Nome não pode estar em branco")
    private String tradeName;

    @Valid
    private AddressRequest address;

    public Company toCompanyModel() {
        return new Company(this.getCnpj(), this.getTradeName(), this.getAddress().toAddressModel());
    }

}
