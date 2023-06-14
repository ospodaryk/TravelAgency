package org.project.dao.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.project.dao.BookingDAO;
import org.project.models.Booking;
import org.springframework.stereotype.Repository;

@Repository
public class BookingDAOImpl extends GenericDAOImpl<Booking, Long> implements BookingDAO {


    private final SessionFactory sessionFactory;

    public BookingDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    public void deleteByUserId(long userId) {
        Session session = null;

        try {
            session = sessionFactory.getCurrentSession();
            String hql = "DELETE FROM Booking WHERE user.id = :userId";
            Query<?> query = session.createQuery(hql);
            query.setParameter("userId", userId);
            query.executeUpdate();
        } catch (Exception e) {
            // Handle any exceptions
        }
    }

    @Override
    public void deleteByHotelId(long hotelId) {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            String hql = "DELETE FROM Booking WHERE hotel.id = :hotelId";
            Query<?> query = session.createQuery(hql);
            query.setParameter("hotelId", hotelId);
            query.executeUpdate();
        } catch (Exception e) {
            // Handle any exceptions
        }
    }

    @Override
    public void delete(Booking entity) {
//        getSession().delete(entity);
        entity.setActual(false);
    }
}