package com.zapstore.supplier.application;

import com.zapstore.supplier.domain.service.SupplierService;

public class DeleteSupplierUseCase {
    private final SupplierService supplierService;

    public DeleteSupplierUseCase(SupplierService supplierService){
        this.supplierService = supplierService;
    }

    public void execute(String SupplierId){
        supplierService.deleteSupplier(SupplierId);
    }
}
