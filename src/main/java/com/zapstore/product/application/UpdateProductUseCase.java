package com.zapstore.product.application;

import com.zapstore.product.domain.entity.Product;
import com.zapstore.product.domain.service.ProductService;

public class UpdateProductUseCase {
    private final ProductService productService;

    public UpdateProductUseCase(ProductService productService){
        this.productService = productService;
    }

    public void execute(Product Product){
        productService.updateProduct(Product);
    }
}
