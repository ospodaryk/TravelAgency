package org.project.dao;

import org.project.models.Room;

public interface RoomDao {

    void save(Room user);

    Room get(long id);
}
