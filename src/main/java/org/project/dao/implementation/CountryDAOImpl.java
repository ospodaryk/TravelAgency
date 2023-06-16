package org.project.dao.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.project.dao.CountryDAO;
import org.project.models.Booking;
import org.project.models.City;
import org.project.models.Country;
import org.project.models.Hotel;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CountryDAOImpl extends GenericDAOImpl<Country, Long> implements CountryDAO {
    private final SessionFactory sessionFactory;

    public CountryDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    private List<City> getCitiesByCountryId(long countryId) {
        Session session = sessionFactory.getCurrentSession();

        String hqlCity = "FROM City WHERE country.countryId = :countryId";
        Query<City> queryCity = session.createQuery(hqlCity, City.class);
        queryCity.setParameter("countryId", countryId);

        return queryCity.getResultList();
    }

    public void deleteCountryById(long countryId) {
        Session session = sessionFactory.getCurrentSession();

        List<City> cities = getCitiesByCountryId(countryId);

        for (City city : cities) {
            String hqlHotelsInCity = "SELECT h.hotelId FROM Hotel h WHERE h.city.cityId = :cityId";
            Query<Long> queryHotelsInCity = session.createQuery(hqlHotelsInCity, Long.class);
            queryHotelsInCity.setParameter("cityId", city.getCityId());
            List<Long> hotelIds = queryHotelsInCity.getResultList();

            for (Long hotelId : hotelIds) {
                String hqlDeleteBookings = "DELETE FROM Booking WHERE hotel.hotelId = :hotelId";
                Query<?> queryDeleteBookings = session.createQuery(hqlDeleteBookings);
                queryDeleteBookings.setParameter("hotelId", hotelId);
                queryDeleteBookings.executeUpdate();

                String hqlDeleteRooms = "DELETE FROM Room WHERE hotel.hotelId = :hotelId";
                Query<?> queryDeleteRooms = session.createQuery(hqlDeleteRooms);
                queryDeleteRooms.setParameter("hotelId", hotelId);
                queryDeleteRooms.executeUpdate();

                String hqlDeleteHotel = "DELETE FROM Hotel WHERE hotelId = :hotelId";
                Query<?> queryDeleteHotel = session.createQuery(hqlDeleteHotel);
                queryDeleteHotel.setParameter("hotelId", hotelId);
                queryDeleteHotel.executeUpdate();
            }

            String hqlDeleteCity = "DELETE FROM City WHERE cityId = :cityId";
            Query<?> queryDeleteCity = session.createQuery(hqlDeleteCity);
            queryDeleteCity.setParameter("cityId", city.getCityId());
            queryDeleteCity.executeUpdate();
        }

        String hqlDeleteCountry = "DELETE FROM Country WHERE countryId = :countryId";
        Query<?> queryDeleteCountry = session.createQuery(hqlDeleteCountry);
        queryDeleteCountry.setParameter("countryId", countryId);
        queryDeleteCountry.executeUpdate();
    }

    @Override
    public void delete(Country entity) {
        Long countryId = entity.getCountryId();
        Country country = findById(countryId);
        if (country != null) {
            boolean isCountryUsed = false;
            for (City city : country.getCities()) {
                for (Hotel hotel : city.getHotels()) {
                    if (!hotel.getBookings().isEmpty()) {
                        isCountryUsed = true;
                        break;
                    }
                }
                if (isCountryUsed) {
                    break;
                }
            }
            if (!isCountryUsed) {
                getSession().delete(entity);
            } else {
                country.setActual(false);
                for (City city : country.getCities()) {
                    for (Hotel hotel : city.getHotels()) {
                        hotel.setActual(false);
                        for (Booking booking : hotel.getBookings()) {
                            booking.setActual(false);
                        }
                    }
                    city.setActual(false);
                }
            }
        } else {
            throw new RuntimeException("Country not found.");
        }


    }
}
