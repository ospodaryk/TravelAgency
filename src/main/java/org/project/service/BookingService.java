package org.project.service;

import org.project.models.Booking;

import java.util.List;

public interface BookingService {
    List<Booking> getAllBookings();

    Booking getBookingById(long id);

    void saveBooking(long room_id, Booking booking);

    void updateBooking(long id, Booking booking);

    void deleteBooking(long id);

    void saveBookingWithoutRoom(Booking booking);

    List<Booking> getAllBookingsByUserId(Long userId);

    Booking prepareBookingForDate(Long userId, Long roomId, Long bookingId, Booking booking);

    Booking prepareBookingWithRoom(Long userId, Long roomId, Booking booking);
}