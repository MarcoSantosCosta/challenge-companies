package com.accenture.challengecompanies.infrastructure.persistence.mappings.supplier;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndividualSupplierMapping extends SupplierMapping{
    private String rg;
    private Date brithdayDate;


}
