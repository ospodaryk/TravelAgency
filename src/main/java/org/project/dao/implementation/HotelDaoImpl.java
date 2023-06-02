package org.project.dao.implementation;

import org.hibernate.SessionFactory;
import org.project.dao.HotelDao;
import org.project.models.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HotelDaoImpl implements HotelDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Hotel user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    public Hotel get(long id) {
        return sessionFactory.getCurrentSession().get(Hotel.class, id);
    }

    // Implement other methods for CRUD operations...
}
