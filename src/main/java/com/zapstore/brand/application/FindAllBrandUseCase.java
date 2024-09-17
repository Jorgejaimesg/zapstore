package com.zapstore.brand.application;

import java.util.List;

import com.zapstore.brand.domain.entity.Brand;
import com.zapstore.brand.domain.service.BrandService;

public class FindAllBrandUseCase {
    private final BrandService brandService;

    public FindAllBrandUseCase(BrandService brandService) {
        this.brandService = brandService;
    }

    public List<Brand> findAllBrand() {
        return brandService.findAllBrand();
    }
}
