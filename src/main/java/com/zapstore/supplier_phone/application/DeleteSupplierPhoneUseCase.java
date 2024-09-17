package com.zapstore.supplier_phone.application;

import com.zapstore.supplier_phone.domain.entity.SupplierPhone;
import com.zapstore.supplier_phone.domain.service.SupplierPhoneService;

public class DeleteSupplierPhoneUseCase {
    private final SupplierPhoneService supplier_phoneService;

    public DeleteSupplierPhoneUseCase (SupplierPhoneService supplier_phoneService) {
        this.supplier_phoneService = supplier_phoneService;
    }

    public SupplierPhone execute(int id) {
        return supplier_phoneService.deleteSupplierPhone(id);
    }
}
