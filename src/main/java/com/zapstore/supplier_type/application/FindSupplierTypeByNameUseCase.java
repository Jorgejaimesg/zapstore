package com.zapstore.supplier_type.application;

import java.util.Optional;

import com.zapstore.supplier_type.domain.entity.SupplierType;
import com.zapstore.supplier_type.domain.service.SupplierTypeService;

public class FindSupplierTypeByNameUseCase {
    private final SupplierTypeService supplier_typeService;

    public FindSupplierTypeByNameUseCase(SupplierTypeService supplier_typeService) {
        this.supplier_typeService = supplier_typeService;
    }

    public Optional<SupplierType> findSupplierTypeByName(String supplier_typeName) {
        return supplier_typeService.findSupplierTypeByName(supplier_typeName);
    }
}
