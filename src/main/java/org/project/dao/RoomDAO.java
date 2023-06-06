package org.project.dao;

import org.project.models.Room;

public interface RoomDAO extends GenericDAO<Room, Long> {
    void deleteByHotelId(long hotelId);
}