package org.project.service;

import org.project.models.City;

import java.util.List;

public interface CityService {
    City getCityById(long id);

    List<City> getAllCities();

//    Set<City> getCitiesByCountryId(long countryId);

    void saveCity(City city);

    void updateCity(long id, City city);

    void deleteCity(long id);
}
