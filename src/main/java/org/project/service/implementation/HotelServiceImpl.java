package org.project.service.implementation;

import org.project.dao.HotelDAO;
import org.project.models.Hotel;
import org.project.models.Role;
import org.project.models.User;
import org.project.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import org.springframework.beans.BeanUtils;
import javax.persistence.EntityNotFoundException;

@Transactional
@Service
public class HotelServiceImpl implements HotelService {

    private HotelDAO hotelDAO;
    @Autowired
    public HotelServiceImpl(HotelDAO hotelDAO) {
        this.hotelDAO = hotelDAO;
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelDAO.getAll();
    }

    @Override
    public Hotel getHotelById(long id) {
        return hotelDAO.findById(id);
    }

    @Override
    public void saveHotel(Hotel hotel) {
        hotelDAO.save(hotel);
    }

    @Override
    public void updateHotel(long id, Hotel hotel) {
        Hotel existingHotel = hotelDAO.findById(id);

        if (existingHotel != null) {
            existingHotel.setHotelId(id);
            existingHotel.setName(hotel.getName());
            existingHotel.setCity(hotel.getCity());
            existingHotel.setDescription(hotel.getDescription());
            existingHotel.setLocation(hotel.getLocation());
            hotelDAO.update(existingHotel);
        }
    }


    @Override
    public void deleteHotel(long id) {
        hotelDAO.delete(hotelDAO.findById(id));
    }
}