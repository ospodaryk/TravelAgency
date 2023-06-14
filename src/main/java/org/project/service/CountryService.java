package org.project.service;

import org.project.models.Country;

import java.util.List;

public interface CountryService {
    List<Country> getAllCountries();

    Country getCountryById(long id);

    void saveCountry(Country country);

    void updateCountry(long id, Country country);

    void deleteCountry(long id);
}
