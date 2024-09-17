package com.zapstore.brand.application;

import java.util.Optional;

import com.zapstore.brand.domain.entity.Brand;
import com.zapstore.brand.domain.service.BrandService;

public class FindBrandByIdUseCase {
    private final BrandService brandService;
    public FindBrandByIdUseCase(BrandService brandService) {
        this.brandService = brandService;
    }

    public Optional<Brand> findBrandById(int id) {
        return brandService.findBrandById(id);
    }
}
