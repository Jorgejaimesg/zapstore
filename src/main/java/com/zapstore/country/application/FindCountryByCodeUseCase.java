package com.zapstore.country.application;

import java.util.Optional;

import com.zapstore.country.domain.entity.Country;
import com.zapstore.country.domain.service.CountryService;

public class FindCountryByCodeUseCase {
    private final CountryService countryService;
    public FindCountryByCodeUseCase(CountryService countryService) {
        this.countryService = countryService;
    }

    public Optional<Country> findCountryByCode(String codeCountry) {
        return countryService.findCountryByCode(codeCountry);
    }
}
