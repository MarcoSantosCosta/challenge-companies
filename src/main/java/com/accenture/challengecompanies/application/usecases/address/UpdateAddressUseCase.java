package com.accenture.challengecompanies.application.usecases.address;

import com.accenture.challengecompanies.domain.exceptions.InvalidCepException;
import com.accenture.challengecompanies.domain.models.Address;
import com.accenture.challengecompanies.domain.repositories.AddressRepositoryInterface;
import org.springframework.stereotype.Service;

//This @Service annotation is intentionally breaking the Clean Architecture principles in order to reduce code verbosity.
//@Service annotation provide an automatic dependency injection for companyRepository
@Service
public class UpdateAddressUseCase {

    private final AddressRepositoryInterface addressRepository;
    private final ValidateCepUseCase validateCepUseCase;

    public UpdateAddressUseCase(AddressRepositoryInterface addressRepository, ValidateCepUseCase validateCepUseCase){
        this.addressRepository = addressRepository;
        this.validateCepUseCase =validateCepUseCase;
    }

    public Address execute(Address address) {
        Address addressResponse = validateCepUseCase.execute(address.getZipCode());
        if(addressResponse == null){
            throw  new InvalidCepException(String.format("o CEP %s não é válido", address.getZipCode()));
        }

        return this.addressRepository.save(address);
    }

}
