package com.zapstore.supplier.application;

import java.util.List;

import com.zapstore.supplier.domain.entity.Supplier;
import com.zapstore.supplier.domain.service.SupplierService;

public class FindAllSupplierUseCase {
    private final SupplierService supplierService;

    public FindAllSupplierUseCase(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    public List<Supplier> findAllSupplier() {
        return supplierService.findAllSupplier();
    }
}
