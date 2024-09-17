package com.zapstore.city.application;

import java.util.List;

import com.zapstore.city.domain.entity.CityShow;
import com.zapstore.city.domain.service.CityService;

public class FindAllCityUseCase {
        private final CityService cityService;

    public FindAllCityUseCase(CityService cityService) {
        this.cityService = cityService;
    }

    public List<CityShow> findAllCity() {
        return cityService.findAllCity();
    }

}
