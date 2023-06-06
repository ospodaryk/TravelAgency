package org.project.service.implementation;

import org.project.dao.CityDAO;
import org.project.dao.CountryDAO;
import org.project.models.City;
import org.project.models.Country;
import org.project.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

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


//    @Override
//    public Set<City> getCitiesByCountryId(long countryId) {
//        return countryDAO.findById(countryId).getCities();
//    }
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