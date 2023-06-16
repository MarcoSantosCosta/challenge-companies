package com.accenture.challengecompanies.presentation.dto;

import com.accenture.challengecompanies.domain.models.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressRequest {
    @NotBlank(message = "A Rua não pode estar em branco")
    private String street;

    @NotNull(message = "O Número não pode estar em vazio")
    private Integer number;

    private String complement;

    @NotBlank(message = "O Bairro não pode estar em branco")
    private String neighborhood;

    @NotBlank(message = "A Cidade não pode estar em branco")
    private String city;

    @NotBlank(message = "O Estado não pode estar em branco")
    private String state;

    @NotBlank(message = "O CEP não pode estar em branco")
    private String zipCode;

    @NotBlank(message = "O País não pode estar em branco")
    private String country;

    public Address toAddressModel() {
        return new Address(
                this.getStreet(),
                this.getNumber(),
                this.getComplement(),
                this.getNeighborhood(),
                this.getCity(),
                this.getState(),
                this.getZipCode(),
                this.getCountry());
    }
}
