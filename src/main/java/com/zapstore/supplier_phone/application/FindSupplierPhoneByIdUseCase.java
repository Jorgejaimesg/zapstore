package com.zapstore.supplier_phone.application;

import java.util.Optional;

import com.zapstore.supplier_phone.domain.entity.SupplierPhone;
import com.zapstore.supplier_phone.domain.service.SupplierPhoneService;

public class FindSupplierPhoneByIdUseCase {
    private final SupplierPhoneService supplier_phoneService;
    public FindSupplierPhoneByIdUseCase(SupplierPhoneService supplier_phoneService) {
        this.supplier_phoneService = supplier_phoneService;
    }

    public Optional<SupplierPhone> findSupplierPhoneById(int id) {
        return supplier_phoneService.findSupplierPhoneById(id);
    }
}
