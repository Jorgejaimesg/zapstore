package com.zapstore.region.application;

import java.util.Optional;

import com.zapstore.region.domain.entity.Region;
import com.zapstore.region.domain.service.RegionService;

public class FindRegionByNameUseCase {
        private final RegionService regionService;

    public FindRegionByNameUseCase(RegionService regionService) {
        this.regionService = regionService;
    }

    public Optional<Region> execute(String Country, String Name ) {
        return regionService.findRegionByName(Country, Name);
    }
}
