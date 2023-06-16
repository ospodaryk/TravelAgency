package org.project.dao;

import org.project.models.Booking;

import java.util.List;

public interface BookingDAO extends GenericDAO<Booking, Long> {
    void deleteByUserId(long userId);

    void deleteByHotelId(long hotelId);

    List<Booking> findAllByUser(long user_id);
}
