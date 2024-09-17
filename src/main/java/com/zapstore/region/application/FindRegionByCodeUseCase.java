package com.zapstore.region.application;

import java.util.Optional;

import com.zapstore.region.domain.entity.Region;
import com.zapstore.region.domain.service.RegionService;

public class FindRegionByCodeUseCase {
            private final RegionService regionService;

    public FindRegionByCodeUseCase(RegionService regionService) {
        this.regionService = regionService;
    }

    public Optional<Region> findRegionByCode(String regionBox) {
        return regionService.findRegionByCode(regionBox);
    }
}
