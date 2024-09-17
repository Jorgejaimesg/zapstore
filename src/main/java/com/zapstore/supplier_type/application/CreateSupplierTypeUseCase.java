package com.zapstore.supplier_type.application;

import com.zapstore.supplier_type.domain.entity.SupplierType;
import com.zapstore.supplier_type.domain.service.SupplierTypeService;

public class CreateSupplierTypeUseCase {
    private final SupplierTypeService supplier_typeService;

    public CreateSupplierTypeUseCase(SupplierTypeService supplier_typeService){
        this.supplier_typeService = supplier_typeService;
    }

    public void execute(SupplierType SupplierType){
        supplier_typeService.createSupplierType(SupplierType);
    }
}
