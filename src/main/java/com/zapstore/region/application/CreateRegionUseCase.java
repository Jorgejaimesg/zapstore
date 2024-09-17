package com.zapstore.region.application;

import com.zapstore.region.domain.entity.Region;
import com.zapstore.region.domain.service.RegionService;

public class CreateRegionUseCase {
    private final RegionService regionService;

    public CreateRegionUseCase(RegionService regionService){
        this.regionService = regionService;
    }

    public void execute(Region region){
        regionService.createRegion(region);
}
}