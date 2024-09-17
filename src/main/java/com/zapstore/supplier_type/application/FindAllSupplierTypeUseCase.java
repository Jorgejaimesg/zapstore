package com.zapstore.supplier_type.application;

import java.util.List;

import com.zapstore.supplier_type.domain.entity.SupplierType;
import com.zapstore.supplier_type.domain.service.SupplierTypeService;

public class FindAllSupplierTypeUseCase {
    private final SupplierTypeService supplier_typeService;

    public FindAllSupplierTypeUseCase(SupplierTypeService supplier_typeService) {
        this.supplier_typeService = supplier_typeService;
    }

    public List<SupplierType> findAllSupplierType() {
        return supplier_typeService.findAllSupplierType();
    }
}
