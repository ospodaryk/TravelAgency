package org.project.dao.implementation;

import org.hibernate.SessionFactory;
import org.project.dao.CountryDao;
import org.project.models.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CountryDaoImpl implements CountryDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Country user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    public Country get(long id) {
        return sessionFactory.getCurrentSession().get(Country.class, id);
    }

    //
}
