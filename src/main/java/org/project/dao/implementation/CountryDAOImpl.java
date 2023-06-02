package org.project.dao.implementation;

import org.hibernate.SessionFactory;
import org.project.dao.CountryDAO;
import org.project.models.Country;

public class CountryDAOImpl extends GenericDAOImpl<Country, Integer> implements CountryDAO {

    public CountryDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    // implementation of additional methods related to Country can be added here
}