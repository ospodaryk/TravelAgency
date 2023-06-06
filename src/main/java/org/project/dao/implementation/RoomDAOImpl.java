package org.project.dao.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.project.dao.RoomDAO;
import org.project.models.Room;
import org.springframework.stereotype.Repository;

@Repository

public class RoomDAOImpl extends GenericDAOImpl<Room, Long> implements RoomDAO {
    private final SessionFactory sessionFactory;

    public RoomDAOImpl(SessionFactory sessionFactory, SessionFactory sessionFactory1) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory1;
    }


    @Override
    public void deleteByHotelId(long hotelId) {
        Session session = null;

        try {
            session = sessionFactory.getCurrentSession();
            String hql = "DELETE FROM Room WHERE hotel.id = :hotelId";
            Query<?> query = session.createQuery(hql);
            query.setParameter("hotelId", hotelId);
            query.executeUpdate();
        } catch (Exception e) {
            // Handle any exceptions
        }
    }
}