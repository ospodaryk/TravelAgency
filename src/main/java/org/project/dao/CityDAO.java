package org.project.dao;

import org.project.models.City;

public interface CityDAO extends GenericDAO<City, Long> {
    public void deleteCityById(long cityId);
}
