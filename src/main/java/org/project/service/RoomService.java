package org.project.service;

import org.project.models.Room;

import java.util.List;

public interface RoomService {
    List<Room> getAllRooms();

    Room getRoomById(long roomId);

    List<Room> getRoomByHotelID(long id);

    void saveRoom(Room room);

    void updateRoom(long roomId, Room room);

    void deleteRoom(long roomId);
}