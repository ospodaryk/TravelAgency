package org.project.dao.implementation;

import org.hibernate.SessionFactory;
import org.project.dao.BookingDao;
import org.project.dao.CityDao;
import org.project.models.Booking;
import org.project.models.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CityDaoImpl implements CityDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(City user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    public City get(long id) {
        return sessionFactory.getCurrentSession().get(City.class, id);
    }
}
