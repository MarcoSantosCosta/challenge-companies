package com.accenture.challengecompanies.infrastructure.persistence.repositories;

import com.accenture.challengecompanies.domain.models.Address;
import com.accenture.challengecompanies.domain.repositories.AddressRepositoryInterface;
import com.accenture.challengecompanies.infrastructure.persistence.mappings.address.AddressMapping;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDatabaseRepository implements AddressRepositoryInterface {

    private final AddressDataBaseRepositoryInterface addressDataBaseRepository;

    public AddressDatabaseRepository(AddressDataBaseRepositoryInterface addressDataBaseRepository) {
        this.addressDataBaseRepository = addressDataBaseRepository;
    }

    @Override
    public Address save(Address address) {
        return addressDataBaseRepository.save(new AddressMapping(address)).toModel();
    }


}
