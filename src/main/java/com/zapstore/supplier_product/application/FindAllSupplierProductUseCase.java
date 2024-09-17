package com.zapstore.supplier_product.application;

import java.util.List;

import com.zapstore.supplier_product.domain.entity.SupplierProduct;
import com.zapstore.supplier_product.domain.service.SupplierProductService;

public class FindAllSupplierProductUseCase {
    private final SupplierProductService supplier_productService;

    public FindAllSupplierProductUseCase(SupplierProductService supplier_productService) {
        this.supplier_productService = supplier_productService;
    }

    public List<SupplierProduct> findAllSupplierProduct() {
        return supplier_productService.findAllSupplierProduct();
    }
}
