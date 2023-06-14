package org.project.dao.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.project.dao.CityDAO;
import org.project.models.Booking;
import org.project.models.City;
import org.project.models.Hotel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CityDAOImpl extends GenericDAOImpl<City, Long> implements CityDAO {
    private final SessionFactory sessionFactory;

    public CityDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    private List<Hotel> getHotelsByCityId(long cityId) {
        Session session = sessionFactory.getCurrentSession();

        String hqlHotel = "FROM Hotel WHERE city.cityId = :cityId";
        Query<Hotel> queryHotel = session.createQuery(hqlHotel, Hotel.class);
        queryHotel.setParameter("cityId", cityId);

        return queryHotel.getResultList();
    }

    public void deleteCityById(long cityId) {
        Session session = sessionFactory.getCurrentSession();

        List<Hotel> hotels = getHotelsByCityId(cityId);

        String hqlBooking = "DELETE FROM Booking WHERE hotel.hotelId = :hotelId";
        String hqlRoom = "DELETE FROM Room WHERE hotel.hotelId = :hotelId";
        for (Hotel hotel : hotels) {
            Query<?> queryBooking = session.createQuery(hqlBooking);
            queryBooking.setParameter("hotelId", hotel.getHotelId());
            queryBooking.executeUpdate();

            Query<?> queryRoom = session.createQuery(hqlRoom);
            queryRoom.setParameter("hotelId", hotel.getHotelId());
            queryRoom.executeUpdate();
        }

        String hqlHotel = "DELETE FROM Hotel WHERE city.cityId = :cityId";
        Query<?> queryHotel = session.createQuery(hqlHotel);
        queryHotel.setParameter("cityId", cityId);
        queryHotel.executeUpdate();

    }

    @Override
    public void delete(City entity) {
        Long cityId = entity.getCityId();
        City city = findById(cityId);
        if (city != null) {
            boolean isCityUsed = false;
            for (Hotel hotel : city.getHotels()) {
                for (Booking booking : hotel.getBookings()) {
                    if (!booking.getRooms().isEmpty()) {
                        isCityUsed = true;
                        break;
                    }
                }
                if (isCityUsed) {
                    break;
                }
            }
            if (!isCityUsed) {
                getSession().delete(entity);
            } else {
                city.setActual(false);
                for (Hotel hotel : city.getHotels()) {
                    for (Booking booking : hotel.getBookings()) {
                        booking.setActual(false);
                    }
                    hotel.setActual(false);
                }
            }
        } else {
            throw new RuntimeException("City not found.");
        }
    }

}
