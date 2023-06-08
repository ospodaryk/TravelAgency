package org.project.dao;

import org.project.models.Country;

public interface CountryDAO extends GenericDAO<Country, Long> {
    public void deleteCountryById(long cityId);
}