package com.zapstore.supplier_phone.application;

import com.zapstore.supplier_phone.domain.entity.SupplierPhone;
import com.zapstore.supplier_phone.domain.service.SupplierPhoneService;

public class UpdateSupplierPhoneUseCase {
    private final SupplierPhoneService supplier_phoneService;

    public UpdateSupplierPhoneUseCase(SupplierPhoneService supplier_phoneService){
        this.supplier_phoneService = supplier_phoneService;
    }

    public void execute(SupplierPhone supplier_phone){
        supplier_phoneService.updateSupplierPhone(supplier_phone);
    }
}
