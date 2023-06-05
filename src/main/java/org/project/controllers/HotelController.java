package org.project.controllers;

import org.project.models.Hotel;
import org.project.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping("/hotels")
    public String showAllHotels(Model model) {
        List<Hotel> hotels = hotelService.getAllHotels();
        System.out.println("----------------------------------------------------------------");
        System.out.println(hotels);
        System.out.println("----------------------------------------------------------------");

        model.addAttribute("hotels", hotels);

        return "hotels";
    }
}
