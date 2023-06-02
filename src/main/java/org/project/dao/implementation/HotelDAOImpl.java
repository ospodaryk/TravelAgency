package org.project.dao.implementation;

import org.hibernate.SessionFactory;
import org.project.dao.HotelDAO;
import org.project.models.Hotel;

public class HotelDAOImpl extends GenericDAOImpl<Hotel, Integer> implements HotelDAO {

    public HotelDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    // implementation of additional methods related to Country can be added here
}