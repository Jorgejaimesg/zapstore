package com.zapstore.city.application;

import com.zapstore.city.domain.entity.City;
import com.zapstore.city.domain.service.CityService;

public class CreateCityUseCase {
    private final CityService cityService;

    public CreateCityUseCase(CityService cityService){
        this.cityService = cityService;
    }

    public void execute(City city){
        cityService.createCity(city);
}
}
