package org.project.service;

import org.project.models.Hotel;

import java.util.List;

public interface HotelService {
    List<Hotel> getAllHotels();

    Hotel getHotelById(long id);

    void saveHotel(Hotel hotel);

    void updateHotel(long id,Hotel hotel);

    void deleteHotel(long id);
}
