package com.zapstore.supplier_product.application;

import java.util.List;

import com.zapstore.supplier_product.domain.entity.SupplierProduct;
import com.zapstore.supplier_product.domain.service.SupplierProductService;

public class FindSupplierProductByIdUseCase {
    private final SupplierProductService supplier_productService;
    public FindSupplierProductByIdUseCase(SupplierProductService supplier_productService) {
        this.supplier_productService = supplier_productService;
    }

    public List<SupplierProduct> findSupplierProductById(int id) {
        return supplier_productService.findSupplierProductById(id);
    }
}
