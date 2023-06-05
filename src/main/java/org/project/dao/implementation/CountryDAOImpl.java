package org.project.dao.implementation;

import org.hibernate.SessionFactory;
import org.project.dao.CountryDAO;
import org.project.models.Country;
import org.springframework.stereotype.Repository;

@Repository

public class CountryDAOImpl extends GenericDAOImpl<Country, Long> implements CountryDAO {

    public CountryDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    // implementation of additional methods related to Country can be added here
}