package org.project.service.implementation;

import org.project.dao.CityDAO;
import org.project.models.City;
import org.project.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {

    private CityDAO cityDAO;

    @Autowired

    public CityServiceImpl(CityDAO cityDAO) {
        this.cityDAO = cityDAO;
    }

    @Override
    public City getCityById(int id) {
        return cityDAO.findById(id);
    }

    @Override
    public void saveCity(City city) {
        cityDAO.save(city);
    }

    @Override
    public void updateCity(City city) {
        cityDAO.update(city);
    }

    @Override
    public void deleteCity(City city) {
        cityDAO.delete(city);
    }
}