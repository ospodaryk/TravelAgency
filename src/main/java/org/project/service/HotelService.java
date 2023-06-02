package org.project.service;

import org.project.models.Hotel;

public interface HotelService {
    Hotel getHotelById(int id);
    void saveHotel(Hotel hotel);
    void updateHotel(Hotel hotel);
    void deleteHotel(Hotel hotel);
}
