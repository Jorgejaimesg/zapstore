package com.zapstore.supplier.domain.service;
import java.util.List;
import java.util.Optional;

import com.zapstore.supplier.domain.entity.Supplier;

public interface SupplierService {
    void createSupplier(Supplier supplier);
    void updateSupplier(Supplier supplier);
    void deleteSupplier(String supplierId);
    Optional<Supplier> findSupplierById(String supplierId);
    List<Supplier> findAllSupplier();
}
