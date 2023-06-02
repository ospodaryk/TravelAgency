package org.project.dao.implementation;

import org.hibernate.SessionFactory;
import org.project.dao.BookingDAO;
import org.project.models.Booking;
import org.springframework.stereotype.Repository;

@Repository
public class BookingDAOImpl extends GenericDAOImpl<Booking, Integer> implements BookingDAO {

    public BookingDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    // implementation of additional methods related to Booking can be added here
}