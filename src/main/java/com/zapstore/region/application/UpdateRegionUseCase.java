package com.zapstore.region.application;

import com.zapstore.region.domain.entity.Region;
import com.zapstore.region.domain.service.RegionService;

public class UpdateRegionUseCase {
    private final RegionService regionService;

    public UpdateRegionUseCase(RegionService regionService){
        this.regionService = regionService;
    }

    public void execute(Region region){
        regionService.updateRegion(region);
    }
}
