package org.project.controllers.staff;

import org.project.configuration.security.Security;
import org.project.models.*;
import org.project.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/hotel")
public class HotelController {

    private final Logger logger = LoggerFactory.getLogger(HotelController.class);
    private HotelService hotelService;
    private CountryService countryService;
    private CityService cityService;
    private BookingService bookingService;
    private UserService userService;

    @Autowired
    public HotelController(HotelService hotelService, CountryService countryService, CityService cityService, BookingService bookingService, UserService userService) {
        this.hotelService = hotelService;
        this.countryService = countryService;
        this.cityService = cityService;
        this.bookingService = bookingService;
        this.userService = userService;
        logger.info("HotelController initialized");
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        logger.info("Entering initBinder");
        binder.registerCustomEditor(City.class, "city", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                City city = cityService.getCityById(Long.parseLong(text));
                setValue(city);
            }
        });
        logger.info("Exiting initBinder");
    }

    @GetMapping("/book")
    public String showHotelSearchForm(Model model, Principal principal) {
        logger.info("Entering showHotelSearchForm");
        Security userDetails = (Security) ((Authentication) principal).getPrincipal();
        long user_id = userDetails.getUserId();
        model.addAttribute("searchForm", new HotelSearchForm());
        model.addAttribute("user_id", user_id);
        logger.info("Exiting showHotelSearchForm");
        return "book-hotels";
    }

    @PostMapping("/book")
    public String searchHotels(Principal principal, @ModelAttribute("searchForm") HotelSearchForm searchForm, Model model) {
        logger.info("Entering searchHotels");
        long userId = getUserIdFromPrincipal(principal);
        model.addAttribute("user_id", userId);
        model.addAttribute("hotels", hotelService.searchHotels(searchForm, userId));
        String startDateStr = searchForm.getStartDate();
        String endDateStr = searchForm.getEndDate();

        Date startDate = null;
        Date endDate = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            startDate = format.parse(startDateStr);
            endDate = format.parse(endDateStr);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format.", e);
        }

        Booking booking = new Booking();
        booking.setUser(userService.getUserById(userId));
        booking.setStart_date(startDate);
        booking.setEnd_date(endDate);
        bookingService.saveBookingWithoutRoom(booking);
        model.addAttribute("booking", booking);

        logger.info("Exiting searchHotels");
        return "book-hotels";
    }

    @GetMapping("/all")
    public String showAllHotels(Model model) {
        logger.info("Entering showAllHotels");
        List<Hotel> hotels = hotelService.getAllHotels();
        model.addAttribute("hotels", hotels);
        model.addAttribute("countries", countryService.getAllCountries());
        model.addAttribute("cities", cityService.getAllCities());
        logger.info("Exiting showAllHotels");
        return "hotels";
    }

    @GetMapping
    public String showAllHotelsUser(Model model, Principal principal) {
        logger.info("Entering showAllHotelsUser");
        List<Hotel> hotels = hotelService.getAllHotels();
        Security userDetails = (Security) ((Authentication) principal).getPrincipal();
        model.addAttribute("userId", userDetails.getUserId());
        model.addAttribute("hotels", hotels);
        logger.info("Exiting showAllHotelsUser");
        return "user-hotels";
    }

    @GetMapping("/create")
    public String create(Model model) {
        logger.info("Entering create");
        model.addAttribute("hotel", new Hotel());
        model.addAttribute("countries", countryService.getAllCountries());
        model.addAttribute("cities", cityService.getAllCities());
        logger.info("Exiting create");
        return "create-hotel";
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute(name = "hotel") Hotel hotel, BindingResult result) {
        logger.info("Entering create with POST");
        if (result.hasErrors()) {
            logger.warn("Form validation errors occurred");
            return "create-hotel";
        }
        hotelService.saveHotel(hotel);
        logger.info("Exiting create with POST");
        return "redirect:/hotel/all";
    }

    @GetMapping("/read/{id}")
    public String read(@PathVariable(name = "id") Integer id, Model model) {
        logger.info("Entering read");
        Hotel hotel = hotelService.getHotelById(id);
        model.addAttribute("hotel", hotel);
        logger.info("Exiting read");
        return "hotel-info";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable(name = "id") Integer id, Model model) {
        logger.info("Entering update");
        Hotel hotel = hotelService.getHotelById(id);
        model.addAttribute("hotel", hotel);
        model.addAttribute("countries", countryService.getAllCountries());
        model.addAttribute("cities", cityService.getAllCities());
        logger.info("Exiting update");
        return "update-hotel";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable(name = "id") Long id, @ModelAttribute(name = "hotel") Hotel hotel, BindingResult result) {
        logger.info("Entering update with POST");
        if (result.hasErrors()) {
            logger.warn("Form validation errors occurred");
            return "update-hotel";
        }
        hotelService.updateHotel(id, hotel);
        logger.info("Exiting update with POST");
        return "redirect:/hotel/all";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") Integer id) {
        logger.info("Entering delete");
        hotelService.deleteHotel(id);
        logger.info("Exiting delete");
        return "redirect:/hotel/all";
    }

    private long getUserIdFromPrincipal(Principal principal) {
        logger.info("Getting user id from Principal");
        return ((Security) ((Authentication) principal).getPrincipal()).getUserId();
    }
}
