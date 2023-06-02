package org.project.service;

import org.project.models.Hotel;

import java.util.List;

public interface HotelService {
    List<Hotel> findAll();
    Hotel getHotelById(int id);
    void saveHotel(Hotel hotel);
    void updateHotel(Hotel hotel);
    void deleteHotel(Hotel hotel);
}
