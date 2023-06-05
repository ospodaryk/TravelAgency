package org.project.service;

import org.project.models.City;

public interface CityService {
    City getCityById(long id);

    void saveCity(City city);

    void updateCity(City city);

    void deleteCity(City city);
}
