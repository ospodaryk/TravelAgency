package org.project.dao;

import org.project.models.Booking;

public interface BookingDao {

    void save(Booking user);

    Booking get(long id);
}
