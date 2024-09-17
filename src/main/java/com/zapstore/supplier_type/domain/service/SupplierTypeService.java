package com.zapstore.supplier_type.domain.service;

import java.util.List;
import java.util.Optional;
import com.zapstore.supplier_type.domain.entity.SupplierType;

public interface SupplierTypeService {
    void createSupplierType(SupplierType SupplierType);
    void updateSupplierType(SupplierType SupplierType);
    SupplierType deleteSupplierType(String name);
    Optional<SupplierType> findSupplierTypeByName (String name);
    Optional<SupplierType> findSupplierTypeById (int Id);
    List<SupplierType> findAllSupplierType();
}
