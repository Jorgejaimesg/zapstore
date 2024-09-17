package com.zapstore.supplier.application;

import java.util.Optional;

import com.zapstore.supplier.domain.entity.Supplier;
import com.zapstore.supplier.domain.service.SupplierService;

public class FindSupplierByIdUseCase {
    private final SupplierService supplierService;

    public FindSupplierByIdUseCase(SupplierService supplierService){
        this.supplierService = supplierService;
    }

    public Optional<Supplier> findSupplierById(String id){
        return supplierService.findSupplierById(id);
    }
}
