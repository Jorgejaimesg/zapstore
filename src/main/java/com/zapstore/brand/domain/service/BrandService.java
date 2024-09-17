package com.zapstore.brand.domain.service;

import java.util.List;
import java.util.Optional;
import com.zapstore.brand.domain.entity.Brand;

public interface BrandService {
    void createBrand(Brand Brand);
    void updateBrand(Brand Brand);
    Brand deleteBrand(String name);
    Optional<Brand> findBrandByName (String name);
    Optional<Brand> findBrandById (int Id);
    List<Brand> findAllBrand();
}
