package com.zapstore.supplier.application;

import com.zapstore.supplier.domain.entity.Supplier;
import com.zapstore.supplier.domain.service.SupplierService;

public class UpdateSupplierUseCase {
    private final SupplierService supplierService;

    public UpdateSupplierUseCase(SupplierService supplierService){
        this.supplierService = supplierService;
    }

    public void execute(Supplier Supplier){
        supplierService.updateSupplier(Supplier);
    }
}
