package org.project.dao;

import org.project.models.Hotel;

public interface HotelDao {

    void save(Hotel user);

    Hotel get(long id);
}
