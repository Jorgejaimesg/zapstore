package com.zapstore.city.domain.service;

import java.util.List;
import java.util.Optional;

import com.zapstore.city.domain.entity.City;
import com.zapstore.city.domain.entity.CityShow;

public interface CityService {
    void createCity(City City);
    List<CityShow> findAllCity();
    List<City> findAllCityByRegion(String RegionID);
    Optional<City> deleteCityByName(String RegionID, String name);
    Optional<City> findCityByName(String RegionID, String name);
    Optional<City> findCityByCode(String CityID);
    void updateCity(City City);

}
