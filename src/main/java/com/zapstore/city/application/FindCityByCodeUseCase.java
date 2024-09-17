package com.zapstore.city.application;

import java.util.Optional;

import com.zapstore.city.domain.entity.City;
import com.zapstore.city.domain.service.CityService;

public class FindCityByCodeUseCase {
    private final CityService cityService;

    public FindCityByCodeUseCase(CityService cityService) {
        this.cityService = cityService;
    }

    public Optional<City> findCityByCode(String CityBox) {
        return cityService.findCityByCode(CityBox);
    }
}
