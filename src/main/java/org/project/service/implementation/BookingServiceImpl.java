package org.project.service.implementation;

import org.project.dao.BookingDAO;
import org.project.dao.RoomDAO;
import org.project.dao.UserDAO;
import org.project.models.Booking;
import org.project.models.Room;
import org.project.models.User;
import org.project.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class BookingServiceImpl implements BookingService {

    private BookingDAO bookingDAO;
    private RoomDAO roomDAO;
    private UserDAO userDAO;

    @Autowired
    public BookingServiceImpl(BookingDAO bookingDAO, RoomDAO roomDAO, UserDAO userDAO) {
        this.bookingDAO = bookingDAO;
        this.roomDAO = roomDAO;
        this.userDAO = userDAO;
    }


    @Override
    public List<Booking> getAllBookingsByUserId(Long userId) {
        User user = userDAO.findById(userId);
        if (user != null) {
            return bookingDAO.findAllByUser(userId);
        } else {
            throw new RuntimeException("User not found with id: " + userId);
        }
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
    public void saveBooking(long room_id, Booking booking) {
        Room room = roomDAO.findById(room_id);
        room.setBooking(booking);
        roomDAO.save(room);
        bookingDAO.save(booking);
    }

    @Override
    public void saveBookingWithoutRoom(Booking booking) {
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