package org.project.controllers.staff;

import org.project.configuration.security.Security;
import org.project.models.Booking;
import org.project.models.User;
import org.project.service.BookingService;
import org.project.service.RoomService;
import org.project.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/booking")
@Transactional
public class BookingController {

    private static final Logger logger = LoggerFactory.getLogger(BookingController.class);

    private final BookingService bookingService;
    private final UserService userService;
    private final RoomService roomService;

    @Autowired
    public BookingController(BookingService bookingService, UserService userService, RoomService roomService) {
        this.bookingService = bookingService;
        this.userService = userService;
        this.roomService = roomService;
        logger.info("BookingController initialized");
    }

    @PreAuthorize("hasAuthority('STAFF') or hasAuthority('ADMIN')")
    @GetMapping
    public String displayAllBookings(Model model) {
        logger.info("Entering displayAllBookings");
        List<Booking> bookings = bookingService.getAllBookings();
        model.addAttribute("bookings", bookings);
        logger.info("Exiting displayAllBookings");
        return "bookings";
    }

    @GetMapping("/user")
    public String fetchUserBookings(Model model, Principal principal) {
        logger.info("Entering fetchUserBookings");
        Security userDetails = (Security) ((Authentication) principal).getPrincipal();
        long user_id = userDetails.getUserId();
        model.addAttribute("bookings", bookingService.getAllBookingsByUserId(user_id));
        logger.info("Exiting fetchUserBookings");
        return "user-bookings";
    }

    @GetMapping("/create/{room_id}")
    public String showCreateBookingForm(@PathVariable("room_id") Long room_id, Model model, Principal principal) {
        logger.info("Entering showCreateBookingForm");
        Security userDetails = (Security) ((Authentication) principal).getPrincipal();
        long user_id = userDetails.getUserId();
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("user_id", user_id);
        model.addAttribute("booking", new Booking());
        model.addAttribute("room", roomService.getRoomById(room_id));
        logger.info("Exiting showCreateBookingForm");
        return "create-booking";
    }

    @PostMapping("/create/{room_id}")
    public String createNewBooking(Principal principal, @PathVariable("room_id") Long room_id, @Validated @ModelAttribute("booking") Booking booking, BindingResult result, Model model) {
        logger.info("Entering createNewBooking");
        if (result.hasErrors()) {
            List<User> users = userService.getAllUsers();
            model.addAttribute("users", users);
            return "create-booking";
        }
        Security userDetails = (Security) ((Authentication) principal).getPrincipal();
        long user_id = userDetails.getUserId();
        Booking createdBooking = bookingService.prepareBookingWithRoom(user_id, room_id, booking);
        logger.info("Exiting createNewBooking");
        return "redirect:/booking/" + createdBooking.getBookingId();
    }

    @PostMapping("/{booking_id}/create/{room_id}")
    @Transactional
    public String createBookingWithDate(Principal principal, @PathVariable("booking_id") Long booking_id, @PathVariable("room_id") Long room_id, @Validated @ModelAttribute("booking") Booking booking, BindingResult result, Model model) {
        logger.info("Entering createBookingWithDate");
        if (result.hasErrors()) {
            List<User> users = userService.getAllUsers();
            model.addAttribute("users", users);
            return "create-booking-date";
        }
        Security userDetails = (Security) ((Authentication) principal).getPrincipal();
        long user_id = userDetails.getUserId();
        Booking updatedBooking = bookingService.prepareBookingForDate(user_id, room_id, booking_id, booking);
        logger.info("Exiting createBookingWithDate");
        return "redirect:/booking/" + updatedBooking.getBookingId();
    }

    @GetMapping("/{id}")
    public String viewBookingDetails(@PathVariable("id") Long id, Model model) {
        logger.info("Entering viewBookingDetails");
        Booking booking = bookingService.getBookingById(id);
        model.addAttribute("booking", booking);
        logger.info("Exiting viewBookingDetails");
        return "booking-info";
    }

    @PreAuthorize("hasAuthority('STAFF') or hasAuthority('ADMIN')")
    @GetMapping("/update/{id}")
    public String showUpdateBookingForm(@PathVariable("id") Long id, Model model) {
        logger.info("Entering showUpdateBookingForm");
        Booking booking = bookingService.getBookingById(id);
        List<User> users = userService.getAllUsers();
        model.addAttribute("booking", booking);
        model.addAttribute("users", users);
        logger.info("Exiting showUpdateBookingForm");
        return "update-booking";
    }

    @PostMapping("/update/{id}")
    public String updateBookingInfo(@PathVariable("id") Long id, @Validated @ModelAttribute("booking") Booking booking, BindingResult result, Model model) {
        logger.info("Entering updateBookingInfo");
        if (result.hasErrors()) {
            List<User> users = userService.getAllUsers();
            model.addAttribute("users", users);
            return "update-booking";
        }
        bookingService.updateBooking(id, booking);
        logger.info("Exiting updateBookingInfo");
        return "redirect:/booking/" + id;
    }

    @PreAuthorize("hasAuthority('STAFF') or hasAuthority('ADMIN')")
    @GetMapping("/delete/{id}")
    public String deleteBookingRecord(@PathVariable("id") Long id) {
        logger.info("Entering deleteBookingRecord");
        bookingService.deleteBooking(id);
        logger.info("Exiting deleteBookingRecord");
        return "redirect:/booking";
    }
}
