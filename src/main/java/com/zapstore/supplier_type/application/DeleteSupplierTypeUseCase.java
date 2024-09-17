package com.zapstore.supplier_type.application;

import com.zapstore.supplier_type.domain.entity.SupplierType;
import com.zapstore.supplier_type.domain.service.SupplierTypeService;

public class DeleteSupplierTypeUseCase {
    private final SupplierTypeService supplier_typeService;

    public DeleteSupplierTypeUseCase (SupplierTypeService supplier_typeService) {
        this.supplier_typeService = supplier_typeService;
    }

    public SupplierType execute(String Name) {
        return supplier_typeService.deleteSupplierType(Name);
    }
}
