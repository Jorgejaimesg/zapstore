package com.zapstore.supplier_product.application;

import com.zapstore.supplier_product.domain.entity.SupplierProduct;
import com.zapstore.supplier_product.domain.service.SupplierProductService;

public class CreateSupplierProductUseCase {
    private final SupplierProductService supplier_productService;

    public CreateSupplierProductUseCase(SupplierProductService supplier_productService){
        this.supplier_productService = supplier_productService;
    }

    public void execute(SupplierProduct SupplierProduct){
        supplier_productService.createSupplierProduct(SupplierProduct);
    }
}
