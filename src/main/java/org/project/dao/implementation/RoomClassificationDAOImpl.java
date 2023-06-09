package org.project.dao.implementation;

import org.hibernate.SessionFactory;
import org.project.dao.RoleDAO;
import org.project.dao.RoomClassificationDAO;
import org.project.models.*;
import org.springframework.stereotype.Repository;

import java.util.Iterator;

@Repository

public class RoomClassificationDAOImpl extends GenericDAOImpl<RoomClassification, Long> implements RoomClassificationDAO {

    public RoomClassificationDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public void delete(RoomClassification entity) {
        Long roomClassificationID = entity.getId();
        RoomClassification roomClassification = findById(roomClassificationID);

        if (roomClassification != null) {

            boolean isRoomClassificationUsed = false;

            for (Room room : roomClassification.getRooms()) {
                if (room.getBooking() != null) {
                    isRoomClassificationUsed = true;
                    break;
                }
            }
            if (!isRoomClassificationUsed) {
                getSession().delete(entity);
            } else {
                roomClassification.setActual(false);
                for (Room room : roomClassification.getRooms()) {
                    room.setActual(false);
                }
            }
        } else {
            throw new RuntimeException("Role not found.");
        }
    }
}