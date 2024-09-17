package com.zapstore.brand.application;

import com.zapstore.brand.domain.entity.Brand;
import com.zapstore.brand.domain.service.BrandService;

public class UpdateBrandUseCase {
    private final BrandService brandService;

    public UpdateBrandUseCase(BrandService brandService){
        this.brandService = brandService;
    }

    public void execute(Brand brand){
        brandService.updateBrand(brand);
    }
}
