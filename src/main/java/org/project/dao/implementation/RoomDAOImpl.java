package org.project.dao.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.project.dao.RoomDAO;
import org.project.models.Room;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository

public class RoomDAOImpl extends GenericDAOImpl<Room, Long> implements RoomDAO {
    private final SessionFactory sessionFactory;

    public RoomDAOImpl(SessionFactory sessionFactory, SessionFactory sessionFactory1) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory1;
    }

    @Override
    public List<Room> getRoomByHotelID(long id) {
        Session session = null;
        List<Room> rooms = new ArrayList<>();

        try {
            session = sessionFactory.getCurrentSession();
            String hql = "FROM Room WHERE hotel.id = :hotelId";
            Query<Room> query = session.createQuery(hql, Room.class);
            query.setParameter("hotelId", id);
            rooms = query.getResultList();
        } catch (Exception e) {
            // Handle any exceptions
            e.printStackTrace();
        }

        return rooms;
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