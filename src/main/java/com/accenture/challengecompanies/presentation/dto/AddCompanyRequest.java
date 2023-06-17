package com.accenture.challengecompanies.presentation.dto;

import com.accenture.challengecompanies.domain.models.Company;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddCompanyRequest {

    @NotNull(message = "O CNPJ é obrigatório")
    @NotBlank(message = "O CNPJ não pode estar em branco")
    @CNPJ
    private String cnpj;

    @NotBlank(message = "O Nome não pode estar em branco")
    private String tradeName;

    @Valid
    private AddressRequest address;

    public Company toCompanyModel() {
        return new Company(this.getCnpj(), this.getTradeName(), this.getAddress().toAddressModel());
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj.replaceAll("\\D", "");
    }
}
