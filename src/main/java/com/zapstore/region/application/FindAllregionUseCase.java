package com.zapstore.region.application;

import java.util.List;

import com.zapstore.region.domain.entity.Region;
import com.zapstore.region.domain.service.RegionService;


public class FindAllregionUseCase {
    private final RegionService regionService;

    public FindAllregionUseCase(RegionService regionService) {
        this.regionService = regionService;
    }

    public List<Region> findAllRegion() {
        return regionService.findAllRegion();
    }

    }
