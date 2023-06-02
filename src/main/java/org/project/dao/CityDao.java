package org.project.dao;

import org.project.models.City;
import org.project.models.Role;

public interface CityDao {

    void save(City user);

    City get(long id);
}
