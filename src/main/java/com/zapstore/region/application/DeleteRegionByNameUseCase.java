package com.zapstore.region.application;

import com.zapstore.region.domain.entity.Region;
import com.zapstore.region.domain.service.RegionService;
import java.util.Optional;

public class DeleteRegionByNameUseCase {
    private final RegionService regionService;

    public DeleteRegionByNameUseCase(RegionService regionService) {
        this.regionService = regionService;
    }

    public Optional<Region> execute(String countryCode, String Name ) {
        return regionService.deleteRegionByName(countryCode, Name);
    }
}
