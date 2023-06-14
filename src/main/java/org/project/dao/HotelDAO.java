package org.project.dao;

import org.project.models.Hotel;

import java.util.List;

public interface HotelDAO extends GenericDAO<Hotel, Long> {
     List<Hotel> getAvailableHotels(String startDate, String endDate);
}