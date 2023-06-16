package org.project.service;

import org.project.models.Hotel;
import org.project.models.HotelSearchForm;
import org.project.models.Room;

import java.util.List;
import java.util.Map;

public interface HotelService {
    List<Hotel> getAllHotels();

    Hotel getHotelById(long id);

    void saveHotel(Hotel hotel);

    void updateHotel(long id, Hotel hotel);

    void deleteHotel(long id);

    List<Hotel> getAvailableHotels(String startDate, String endDate);

    Map<Hotel, List<Room>> searchHotels(HotelSearchForm searchForm, long userId);
}
