package com.zapstore.country.domain.service;

import java.util.List;
import java.util.Optional;

import com.zapstore.country.domain.entity.Country;

public interface CountryService {
    void createCountry(Country country);
    void updateCountry(Country country);
    void deleteCountry(String name);
    Optional<Country> findCountryByName (String name);
    Optional<Country> findCountryByCode (String codeCountry);
    List<Country> findAllCountry();
}
