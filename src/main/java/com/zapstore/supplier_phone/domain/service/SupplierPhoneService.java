package com.zapstore.supplier_phone.domain.service;

import java.util.List;
import java.util.Optional;
import com.zapstore.supplier_phone.domain.entity.SupplierPhone;

public interface SupplierPhoneService {
    void createSupplierPhone(SupplierPhone SupplierPhone);
    void updateSupplierPhone(SupplierPhone SupplierPhone);
    SupplierPhone deleteSupplierPhone(int id);
    List<SupplierPhone> findSupplierPhoneBySupplier (String supplierid);
    Optional<SupplierPhone> findSupplierPhoneById (int id);
    List<SupplierPhone> findAllSupplierPhone();
}
