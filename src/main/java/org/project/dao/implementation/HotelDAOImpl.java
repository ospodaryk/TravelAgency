package org.project.dao.implementation;

import org.hibernate.SessionFactory;
import org.project.dao.HotelDAO;
import org.project.models.Hotel;
import org.springframework.stereotype.Repository;

@Repository
public class HotelDAOImpl extends GenericDAOImpl<Hotel, Long> implements HotelDAO {

    public HotelDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public void delete(Hotel entity) {
//        Hotel hotel = findById(entity.getHotelId());
//        if(hotel != null) {
//            if(hotel.getBookings().isEmpty()) {
        getSession().delete(entity);
//            } else {
//                throw new RuntimeException("Can't delete hotel with references.");
//            }
//        } else {
//            throw new RuntimeException("Hotel not found.");
//        }
    }

    // implementation of additional methods related to Country can be added here
}