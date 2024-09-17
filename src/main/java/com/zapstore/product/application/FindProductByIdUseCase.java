package com.zapstore.product.application;

import java.util.Optional;

import com.zapstore.product.domain.entity.Product;
import com.zapstore.product.domain.service.ProductService;

public class FindProductByIdUseCase {
    private final ProductService productService;

    public FindProductByIdUseCase(ProductService productService){
        this.productService = productService;
    }

    public Optional<Product> findProductById(int id){
        return productService.findProductById(id);
    }
}
