package org.project.dao.implementation;

import org.hibernate.SessionFactory;
import org.project.dao.CityDAO;
import org.project.models.City;
import org.springframework.stereotype.Repository;

@Repository

public class CityDAOImpl extends GenericDAOImpl<City, Integer> implements CityDAO {

    public CityDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    // implementation of additional methods related to City can be added here
}
