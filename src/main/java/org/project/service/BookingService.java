package org.project.service;

import org.project.models.Booking;
import org.project.models.Room;

import java.util.List;

public interface BookingService {
    List<Booking> getAllBookings();

    Booking getBookingById(long id);

    void saveBooking(Booking booking);

    void updateBooking(long id,Booking booking);

    void deleteBooking(long id);
}