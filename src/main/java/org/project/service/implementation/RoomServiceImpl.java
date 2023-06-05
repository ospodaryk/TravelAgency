package org.project.service.implementation;

import org.project.dao.RoomDAO;
import org.project.models.Room;
import org.project.service.RoomService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {

    private final RoomDAO roomDAO;

    public RoomServiceImpl(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }

    @Override
    public Room getRoomById(long roomId) {
        return roomDAO.findById(roomId);
    }

    @Override
    public void saveRoom(Room room) {
        roomDAO.save(room);
    }

    @Override
    public void updateRoom(Room room) {
        roomDAO.update(room);
    }

    @Override
    public void deleteRoom(Room room) {
        roomDAO.delete(room);
    }
}