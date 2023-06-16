package org.project.service.implementation;

import org.project.dao.HotelDAO;
import org.project.dao.RoomDAO;
import org.project.models.Room;
import org.project.service.RoomService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {

    private final RoomDAO roomDAO;
    private final HotelDAO hotelDAO;


    public RoomServiceImpl(RoomDAO roomDAO, HotelDAO hotelDAO) {
        this.roomDAO = roomDAO;
        this.hotelDAO = hotelDAO;
    }

    @Override
    public List<Room> getAllRooms() {
        return roomDAO.getAll();
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
    public void updateRoom(long id, Room room) {
        Room existingRoom = roomDAO.findById(id);

        if (existingRoom != null) {
            existingRoom.setRoomId(id);
            existingRoom.setCapacity(room.getCapacity());
            existingRoom.setPrice(room.getPrice());
            existingRoom.setNumber(room.getNumber());
            existingRoom.setHotel(room.getHotel());
            existingRoom.setRoomClassification(room.getRoomClassification());
            roomDAO.update(existingRoom);
        }
    }

    @Override
    public List<Room> getRoomByHotelID(long id) {
        return roomDAO.getRoomByHotelID(id);
    }

    @Override
    public void deleteRoom(long id) {
        roomDAO.delete(roomDAO.findById(id));
    }
}