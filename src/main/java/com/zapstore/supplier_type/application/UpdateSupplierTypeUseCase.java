package com.zapstore.supplier_type.application;

import com.zapstore.supplier_type.domain.entity.SupplierType;
import com.zapstore.supplier_type.domain.service.SupplierTypeService;

public class UpdateSupplierTypeUseCase {
    private final SupplierTypeService supplier_typeService;

    public UpdateSupplierTypeUseCase(SupplierTypeService supplier_typeService){
        this.supplier_typeService = supplier_typeService;
    }

    public void execute(SupplierType supplier_type){
        supplier_typeService.updateSupplierType(supplier_type);
    }
}
