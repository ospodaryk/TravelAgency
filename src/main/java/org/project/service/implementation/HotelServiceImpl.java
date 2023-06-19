package org.project.service.implementation;

import org.project.dao.BookingDAO;
import org.project.dao.HotelDAO;
import org.project.dao.RoomDAO;
import org.project.dao.UserDAO;
import org.project.models.*;
import org.project.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Transactional
@Service
public class HotelServiceImpl implements HotelService {

    private HotelDAO hotelDAO;
    private RoomDAO roomDAO;
    private BookingDAO bookingDAO;
    private UserDAO userDAO;

    @Autowired
    public HotelServiceImpl(HotelDAO hotelDAO, RoomDAO roomDAO, BookingDAO bookingDAO, UserDAO userDAO) {
        this.hotelDAO = hotelDAO;
        this.roomDAO = roomDAO;
        this.bookingDAO = bookingDAO;
        this.userDAO = userDAO;
    }


    @Override
    public List<Hotel> getAllHotels() {
        return hotelDAO.getAll();
    }

    @Override
    public Hotel getHotelById(long id) {
        return hotelDAO.findById(id);
    }

    @Override
    public void saveHotel(Hotel hotel) {
        hotelDAO.save(hotel);
    }

    @Override
    public void updateHotel(long id, Hotel hotel) {
        Hotel existingHotel = hotelDAO.findById(id);

        if (existingHotel != null) {


            existingHotel.setHotelId(id);
            existingHotel.setName(hotel.getName());
            existingHotel.setCity(hotel.getCity());
            existingHotel.setDescription(hotel.getDescription());
            existingHotel.setLocation(hotel.getLocation());
            hotelDAO.update(existingHotel);
        }
    }


    @Override
    public void deleteHotel(long id) {
        hotelDAO.delete(hotelDAO.findById(id));
    }

    @Override
    public List<Hotel> getAvailableHotels(String startDate, String endDate) {
        return hotelDAO.getAvailableHotels(startDate, endDate);
    }

    @Override
    public Map<Hotel, List<Room>> searchHotels(HotelSearchForm searchForm, long userId) {
        String startDateStr = searchForm.getStartDate();
        String endDateStr = searchForm.getEndDate();

        Date startDate = null;
        Date endDate = null;
        List<Hotel> hotels = getAvailableHotels(startDateStr, endDateStr);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            startDate = format.parse(startDateStr);
            endDate = format.parse(endDateStr);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format.", e);
        }

        Map<Hotel, List<Room>> uniqueRoomHotels = new HashMap<>();
        Booking booking = new Booking();
        booking.setUser(userDAO.findById(userId));
        booking.setStart_date(startDate);
        booking.setEnd_date(endDate);

        for (Hotel hotel : hotels) {
            Set<RoomClassification> classificationsSeen = new HashSet<>();
            List<Room> uniqueRooms = new ArrayList<>();
            for (Room room : hotel.getRooms()) {
                if (room.isActual()) {
                    if (classificationsSeen.add(room.getRoomClassification())) {
                        uniqueRooms.add(room);
                    }
                }
            }
            uniqueRoomHotels.put(hotel, uniqueRooms);
        }

        bookingDAO.save(booking);
        return uniqueRoomHotels;
    }
}