package org.project.service.implementation;

import org.project.dao.CountryDAO;
import org.project.models.Country;
import org.project.service.CountryService;

public class CountryServiceImpl implements CountryService {

    private CountryDAO countryDAO;

    public CountryServiceImpl(CountryDAO countryDAO) {
        this.countryDAO = countryDAO;
    }

    @Override
    public Country getCountryById(int id) {
        return countryDAO.findById(id);
    }

    @Override
    public void saveCountry(Country country) {
        countryDAO.save(country);
    }

    @Override
    public void updateCountry(Country country) {
        countryDAO.update(country);
    }

    @Override
    public void deleteCountry(Country country) {
        countryDAO.delete(country);
    }
}
