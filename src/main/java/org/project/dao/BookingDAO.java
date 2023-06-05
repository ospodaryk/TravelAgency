package org.project.dao;

import org.project.models.Booking;

public interface BookingDAO extends GenericDAO<Booking, Long> {
    void deleteByUserId(long userId);
}
