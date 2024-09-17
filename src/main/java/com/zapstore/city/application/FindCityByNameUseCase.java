package com.zapstore.city.application;

import java.util.Optional;

import com.zapstore.city.domain.entity.City;
import com.zapstore.city.domain.service.CityService;

public class FindCityByNameUseCase {
    private final CityService cityService;

    public FindCityByNameUseCase(CityService cityService) {
        this.cityService = cityService;
    }

    public Optional<City> execute(String RegionID, String Name ) {
        return cityService.findCityByName(RegionID, Name);
    }
}
