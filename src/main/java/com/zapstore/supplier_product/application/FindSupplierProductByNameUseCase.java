package com.zapstore.supplier_product.application;

import java.util.List;

import com.zapstore.supplier_product.domain.entity.SupplierProduct;
import com.zapstore.supplier_product.domain.service.SupplierProductService;

public class FindSupplierProductByNameUseCase {
    private final SupplierProductService supplier_productService;

    public FindSupplierProductByNameUseCase(SupplierProductService supplier_productService) {
        this.supplier_productService = supplier_productService;
    }

    public List<SupplierProduct> findSupplierProductByName(String supplier_productName) {
        return supplier_productService.findSupplierProductByName(supplier_productName);
    }
}
