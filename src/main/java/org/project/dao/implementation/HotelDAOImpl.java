package org.project.dao.implementation;

import org.hibernate.SessionFactory;
import org.project.configuration.security.StringToLocalDateConverter;
import org.project.dao.HotelDAO;
import org.project.models.Hotel;
import org.project.models.Room;
import org.project.models.User;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Repository
public class HotelDAOImpl extends GenericDAOImpl<Hotel, Long> implements HotelDAO {

    public HotelDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public void delete(Hotel entity) {
        Long hotelId = entity.getHotelId();
        Hotel hotel = findById(hotelId);
        if (hotel != null) {
            if (hotel.getBookings().isEmpty()) {
                getSession().delete(entity);
            } else {
                hotel.setActual(false);
                for (Iterator<Room> it = hotel.getRooms().iterator(); it.hasNext(); ) {
                    Room room = it.next();
                    room.setActual(false);
                    room.setAvailable(false);
                }
            }
        } else {
            throw new RuntimeException("User not found.");
        }
    }

    @Override
    public List<Hotel> getAvailableHotels(String startDate, String endDate) {
        StringToLocalDateConverter converter = new StringToLocalDateConverter();
        LocalDate startLocalDate = converter.convert(startDate);
        LocalDate endLocalDate = converter.convert(endDate);

        Date start = java.sql.Date.valueOf(startLocalDate);
        Date end = java.sql.Date.valueOf(endLocalDate);

        String query = "SELECT DISTINCT h FROM Hotel h " +
                "JOIN h.rooms r " +
                "LEFT JOIN r.booking b " +
                "WHERE (b IS NULL OR (b.start_date NOT BETWEEN :startDate AND :endDate " +
                "AND b.end_date NOT BETWEEN :startDate AND :endDate)) " +
                "AND h.isActual = true AND r.isAvailable = true";

        return getSession().createQuery(query, Hotel.class)
                .setParameter("startDate", start)
                .setParameter("endDate", end)
                .getResultList();
    }


}