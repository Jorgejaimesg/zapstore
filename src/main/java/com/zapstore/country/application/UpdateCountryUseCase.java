package com.zapstore.country.application;

import com.zapstore.country.domain.entity.Country;
import com.zapstore.country.domain.service.CountryService;

public class UpdateCountryUseCase {
    private final CountryService countryService;

    public UpdateCountryUseCase(CountryService countryService){
        this.countryService = countryService;
    }

    public void execute(Country country){
        countryService.updateCountry(country);
    }
}
