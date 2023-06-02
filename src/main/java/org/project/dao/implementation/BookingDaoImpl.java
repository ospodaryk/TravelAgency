package org.project.dao.implementation;

import org.hibernate.SessionFactory;
import org.project.dao.BookingDao;
import org.project.models.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookingDaoImpl implements BookingDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Booking user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    public Booking get(long id) {
        return sessionFactory.getCurrentSession().get(Booking.class, id);
    }

    //
}
