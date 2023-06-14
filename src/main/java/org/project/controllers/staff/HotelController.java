package org.project.controllers.staff;

import org.project.models.*;
import org.project.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/hotel")
public class HotelController {

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
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(City.class, "city", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                City city = cityService.getCityById(Long.parseLong(text));
                setValue(city);
            }
        });
    }


    @GetMapping("/book/{user_id}")
    public String showHotelSearchForm(@PathVariable(name = "user_id") Integer user_id, Model model) {
        model.addAttribute("searchForm", new HotelSearchForm());
        model.addAttribute("user_id", user_id);
        return "book-hotels";
    }

    @PostMapping("/book/{user_id}")
    public String searchHotels(@PathVariable(name = "user_id") Integer user_id, @ModelAttribute("searchForm") HotelSearchForm searchForm, Model model) {
        model.addAttribute("user_id", user_id);
        String startDateStr = searchForm.getStartDate();
        String endDateStr = searchForm.getEndDate();
        model.addAttribute("user_id", user_id);

        Date startDate = null;
        Date endDate = null;
        List<Hotel> hotels = hotelService.getAvailableHotels(startDateStr, endDateStr);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            startDate = format.parse(startDateStr);
            endDate = format.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // create a map where the key is the Hotel object and the value is a List of unique Room objects
        Map<Hotel, List<Room>> uniqueRoomHotels = new HashMap<>();
        Booking booking = new Booking();
        booking.setUser(userService.getUserById(user_id));
        booking.setStart_date(startDate);
        booking.setEnd_date(endDate);


        for (Hotel hotel : hotels) {
            Set<RoomClassification> classificationsSeen = new HashSet<>();
            List<Room> uniqueRooms = new ArrayList<>();
            for (Room room : hotel.getRooms()) {
                if (classificationsSeen.add(room.getRoomClassification())) {
                    uniqueRooms.add(room);
                }
            }
            uniqueRoomHotels.put(hotel, uniqueRooms);
        }


        bookingService.saveBookingWithoutRoom(booking);
        model.addAttribute("booking", booking);

        model.addAttribute("hotels", uniqueRoomHotels);
        return "book-hotels";
    }

    @GetMapping("/all")
    public String showAllHotels(Model model) {
        List<Hotel> hotels = hotelService.getAllHotels();
        model.addAttribute("hotels", hotels);
        model.addAttribute("countries", countryService.getAllCountries());
        model.addAttribute("cities", cityService.getAllCities());
        return "hotels";
    }

    @GetMapping("/{user_id}")
    public String showAllHotelsUser(@PathVariable(name = "user_id") Integer user_id, Model model) {
        List<Hotel> hotels = hotelService.getAllHotels();
        model.addAttribute("user_id", user_id);
        model.addAttribute("hotels", hotels);
        return "user-hotels";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("hotel", new Hotel());
        model.addAttribute("countries", countryService.getAllCountries());
        model.addAttribute("cities", cityService.getAllCities());

        return "create-hotel";
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute(name = "hotel") Hotel hotel, BindingResult result) {
        if (result.hasErrors()) {
            return "create-hotel";
        }
        hotelService.saveHotel(hotel);
        return "redirect:/hotel/" + hotel.getHotelId();
    }

    @GetMapping("/read/{id}")
    public String read(@PathVariable(name = "id") Integer id, Model model) {
        Hotel hotel = hotelService.getHotelById(id);
        model.addAttribute("hotel", hotel);
        return "hotel-info";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable(name = "id") Integer id, Model model) {

        Hotel hotel = hotelService.getHotelById(id);
        model.addAttribute("hotel", hotel);
        model.addAttribute("countries", countryService.getAllCountries());
        model.addAttribute("cities", cityService.getAllCities());

        return "update-hotel";
    }


    @PostMapping("/update/{id}")
    public String update(@PathVariable(name = "id") Long id, @ModelAttribute(name = "hotel") Hotel hotel, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println("\nERROR\n");
            System.out.println(result.getAllErrors());
            return "update-hotel";
        }
        hotelService.updateHotel(id, hotel);
        return "redirect:/hotel/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") Integer id) {
        hotelService.deleteHotel(id);
        return "redirect:/hotel";
    }


}
