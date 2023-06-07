package org.project.service;

import org.project.models.City;
import org.project.models.Country;

import java.util.List;
import java.util.Set;

public interface CityService {
    City getCityById(long id);

    List<City> getAllCities();

//    Set<City> getCitiesByCountryId(long countryId);

    void saveCity(City city);

    void updateCity(long id,City city);

    void deleteCity(long id);
}
