package org.project.controllers.users;

import org.project.models.Room;
import org.project.models.Hotel;
import org.project.service.RoomService;
import org.project.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/room")
public class RoomController {
    private RoomService roomService;
    private HotelService hotelService;

    @Autowired
    public RoomController(RoomService roomService, HotelService hotelService) {
        this.roomService = roomService;
        this.hotelService = hotelService;
    }

    @GetMapping
    public String showAllRooms(Model model) {
        List<Room> rooms = roomService.getAllRooms();
        model.addAttribute("rooms", rooms);
        return "rooms";
    }
    @GetMapping("/user-room")
    public String showAllRoomsUser(Model model) {
        List<Room> rooms = roomService.getAllRooms();
        model.addAttribute("rooms", rooms);
        return "user-rooms";
    }
    @GetMapping  ("/hotel/{id}")
    public String showAllRoomsByHotel(@PathVariable(name = "id") Long id, Model model) {
        List<Room> rooms = roomService.getRoomByHotelID(id);
        model.addAttribute("rooms", rooms);

        model.addAttribute("hotel", hotelService.getHotelById(id).getName());
        //Todo : split by admin and user methods

        return "user-rooms";
    }
    @GetMapping  ("/adm/hotel/{id}")
    public String showAllRoomsByHotelAdmin(@PathVariable(name = "id") Long id, Model model) {
        List<Room> rooms = roomService.getRoomByHotelID(id);
        model.addAttribute("rooms", rooms);
        model.addAttribute("hotel", hotelService.getHotelById(id).getName());
        //Todo : split by admin and user methods
        return "rooms";
    }
    @GetMapping("/create/{id}")
    public String create(@PathVariable(name = "id") Long id,Model model) {
        model.addAttribute("room", new Room());
        model.addAttribute("hotels", hotelService.getAllHotels());
        model.addAttribute("hotel", hotelService.getHotelById(id));
        return "create-room";
    }

    @PostMapping("/create/{id}")
    public String create(@PathVariable(name = "id") Long id,@Validated @ModelAttribute(name = "room") Room room, BindingResult result) {
        if (result.hasErrors()) {
            return "create-room";
        }
        room.setHotel(hotelService.getHotelById(id));
        roomService.saveRoom(room);
        return "redirect:/room/" + room.getRoomId();
    }

    @GetMapping("/{id}")
    public String read(@PathVariable(name = "id") Long id, Model model) {
        Room room = roomService.getRoomById(id);
        model.addAttribute("room", room);
        return "room-info";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable(name = "id") Long id, Model model) {
        Room room = roomService.getRoomById(id);
        model.addAttribute("room", room);
        model.addAttribute("hotels", hotelService.getAllHotels());
        return "update-room";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable(name = "id") Long id, @ModelAttribute(name = "room") Room room, BindingResult result) {
        if (result.hasErrors()) {
            return "update-room";
        }
        roomService.updateRoom(id, room);
        return "redirect:/room/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id) {
        roomService.deleteRoom(id);
        return "redirect:/room";
    }
}
