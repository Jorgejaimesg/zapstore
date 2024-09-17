package com.zapstore.supplier_product.application;

import com.zapstore.supplier_product.domain.entity.SupplierProduct;
import com.zapstore.supplier_product.domain.service.SupplierProductService;

public class DeleteSupplierProductUseCase {
    private final SupplierProductService supplier_productService;

    public DeleteSupplierProductUseCase (SupplierProductService supplier_productService) {
        this.supplier_productService = supplier_productService;
    }

    public SupplierProduct execute(String Name, int productid) {
        return supplier_productService.deleteSupplierProduct(Name, productid);
    }
}
