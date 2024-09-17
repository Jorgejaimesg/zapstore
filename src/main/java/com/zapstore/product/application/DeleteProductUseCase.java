package com.zapstore.product.application;

import com.zapstore.product.domain.service.ProductService;

public class DeleteProductUseCase {
    private final ProductService productService;

    public DeleteProductUseCase(ProductService productService){
        this.productService = productService;
    }

    public void execute(String ProductId){
        productService.deleteProduct(ProductId);
    }
}
