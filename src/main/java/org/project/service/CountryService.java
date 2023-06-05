package org.project.service;

import org.project.models.Country;

public interface CountryService {
    Country getCountryById(long id);

    void saveCountry(Country country);

    void updateCountry(Country country);

    void deleteCountry(Country country);
}
