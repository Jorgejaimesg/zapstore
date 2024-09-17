package com.zapstore.country.application;

import com.zapstore.country.domain.service.CountryService;

public class DeleteCountryUseCase {
    private final CountryService countryService;

    public DeleteCountryUseCase (CountryService countryService) {
        this.countryService = countryService;
    }

    public void execute(String Name) {
        countryService.deleteCountry(Name);
    }
}
