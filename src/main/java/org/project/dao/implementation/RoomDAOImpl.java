package org.project.dao.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.project.dao.RoomDAO;
import org.project.models.Room;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
            String hql = "FROM Room WHERE hotel.id = :hotelId AND isActual = true";
            Query<Room> query = session.createQuery(hql, Room.class);
            query.setParameter("hotelId", id);
            rooms = query.getResultList();
        } catch (Exception e) {
            // Handle any exceptions
            e.printStackTrace();
        }

        Map<Long, Room> roomMap = rooms.stream()
                .collect(Collectors.toMap(
                        room -> room.getRoomClassification().getId(),
                        room -> room,
                        (oldValue, newValue) -> oldValue)
                );

        // Return a new list of rooms, without duplicate roomClassification
        return new ArrayList<>(roomMap.values());
    }


    @Override
    public void deleteByClassificationId(long id) {
        Session session = null;

        try {
            session = sessionFactory.getCurrentSession();
            String hql = "DELETE FROM Room WHERE roomClassification.id = :id";
            Query<?> query = session.createQuery(hql);
            query.setParameter("id", id);
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
            String hql = "DELETE FROM Room WHERE hotel.id = :hotelId";
            Query<?> query = session.createQuery(hql);
            query.setParameter("hotelId", hotelId);
            query.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void deleteRoomIfNoBooking(Long roomId) {
        Room room = findById(roomId);
        if (room != null) {
            if (room.getBooking() == null) {
                delete(room);
            } else {
                throw new RuntimeException("Can't delete room with booking.");
            }
        } else {
            throw new RuntimeException("Room not found.");
        }
    }

    @Override
    public void delete(Room entity) {
        Room room = findById(entity.getRoomId());
        if (room != null) {
            if (room.getBooking() == null) {
                getSession().delete(entity);
            } else {
                room.setActual(false);
                room.getBooking().setActual(false);
            }
        } else {
            throw new RuntimeException("Room not found.");
        }


    }
}