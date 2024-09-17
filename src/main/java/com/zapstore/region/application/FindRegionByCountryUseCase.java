package com.zapstore.region.application;

import java.util.List;

import com.zapstore.region.domain.entity.Region;
import com.zapstore.region.domain.service.RegionService;

public class FindRegionByCountryUseCase {
    private final RegionService regionService;

    public FindRegionByCountryUseCase(RegionService regionService) {
        this.regionService = regionService;
    }

    public List<Region> findAllRegionByCountry(String CountryID) {
        return regionService.findAllRegionByCountry(CountryID);
    }
}
