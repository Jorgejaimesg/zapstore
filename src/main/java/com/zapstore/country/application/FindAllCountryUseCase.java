package com.zapstore.country.application;

import java.util.List;

import com.zapstore.country.domain.entity.Country;
import com.zapstore.country.domain.service.CountryService;

public class FindAllCountryUseCase {
    private final CountryService countryService;

    public FindAllCountryUseCase(CountryService countryService) {
        this.countryService = countryService;
    }

    public List<Country> findAllCountry() {
        return countryService.findAllCountry();
    }
}
