package org.project.service;

import org.project.models.Booking;

public interface BookingService {
    Booking getBookingById(int id);
    void saveBooking(Booking booking);
    void updateBooking(Booking booking);
    void deleteBooking(Booking booking);
}