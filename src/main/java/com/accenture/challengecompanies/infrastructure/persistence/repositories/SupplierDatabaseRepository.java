package com.accenture.challengecompanies.infrastructure.persistence.repositories;

import com.accenture.challengecompanies.domain.enums.DocumentType;
import com.accenture.challengecompanies.domain.exceptions.ElementNotFoundException;
import com.accenture.challengecompanies.domain.models.Supplier;
import com.accenture.challengecompanies.domain.repositories.SupplierRepositoryInterface;
import com.accenture.challengecompanies.infrastructure.persistence.mappings.supplier.SupplierMapping;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class SupplierDatabaseRepository implements SupplierRepositoryInterface {

    private final SupplierDataBaseRepositoryInterface supplierDataBaseRepository;

    public SupplierDatabaseRepository(SupplierDataBaseRepositoryInterface supplierDataBaseRepository) {
        this.supplierDataBaseRepository = supplierDataBaseRepository;
    }

    @Override
    public Supplier create(Supplier supplier) {
        return supplierDataBaseRepository.save(new SupplierMapping(supplier)).toModel();
    }

    private SupplierMapping internalGetById(long id) throws ElementNotFoundException {
        return supplierDataBaseRepository.findById(id).orElseThrow(() -> new ElementNotFoundException("Id " + id + " não encontrado"));
    }

    @Override
    public Supplier getById(long id) throws ElementNotFoundException {
        var supplier = this.internalGetById(id);
        return supplier.toModel();
    }

    @Override
    public Supplier findByDocument(String document, DocumentType documentType) {
        return supplierDataBaseRepository.findByDocumentAndDocumentType(document, documentType)
                .map(SupplierMapping::toModel)
                .orElse(null);
    }


    @Override
    public List<Supplier> getAll() {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        return supplierDataBaseRepository.findAll(sort).stream().map(SupplierMapping::toModel).collect(Collectors.toList());
    }

    @Override
    public void delete(long id) {
        supplierDataBaseRepository.deleteById(id);
    }

    @Override
    public Supplier update(Supplier supplier) throws ElementNotFoundException {
        var companyStored = this.internalGetById(supplier.getId());
        BeanUtils.copyProperties(supplier, companyStored, "id", "document");

        supplierDataBaseRepository.saveAndFlush(companyStored);
        return companyStored.toModel();
    }

}
