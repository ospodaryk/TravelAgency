package org.project.controllers;

import org.project.models.Booking;
import org.project.models.Room;
import org.project.models.User;
import org.project.service.BookingService;
import org.project.service.RoomService;
import org.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/booking")
public class BookingController {

    private BookingService bookingService;
    private UserService userService;
    private RoomService roomService;

    @Autowired
    public BookingController(BookingService bookingService, UserService userService, RoomService roomService) {
        this.bookingService = bookingService;
        this.userService = userService;
        this.roomService = roomService;
    }


    @GetMapping
    public String showAllBookings(Model model) {
        List<Booking> bookings = bookingService.getAllBookings();
        model.addAttribute("bookings", bookings);
        return "bookings";
    }

    @GetMapping("/create/{room_id}")
    public String createBookingForm(@PathVariable("room_id") Long room_id, Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("booking", new Booking());
        model.addAttribute("room", roomService.getRoomById(room_id));
        return "create-booking";
    }

    //TODO: ADD USER
    @PostMapping("/create/{room_id}")
    public String createBooking(@PathVariable("room_id") Long room_id, @Validated @ModelAttribute("booking") Booking booking, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<User> users = userService.getAllUsers();
            model.addAttribute("users", users);
            return "create-booking";
        }
        booking.setUser(userService.getUserById(1));
        booking.getRooms().add(roomService.getRoomById(room_id));
        booking.setHotel(roomService.getRoomById(room_id).getHotel());
        model.addAttribute("room", roomService.getRoomById(room_id));
        double sum = 0;
        for (Iterator<Room> it = booking.getRooms().iterator(); it.hasNext(); ) {
            sum += it.next().getPrice();
        }
        booking.setTotalPrice(sum);
        Room room = roomService.getRoomById(room_id);
        room.setAvailable(false);
        roomService.updateRoom(room_id, room);
//        System.out.println("\n\n\nROOOM:" + room_id + " | " + room.getBooking() + " | " + room.isAvailable() + "\n\n\n");
        bookingService.saveBooking(room_id, booking);
        return "redirect:/booking/" + booking.getBookingId();
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
        return "redirect:/booking/" + id;
    }

    @GetMapping("/delete/{id}")
    public String deleteBooking(@PathVariable("id") Long id) {
        bookingService.deleteBooking(id);
        return "redirect:/booking";
    }
}
