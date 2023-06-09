package org.project.dao.implementation;

import org.hibernate.SessionFactory;
import org.project.dao.CityDAO;
import org.project.models.City;
import org.springframework.stereotype.Repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.project.dao.CityDAO;
import org.project.models.Booking;
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
        getSession().delete(entity);
//        City city = findById(entity.getCityId());
//        if(city != null) {
//            boolean hasHotelReference = city.getHotels().stream()
//                    .anyMatch(hotel -> !hotel.getBookings().isEmpty());
//
//            if(!hasHotelReference) {
//                delete(city);
//            } else {
//                city.setActual(false);
//                throw new RuntimeException("Can't delete city with hotels that have bookings.");
//            }
//        } else {
//            throw new RuntimeException("City not found.");
//        }
    }
}
