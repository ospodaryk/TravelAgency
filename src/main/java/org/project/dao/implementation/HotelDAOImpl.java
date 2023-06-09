package org.project.dao.implementation;

import org.hibernate.SessionFactory;
import org.project.dao.HotelDAO;
import org.project.models.Hotel;
import org.project.models.User;
import org.springframework.stereotype.Repository;

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
//                throw new RuntimeException("Can't delete user with references.");
            }
        } else {
            throw new RuntimeException("User not found.");
        }
    }

    // implementation of additional methods related to Country can be added here
}