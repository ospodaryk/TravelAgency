package org.project.dao;

import org.project.models.Country;

public interface CountryDao {

    void save(Country user);

    Country get(long id);
}
