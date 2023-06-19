package com.accenture.challengecompanies.application.usecases.supplier;

import com.accenture.challengecompanies.domain.exceptions.AgeRestrictionException;
import com.accenture.challengecompanies.domain.models.Supplier;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

//This @Service annotation is intentionally breaking the Clean Architecture principles in order to reduce code verbosity.
//@Service annotation provide an automatic dependency injection for supplierRepository
@Service
public class AgeValidationUseCase {
    private final List<String> restrictedStates = Arrays.asList("PR", "PARANA", "PARANÁ");

    public boolean execute(Supplier supplier) {
        if (restrictedStates.contains(supplier.getAddress().getState()) &&
                ageCalculator(supplier.getBirthdate()) < 18) {
            throw new AgeRestrictionException();
        }
        return true;
    }

    private int ageCalculator(Date birthdate) {
        Date today = new Date();
        int birthdateYear = birthdate.getYear();
        int actualYear = today.getYear();
        int age = actualYear - birthdateYear;


        if (today.getMonth() < birthdate.getMonth() ||
                (today.getMonth() == birthdate.getMonth() &&
                        today.getDay() < birthdate.getDay())) {
            age--;
        }
        return age;
    }
}
