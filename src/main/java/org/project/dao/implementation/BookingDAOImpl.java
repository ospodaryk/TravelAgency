package org.project.dao.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.project.dao.BookingDAO;
import org.project.models.Booking;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookingDAOImpl extends GenericDAOImpl<Booking, Long> implements BookingDAO {


    private final SessionFactory sessionFactory;

    public BookingDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Booking> findAllByUser(long user_id) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Booking WHERE user.id = :user_id";
        Query<Booking> query = session.createQuery(hql, Booking.class);
        query.setParameter("user_id", user_id);
        return query.getResultList();
    }

    @Override

    public void deleteByUserId(long userId) {
        Session session = null;

        try {
            session = sessionFactory.getCurrentSession();
            String hql = "DELETE FROM Booking WHERE user.id = :userId";
            Query<?> query = session.createQuery(hql);
            query.setParameter("userId", userId);
            query.executeUpdate();
        } catch (Exception e) {
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
        }
    }

    @Override
    public void delete(Booking entity) {
        entity.setActual(false);
    }
}