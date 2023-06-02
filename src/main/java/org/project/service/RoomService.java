package org.project.service;

import org.project.models.Room;

public interface RoomService {
    Room getRoomById(int roomId);
    void saveRoom(Room room);
    void updateRoom(Room room);
    void deleteRoom(Room room);
}