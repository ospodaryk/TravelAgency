package org.project.controllers.staff;

import org.project.configuration.security.Security;
import org.project.models.Booking;
import org.project.models.Room;
import org.project.models.User;
import org.project.service.BookingService;
import org.project.service.RoomService;
import org.project.service.UserService;
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
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/booking")
@Transactional
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

    @PreAuthorize("hasAuthority('STAFF')")
    @GetMapping
    public String showAllBookings(Model model) {
        List<Booking> bookings = bookingService.getAllBookings();
        model.addAttribute("bookings", bookings);
        return "bookings";
    }

    @GetMapping("/create/{room_id}")
    public String createBookingForm( @PathVariable("room_id") Long room_id, Model model, Principal principal) {
        Security userDetails = (Security) ((Authentication) principal).getPrincipal();
        long user_id=userDetails.getUserId();
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("user_id", user_id);
        model.addAttribute("booking", new Booking());
        model.addAttribute("room", roomService.getRoomById(room_id));
        return "create-booking";
    }

    @PostMapping("/create/{room_id}")
    public String createBooking(Principal principal, @PathVariable("room_id") Long room_id, @Validated @ModelAttribute("booking") Booking booking, BindingResult result, Model model) {
        Security userDetails = (Security) ((Authentication) principal).getPrincipal();
        long user_id=userDetails.getUserId();
        if (result.hasErrors()) {
            List<User> users = userService.getAllUsers();
            model.addAttribute("users", users);
            return "create-booking";
        }
        model.addAttribute("user_id", user_id);
        booking.setUser(userService.getUserById(user_id));
        booking.getRooms().add(roomService.getRoomById(room_id));
        booking.setHotel(roomService.getRoomById(room_id).getHotel());
        model.addAttribute("room", roomService.getRoomById(room_id));
        double sum = 0;
        for (Iterator<Room> it = booking.getRooms().iterator(); it.hasNext(); ) {
            sum += it.next().getPrice();
        }
        booking.setTotalPrice(sum);
        Room room = roomService.getRoomById(room_id);
        roomService.updateRoom(room_id, room);
        bookingService.saveBooking(room_id, booking);
        return "redirect:/booking/" + booking.getBookingId();
    }

    @GetMapping("/{booking_id}/create/{room_id}")
    public String createBookingFormFORDATE(Principal principal, @PathVariable("booking_id") Long booking_id, @PathVariable("room_id") Long room_id, Model model) {
        Security userDetails = (Security) ((Authentication) principal).getPrincipal();
        long user_id=userDetails.getUserId();
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        Booking booking = bookingService.getBookingById(booking_id);
        booking.setUser(userService.getUserById(user_id));
        model.addAttribute("booking", booking);
        String startDate = booking.getStart_date().toString();
        String endDate = booking.getEnd_date().toString();
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("room", roomService.getRoomById(room_id));
        userService.getUserById(user_id).getBookings().add(booking);
        bookingService.updateBooking(booking_id, booking);

        return "create-booking-date";
    }

    @PostMapping("/{booking_id}/create/{room_id}")
    @Transactional
    public String createBookingFORDATE(Principal principal, @PathVariable("booking_id") Long booking_id, @PathVariable("room_id") Long room_id, @Validated @ModelAttribute("booking") Booking booking, BindingResult result, Model model) {
        Security userDetails = (Security) ((Authentication) principal).getPrincipal();
        long user_id=userDetails.getUserId();
        if (result.hasErrors()) {
            List<User> users = userService.getAllUsers();
            model.addAttribute("users", users);
            return "create-booking-date";
        }
        Booking existingBooking = bookingService.getBookingById(booking_id);
        existingBooking.setUser(userService.getUserById(user_id));
        existingBooking.getRooms().add(roomService.getRoomById(room_id));
        existingBooking.setHotel(roomService.getRoomById(room_id).getHotel());
        model.addAttribute("room", roomService.getRoomById(room_id));
        double sum = 0;
        for (Iterator<Room> it = existingBooking.getRooms().iterator(); it.hasNext(); ) {
            sum += it.next().getPrice();
        }
        existingBooking.setTotalPrice(sum);
        Room room = roomService.getRoomById(room_id);
//        room.setAvailable(false);
        roomService.updateRoom(room_id, room);
        existingBooking.setNumOfPeople(room.getCapacity());
        existingBooking.getRooms().add(roomService.getRoomById(room_id));
        bookingService.updateBooking(booking_id, existingBooking);
        return "redirect:/booking/" + existingBooking.getBookingId();
    }


    @GetMapping("/{id}")
    public String viewBooking(@PathVariable("id") Long id, Model model) {
        Booking booking = bookingService.getBookingById(id);
        model.addAttribute("booking", booking);
        return "booking-info";
    }

    @PreAuthorize("hasAuthority('STAFF')")
    @GetMapping("/update/{id}")
    public String updateBookingForm(@PathVariable("id") Long id, Model model) {
        Booking booking = bookingService.getBookingById(id);
        List<User> users = userService.getAllUsers();
        model.addAttribute("booking", booking);
        model.addAttribute("users", users);
        return "update-booking";
    }

    @PostMapping("/update/{id}")
    public String updateBooking(@PathVariable("id") Long id, @Validated @ModelAttribute("booking") Booking booking, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<User> users = userService.getAllUsers();
            model.addAttribute("users", users);
            return "update-booking";
        }
        bookingService.updateBooking(id, booking);
        return "redirect:/booking/" + id;
    }

    @PreAuthorize("hasAuthority('STAFF')")
    @GetMapping("/delete/{id}")
    public String deleteBooking(@PathVariable("id") Long id) {
        bookingService.deleteBooking(id);
        return "redirect:/booking";
    }
}
