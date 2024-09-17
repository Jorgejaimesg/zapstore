package com.zapstore.product.domain.service;
import java.util.List;
import java.util.Optional;

import com.zapstore.product.domain.entity.Product;

public interface ProductService {
    void createProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(String productId);
    Optional<Product> findProductById(int productId);
    List<Product> findAllProduct();
}
