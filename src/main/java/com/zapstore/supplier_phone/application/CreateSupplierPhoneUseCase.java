package com.zapstore.supplier_phone.application;

import com.zapstore.supplier_phone.domain.entity.SupplierPhone;
import com.zapstore.supplier_phone.domain.service.SupplierPhoneService;

public class CreateSupplierPhoneUseCase {
    private final SupplierPhoneService supplier_phoneService;

    public CreateSupplierPhoneUseCase(SupplierPhoneService supplier_phoneService){
        this.supplier_phoneService = supplier_phoneService;
    }

    public void execute(SupplierPhone SupplierPhone){
        supplier_phoneService.createSupplierPhone(SupplierPhone);
    }
}
