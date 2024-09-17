package com.zapstore.supplier_phone.application;

import java.util.List;

import com.zapstore.supplier_phone.domain.entity.SupplierPhone;
import com.zapstore.supplier_phone.domain.service.SupplierPhoneService;

public class FindAllSupplierPhoneUseCase {
    private final SupplierPhoneService supplier_phoneService;

    public FindAllSupplierPhoneUseCase(SupplierPhoneService supplier_phoneService) {
        this.supplier_phoneService = supplier_phoneService;
    }

    public List<SupplierPhone> findAllSupplierPhone() {
        return supplier_phoneService.findAllSupplierPhone();
    }
}
