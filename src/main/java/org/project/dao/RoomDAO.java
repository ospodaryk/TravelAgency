package org.project.dao;

import org.project.models.Room;

import java.util.List;

public interface RoomDAO extends GenericDAO<Room, Long> {
    void deleteByHotelId(long hotelId);

    List<Room> getRoomByHotelID(long id);
    void deleteByClassificationId(long id);

}