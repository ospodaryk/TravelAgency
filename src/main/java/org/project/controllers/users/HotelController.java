package org.project.controllers.users;

import org.project.models.*;
import org.project.service.BookingService;
import org.project.service.CityService;
import org.project.service.CountryService;
import org.project.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
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

    @Autowired
    public HotelController(HotelService hotelService, CountryService countryService, CityService cityService, BookingService bookingService) {
        this.hotelService = hotelService;
        this.countryService = countryService;
        this.cityService = cityService;
        this.bookingService = bookingService;
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


    @GetMapping("/book")
    public String showHotelSearchForm(Model model) {
        model.addAttribute("searchForm", new HotelSearchForm());
        return "book-hotels";
    }

    @PostMapping("/book")
    public String searchHotels(@ModelAttribute("searchForm") HotelSearchForm searchForm, Model model) {
        String startDateStr = searchForm.getStartDate();
        String endDateStr = searchForm.getEndDate();

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

    @GetMapping
    public String showAllHotelsUser(Model model) {
        List<Hotel> hotels = hotelService.getAllHotels();
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

    @GetMapping("/{id}")
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
