package com.zapstore.supplier_product.domain.service;

import java.util.List;

import com.zapstore.supplier_product.domain.entity.SupplierProduct;

public interface SupplierProductService {
    void createSupplierProduct(SupplierProduct SupplierProduct);
    void updateSupplierProduct(SupplierProduct SupplierProduct);
    SupplierProduct deleteSupplierProduct(String name, int productid);
    List<SupplierProduct> findSupplierProductByName (String name);
    List<SupplierProduct> findSupplierProductById (int Id);
    List<SupplierProduct> findAllSupplierProduct();
}
