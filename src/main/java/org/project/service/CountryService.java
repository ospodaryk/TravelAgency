package org.project.service;

import org.project.models.Country;
import org.project.models.Hotel;

import java.util.List;

public interface CountryService {
    List<Country> getAllCountries();

    Country getCountryById(long id);

    void saveCountry(Country country);

    void updateCountry(Country country);

    void deleteCountry(Country country);
}
