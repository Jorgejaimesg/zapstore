package com.zapstore.city.application;

import java.util.List;

import com.zapstore.city.domain.entity.City;
import com.zapstore.city.domain.service.CityService;

public class FindCityByRegionUseCase {
    private final CityService cityService;

    public FindCityByRegionUseCase(CityService cityService) {
        this.cityService = cityService;
    }

    public List<City> findAllCityByRegion(String RegionID) {
        return cityService.findAllCityByRegion(RegionID);
    }
}
