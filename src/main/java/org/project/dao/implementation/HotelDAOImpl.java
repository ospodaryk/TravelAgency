package org.project.dao.implementation;

import org.hibernate.SessionFactory;
import org.project.dao.HotelDAO;
import org.project.models.Hotel;
import org.project.models.Room;
import org.project.models.User;
import org.springframework.stereotype.Repository;

import java.util.Iterator;

@Repository
public class HotelDAOImpl extends GenericDAOImpl<Hotel, Long> implements HotelDAO {

    public HotelDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public void delete(Hotel entity) {
        Long hotelId=entity.getHotelId();
        Hotel hotel = findById(hotelId);
        if(hotel != null) {
            if(hotel.getBookings().isEmpty()) {
                getSession().delete(entity);
            } else {
                hotel.setActual(false);
                for (Iterator<Room> it = hotel.getRooms().iterator(); it.hasNext(); ) {
                   Room room=it.next();
                   room.setActual(false);
                   room.setAvailable(false);
                }
            }
        } else {
            throw new RuntimeException("User not found.");
        }
    }

    // implementation of additional methods related to Country can be added here
}