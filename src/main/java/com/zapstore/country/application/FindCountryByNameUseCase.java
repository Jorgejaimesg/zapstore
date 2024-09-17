package com.zapstore.country.application;

import java.util.Optional;

import com.zapstore.country.domain.entity.Country;
import com.zapstore.country.domain.service.CountryService;

public class FindCountryByNameUseCase {
    private final CountryService countryService;

    public FindCountryByNameUseCase(CountryService countryService) {
        this.countryService = countryService;
    }

    public Optional<Country> findCountryByName(String countryName) {
        return countryService.findCountryByName(countryName);
    }
}
