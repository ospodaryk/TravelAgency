package org.project.service.implementation;

import org.project.dao.HotelDAO;
import org.project.models.Hotel;
import org.project.service.HotelService;

public class HotelServiceImpl implements HotelService {

    private HotelDAO hotelDAO;

    public HotelServiceImpl(HotelDAO hotelDAO) {
        this.hotelDAO = hotelDAO;
    }

    @Override
    public Hotel getHotelById(int id) {
        return hotelDAO.findById(id);
    }

    @Override
    public void saveHotel(Hotel hotel) {
        hotelDAO.save(hotel);
    }

    @Override
    public void updateHotel(Hotel hotel) {
        hotelDAO.update(hotel);
    }

    @Override
    public void deleteHotel(Hotel hotel) {
        hotelDAO.delete(hotel);
    }
}