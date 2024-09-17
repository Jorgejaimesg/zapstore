package com.zapstore.country.application;

import com.zapstore.country.domain.entity.Country;
import com.zapstore.country.domain.service.CountryService;

public class CreateCountryUseCase {
    private final CountryService countryService;

    public CreateCountryUseCase(CountryService countryService){
        this.countryService = countryService;
    }

    public void execute(Country Country){
        countryService.createCountry(Country);
    }
}
