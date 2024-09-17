package com.zapstore.brand.application;

import com.zapstore.brand.domain.entity.Brand;
import com.zapstore.brand.domain.service.BrandService;

public class DeleteBrandUseCase {
    private final BrandService brandService;

    public DeleteBrandUseCase (BrandService brandService) {
        this.brandService = brandService;
    }

    public Brand execute(String Name) {
        return brandService.deleteBrand(Name);
    }
}
