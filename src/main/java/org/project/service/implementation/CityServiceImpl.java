package org.project.service.implementation;

import org.project.dao.CityDAO;
import org.project.dao.CountryDAO;
import org.project.models.City;
import org.project.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional

@Service
public class CityServiceImpl implements CityService {

    private CityDAO cityDAO;
    private CountryDAO countryDAO;

    @Autowired
    public CityServiceImpl(CityDAO cityDAO, CountryDAO countryDAO) {
        this.cityDAO = cityDAO;
        this.countryDAO = countryDAO;
    }

    @Override
    public List<City> getAllCities() {
        return cityDAO.getAll();
    }

    @Override
    public City getCityById(long id) {
        return cityDAO.findById(id);
    }

    @Override
    public void saveCity(City city) {
        city.setActual(true);

        cityDAO.save(city);
    }

    @Override
    public void updateCity(long id, City city) {
        city.setCityId(id);
        cityDAO.update(city);
    }

    @Override
    public void deleteCity(long id) {
//        cityDAO.deleteCityById(id);
        cityDAO.delete(cityDAO.findById(id));
    }
}