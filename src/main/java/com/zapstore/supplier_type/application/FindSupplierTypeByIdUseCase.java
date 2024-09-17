package com.zapstore.supplier_type.application;

import java.util.Optional;

import com.zapstore.supplier_type.domain.entity.SupplierType;
import com.zapstore.supplier_type.domain.service.SupplierTypeService;

public class FindSupplierTypeByIdUseCase {
    private final SupplierTypeService supplier_typeService;
    public FindSupplierTypeByIdUseCase(SupplierTypeService supplier_typeService) {
        this.supplier_typeService = supplier_typeService;
    }

    public Optional<SupplierType> findSupplierTypeById(int id) {
        return supplier_typeService.findSupplierTypeById(id);
    }
}
