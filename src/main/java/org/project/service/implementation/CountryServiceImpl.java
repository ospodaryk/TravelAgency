package org.project.service.implementation;

import org.project.dao.CountryDAO;
import org.project.models.Country;
import org.project.models.Hotel;
import org.project.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional

@Service
public class CountryServiceImpl implements CountryService {

    private CountryDAO countryDAO;

    @Autowired
    public CountryServiceImpl(CountryDAO countryDAO) {
        this.countryDAO = countryDAO;
    }

    @Override
    public List<Country> getAllCountries() {
        return countryDAO.getAll();
    }


    @Override
    public Country getCountryById(long id) {
        return countryDAO.findById(id);
    }

    @Override
    public void saveCountry(Country country) {
        countryDAO.save(country);
    }

    @Override
    public void updateCountry(long id,Country country) {
        country.setCountryId(id);
        countryDAO.update(country);
    }

    @Override
    public void deleteCountry(long id) {
        countryDAO.delete(countryDAO.findById(id));
    }
}
