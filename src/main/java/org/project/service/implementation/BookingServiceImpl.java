package org.project.service.implementation;

import org.project.dao.BookingDAO;
import org.project.models.Booking;
import org.project.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional

@Service
public class BookingServiceImpl implements BookingService {

    private BookingDAO bookingDAO;

    @Autowired
    public BookingServiceImpl(BookingDAO bookingDAO) {
        this.bookingDAO = bookingDAO;
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingDAO.getAll();
    }

    @Override
    public Booking getBookingById(long id) {
        return bookingDAO.findById(id);
    }

    @Override
    public void saveBooking(Booking booking) {
        bookingDAO.save(booking);
    }

    @Override
    public void updateBooking(long id, Booking booking) {
        booking.setBookingId(id);
        bookingDAO.update(booking);
    }

    @Override
    public void deleteBooking(long id) {
        bookingDAO.delete(bookingDAO.findById(id));
    }
}