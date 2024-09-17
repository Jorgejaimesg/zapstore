package com.zapstore.brand.application;

import com.zapstore.brand.domain.entity.Brand;
import com.zapstore.brand.domain.service.BrandService;

public class CreateBrandUseCase {
    private final BrandService brandService;

    public CreateBrandUseCase(BrandService brandService){
        this.brandService = brandService;
    }

    public void execute(Brand Brand){
        brandService.createBrand(Brand);
    }
}
