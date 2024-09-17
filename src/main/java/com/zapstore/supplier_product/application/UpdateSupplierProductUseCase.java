package com.zapstore.supplier_product.application;

import com.zapstore.supplier_product.domain.entity.SupplierProduct;
import com.zapstore.supplier_product.domain.service.SupplierProductService;

public class UpdateSupplierProductUseCase {
    private final SupplierProductService supplier_productService;

    public UpdateSupplierProductUseCase(SupplierProductService supplier_productService){
        this.supplier_productService = supplier_productService;
    }

    public void execute(SupplierProduct supplier_product){
        supplier_productService.updateSupplierProduct(supplier_product);
    }
}
