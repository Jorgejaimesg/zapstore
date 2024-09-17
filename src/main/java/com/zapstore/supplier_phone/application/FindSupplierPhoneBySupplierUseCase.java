package com.zapstore.supplier_phone.application;

import java.util.List;

import com.zapstore.supplier_phone.domain.entity.SupplierPhone;
import com.zapstore.supplier_phone.domain.service.SupplierPhoneService;

public class FindSupplierPhoneBySupplierUseCase {
    private final SupplierPhoneService supplier_phoneService;

    public FindSupplierPhoneBySupplierUseCase(SupplierPhoneService supplier_phoneService) {
        this.supplier_phoneService = supplier_phoneService;
    }

    public List<SupplierPhone> findSupplierPhoneBySupplier(String supplier_phoneName) {
        return supplier_phoneService.findSupplierPhoneBySupplier(supplier_phoneName);
    }
}
