package com.zapstore.supplier.application;

import com.zapstore.supplier.domain.entity.Supplier;
import com.zapstore.supplier.domain.service.SupplierService;

public class CreateSupplierUseCase {
    private final SupplierService supplierService;

    public CreateSupplierUseCase(SupplierService supplierService){
        this.supplierService = supplierService;
    }

    public void execute(Supplier Supplier){
        supplierService.createSupplier(Supplier);
    }
}
