package com.zapstore.product.application;

import java.util.List;

import com.zapstore.product.domain.entity.Product;
import com.zapstore.product.domain.service.ProductService;

public class FindAllProductUseCase {
    private final ProductService productService;

    public FindAllProductUseCase(ProductService productService) {
        this.productService = productService;
    }

    public List<Product> findAllProduct() {
        return productService.findAllProduct();
    }
}
