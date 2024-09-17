package com.zapstore.brand.application;

import java.util.Optional;

import com.zapstore.brand.domain.entity.Brand;
import com.zapstore.brand.domain.service.BrandService;

public class FindBrandByNameUseCase {
    private final BrandService brandService;

    public FindBrandByNameUseCase(BrandService brandService) {
        this.brandService = brandService;
    }

    public Optional<Brand> findBrandByName(String brandName) {
        return brandService.findBrandByName(brandName);
    }
}
