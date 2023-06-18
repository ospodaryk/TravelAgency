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
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.time.Duration;
import java.time.LocalDateTime;

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
            return bookingDAO.findAllByUser(userId).stream().filter(onj->onj.getHotel()!=null).toList();
        } else {
            throw new RuntimeException("User not found with id: " + userId);
        }
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingDAO.getAll().stream().filter(obj->obj.getHotel()!=null).toList();
    }

    @Override
    public Booking getBookingById(long id) {
        return bookingDAO.findById(id);
    }

    @Override
    public void saveBooking(long room_id, Booking booking) {
        Room room = roomDAO.findById(room_id);
        room.setBooking(booking);
        booking.setActual(true);
        booking.setNumOfPeople(room.getCapacity());
        roomDAO.save(room);
        bookingDAO.save(booking);
    }

    @Override
    public void saveBookingWithoutRoom(Booking booking) {
        booking.setActual(true);
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

    @Override
    public Booking prepareBookingWithRoom(Long userId, Long roomId, Booking booking) {
        User user = userDAO.findById(userId);
        Room room = roomDAO.findById(roomId);
        booking.setNumOfPeople(room.getCapacity());
        booking.setActual(true);
        if (user != null && room != null) {
            booking.setUser(user);
            booking.getRooms().add(room);
            booking.setHotel(room.getHotel());
//            long daysBetween = ChronoUnit.DAYS.between(booking.getStart_date().toInstant(), booking.getEnd_date().toInstant());
            booking.setTotalPrice(room.getPrice() * booking.getTotalPrice());
            room.setBooking(booking);
            roomDAO.save(room);
            bookingDAO.update(booking);
            return booking;
        } else {
            throw new RuntimeException("User or Room not found");
        }
    }

    @Override
    public Booking prepareBookingForDate(Long userId, Long roomId, Long bookingId, Booking booking) {
        User user = userDAO.findById(userId);
        Room room = roomDAO.findById(roomId);
        Booking existingBooking = bookingDAO.findById(bookingId);

        if (user != null && room != null && existingBooking != null) {
            existingBooking.setUser(user);
            existingBooking.getRooms().add(room);
            existingBooking.setHotel(room.getHotel());
            existingBooking.setTotalPrice(room.getPrice()*existingBooking.getTotalPrice());
//            existingBooking.setTotalPrice(room.getPrice());
//            room.setActual(false);
            existingBooking.setNumOfPeople(room.getCapacity());
            existingBooking.getRooms().add(room);
            room.setBooking(existingBooking);
            roomDAO.save(room);
            bookingDAO.update(existingBooking);
            return existingBooking;
        } else {
            throw new RuntimeException("User, Room or Booking not found");
        }
    }
}