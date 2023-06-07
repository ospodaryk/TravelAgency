package org.project.service.implementation;

import org.project.dao.RoomClassificationDAO;
import org.project.dao.RoomDAO;
import org.project.models.RoomClassification;
import org.project.service.RoomClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoomClassificationServiceImpl implements RoomClassificationService {

    private final RoomClassificationDAO roomClassificationDAO;
    private final RoomDAO roomDAO;

    @Autowired
    public RoomClassificationServiceImpl(RoomClassificationDAO roomClassificationDAO, RoomDAO roomDAO) {
        this.roomClassificationDAO = roomClassificationDAO;
        this.roomDAO = roomDAO;
    }

    @Override
    public List<RoomClassification> getAllRoomClassifications() {
        return roomClassificationDAO.getAll();
    }

    @Override
    public RoomClassification getRoomClassificationById(long id) {
        return roomClassificationDAO.findById(id);
    }

    @Override
    public void saveRoomClassification(RoomClassification roomClassification) {
        roomClassificationDAO.save(roomClassification);
    }

    @Override
    public void updateRoomClassification(long id,RoomClassification roomClassification) {
        RoomClassification existingRoomClassification = roomClassificationDAO.findById(id);

        if (existingRoomClassification != null) {
            existingRoomClassification.setId(id);
            existingRoomClassification.setName(roomClassification.getName());
            existingRoomClassification.setRooms(roomClassification.getRooms());
            roomClassificationDAO.update(existingRoomClassification);
        }

    }

    @Override
    public void deleteRoomClassification(long id) {
        roomDAO.deleteByClassificationId(id);
        roomClassificationDAO.delete(roomClassificationDAO.findById(id));
    }
}
