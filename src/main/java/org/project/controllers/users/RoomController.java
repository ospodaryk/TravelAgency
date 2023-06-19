package org.project.controllers.users;

import org.project.configuration.security.Security;
import org.project.models.Room;
import org.project.service.HotelService;
import org.project.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/room")
public class RoomController {
    private final Logger logger = LoggerFactory.getLogger(RoomController.class);
    private RoomService roomService;
    private HotelService hotelService;

    @Autowired
    public RoomController(RoomService roomService, HotelService hotelService) {
        this.roomService = roomService;
        this.hotelService = hotelService;
        logger.info("RoomController initialized");
    }

    @GetMapping
    public String showAllRooms(Model model) {
        logger.info("Entering showAllRooms");
        List<Room> rooms = roomService.getAllRooms();
        model.addAttribute("rooms", rooms);
        logger.info("Exiting showAllRooms");
        return "rooms";
    }

    @GetMapping("/user-room")
    public String showAllRoomsUser(Model model) {
        logger.info("Entering showAllRoomsUser");
        List<Room> rooms = roomService.getAllRooms().stream().filter(Room::isActual).toList();
        model.addAttribute("rooms", rooms);
        logger.info("Exiting showAllRoomsUser");
        return "user-rooms";
    }

    @GetMapping("/hotel/{id}")
    public String showAllRoomsByHotel(Principal principal, @PathVariable(name = "id") Long id, Model model) {
        logger.info("Entering showAllRoomsByHotel");
        Security userDetails = (Security) ((Authentication) principal).getPrincipal();
        long user_id = userDetails.getUserId();
        List<Room> rooms = roomService.getRoomByHotelID(id).stream().filter(Room::isActual).toList();
        model.addAttribute("rooms", rooms);
        model.addAttribute("user_id", user_id);
        model.addAttribute("hotel", hotelService.getHotelById(id).getName());
        logger.info("Exiting showAllRoomsByHotel");
        return "user-rooms";
    }

    @GetMapping("/adm/hotel/{id}")
    public String showAllRoomsByHotelAdmin(@PathVariable(name = "id") Long id, Model model) {
        logger.info("Entering showAllRoomsByHotelAdmin");
        List<Room> rooms = roomService.getRoomByHotelID(id);
        model.addAttribute("rooms", rooms);
        model.addAttribute("hotel", hotelService.getHotelById(id).getName());
        logger.info("Exiting showAllRoomsByHotelAdmin");
        return "rooms";
    }

    @GetMapping("/create/{id}")
    public String create(@PathVariable(name = "id") Long id, Model model) {
        logger.info("Entering create");
        model.addAttribute("room", new Room());
        model.addAttribute("hotels", hotelService.getAllHotels());
        model.addAttribute("hotel", hotelService.getHotelById(id));
        logger.info("Exiting create");
        return "create-room";
    }

    @PostMapping("/create/{id}")
    public String create(@PathVariable(name = "id") Long id, @Validated @ModelAttribute(name = "room") Room room, BindingResult result) {
        logger.info("Entering create with POST");
        if (result.hasErrors()) {
            logger.warn("Form validation errors occurred");
            return "create-room";
        }
        room.setHotel(hotelService.getHotelById(id));
        roomService.saveRoom(room);
        logger.info("Exiting create with POST");
        return "redirect:/room/" + room.getRoomId();
    }

    @GetMapping("/{id}")
    public String read(@PathVariable(name = "id") Long id, Model model) {
        logger.info("Entering read");
        Room room = roomService.getRoomById(id);
        model.addAttribute("room", room);
        logger.info("Exiting read");
        return "room-info";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable(name = "id") Long id, Model model) {
        logger.info("Entering update");
        Room room = roomService.getRoomById(id);
        model.addAttribute("room", room);
        model.addAttribute("hotels", hotelService.getAllHotels());
        logger.info("Exiting update");
        return "update-room";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable(name = "id") Long id, @ModelAttribute(name = "room") Room room, BindingResult result) {
        logger.info("Entering update with POST");
        if (result.hasErrors()) {
            logger.warn("Form validation errors occurred");
            return "update-room";
        }
        roomService.updateRoom(id, room);
        logger.info("Exiting update with POST");
        return "redirect:/room/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id) {
        logger.info("Entering delete");
        roomService.deleteRoom(id);
        logger.info("Exiting delete");
        return "redirect:/room";
    }
}
