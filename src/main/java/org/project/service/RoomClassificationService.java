package org.project.service;

import org.project.models.RoomClassification;

import java.util.List;

public interface RoomClassificationService {
    List<RoomClassification> getAllRoomClassifications();

    RoomClassification getRoomClassificationById(long id);

    void saveRoomClassification(RoomClassification roomClassification);

    void updateRoomClassification(long id, RoomClassification roomClassification);

    void deleteRoomClassification(long id);
}

