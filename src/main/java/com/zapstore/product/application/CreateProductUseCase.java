package com.zapstore.product.application;

import com.zapstore.product.domain.entity.Product;
import com.zapstore.product.domain.service.ProductService;

public class CreateProductUseCase {
    private final ProductService productService;

    public CreateProductUseCase(ProductService productService){
        this.productService = productService;
    }

    public void execute(Product Product){
        productService.createProduct(Product);
    }
}
