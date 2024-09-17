package com.zapstore.city.application;

import com.zapstore.city.domain.entity.City;
import com.zapstore.city.domain.service.CityService;

public class UpdateCityUseCase {
    private final CityService cityService;

    public UpdateCityUseCase(CityService cityService){
        this.cityService = cityService;
    }

    public void execute(City city){
        cityService.updateCity(city);
    }
}
