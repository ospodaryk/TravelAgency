package org.project.controllers;

import org.project.models.Booking;
import org.project.models.User;
import org.project.service.BookingService;
import org.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/booking")
public class BookingController {

    private BookingService bookingService;
    private UserService userService;

    @Autowired
    public BookingController(BookingService bookingService, UserService userService) {
        this.bookingService = bookingService;
        this.userService = userService;
    }

    @GetMapping
    public String showAllBookings(Model model) {
        List<Booking> bookings = bookingService.getAllBookings();
        model.addAttribute("bookings", bookings);
        return "bookings";
    }

    @GetMapping("/create")
    public String createBookingForm(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("booking", new Booking());
        return "create-booking";
    }

    @PostMapping("/create")
    public String createBooking(@Validated @ModelAttribute("booking") Booking booking, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<User> users = userService.getAllUsers();
            model.addAttribute("users", users);
            return "create-booking";
        }
        bookingService.saveBooking(booking);
        return "redirect:/bookings/" + booking.getBookingId();
    }

    @GetMapping("/{id}")
    public String viewBooking(@PathVariable("id") Long id, Model model) {
        Booking booking = bookingService.getBookingById(id);
        model.addAttribute("booking", booking);
        return "booking-info";
    }

    @GetMapping("/update/{id}")
    public String updateBookingForm(@PathVariable("id") Long id, Model model) {
        Booking booking = bookingService.getBookingById(id);
        List<User> users = userService.getAllUsers();
        model.addAttribute("booking", booking);
        model.addAttribute("users", users);
        return "update-booking";
    }

    @PostMapping("/update/{id}")
    public String updateBooking(@PathVariable("id") Long id, @Validated @ModelAttribute("booking") Booking booking,
                                BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<User> users = userService.getAllUsers();
            model.addAttribute("users", users);
            return "update-booking";
        }
        bookingService.updateBooking(id, booking);
        return "redirect:/bookings/" + id;
    }

    @GetMapping("/delete/{id}")
    public String deleteBooking(@PathVariable("id") Long id) {
        bookingService.deleteBooking(id);
        return "redirect:/bookings";
    }
}
