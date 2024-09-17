package com.zapstore.region.domain.service;

import com.zapstore.region.domain.entity.Region;


import java.util.List;
import java.util.Optional;

public interface RegionService {
    void createRegion(Region Region);
    List<Region> findAllRegion();
    List<Region> findAllRegionByCountry(String CountryID);
    Optional<Region> deleteRegionByName(String CountryID, String name);
    Optional<Region> findRegionByName(String CountryID, String name);
    Optional<Region> findRegionByCode(String RegionID);
    void updateRegion(Region region);
}
