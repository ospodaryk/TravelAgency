package org.project.dao.implementation;

import org.hibernate.SessionFactory;
import org.project.dao.RoomDAO;
import org.project.models.Room;
import org.springframework.stereotype.Repository;

@Repository

public class RoomDAOImpl extends GenericDAOImpl<Room, Integer> implements RoomDAO {

    public RoomDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    // implementation of additional methods related to Country can be added here
}