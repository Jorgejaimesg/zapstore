package com.zapstore.city.application;

import java.util.Optional;
import com.zapstore.city.domain.entity.City;
import com.zapstore.city.domain.service.CityService;

public class DeleteCityByNameUseCase {
    private final CityService CityService;

    public DeleteCityByNameUseCase(CityService CityService) {
        this.CityService = CityService;
    }

    public Optional<City> execute(String regionCode, String Name ) {
        return CityService.deleteCityByName(regionCode, Name);
    }
}
