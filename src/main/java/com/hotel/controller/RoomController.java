package com.hotel.controller;

import com.hotel.dto.RoomDto;
import com.hotel.dto.RoomMapper;
import com.hotel.model.Room;
import com.hotel.model.RoomClass;
import com.hotel.service.GuestService;
import com.hotel.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;
    private final GuestService guestService;
    private final RoomMapper roomMapper;

    @Autowired
    public RoomController(RoomService roomService, GuestService guestService, RoomMapper roomMapper) {
        this.roomService = roomService;
        this.guestService = guestService;
        this.roomMapper = roomMapper;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("rooms", roomService.getAll());
        return "room/room-list";
    }

    @GetMapping("/{id}")
    public String getOneById(@PathVariable("id") int id, Model model) {
        Room room = roomService.readById(id);
        model.addAttribute("room", room);
        model.addAttribute("guests", room.getGuestList());
        return "room/room-info";
    }

    @GetMapping("/create")
    public String create(@ModelAttribute("room") RoomDto roomDto, Model model) {
        model.addAttribute("roomClasses", RoomClass.values());
        model.addAttribute("bedsNumbers", new int[]{1, 2, 3, 4, 5, 6});
        return "room/create-room";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("room") @Valid RoomDto roomDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("roomClasses", RoomClass.values());
            model.addAttribute("bedsNumbers", new int[]{1, 2, 3, 4, 5, 6});
            return "room/create-room";
        }
        roomService.create(roomMapper.convertToEntity(roomDto));
        return "redirect:/rooms";
    }

    @GetMapping("{id}/update")
    public String update(@PathVariable("id") int id, Model model) {
        model.addAttribute("room", roomService.readById(id));
        model.addAttribute("roomClasses", RoomClass.values());
        model.addAttribute("bedsNumbers", new int[]{1, 2, 3, 4, 5, 6});
        return "room/update-room";
    }

    @PostMapping("{id}/update")
    public String update(@PathVariable("id") int id, @ModelAttribute("room") @Valid RoomDto roomDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("roomClasses", RoomClass.values());
            model.addAttribute("bedsNumbers", new int[]{1, 2, 3, 4, 5, 6});
            return "room/update-room";
        }
        Room room = roomMapper.convertToEntity(roomDto);
        room.setCreatedAt(roomService.readById(id).getCreatedAt());
        room.setId(id);
        roomService.update(room);
        return "redirect:/rooms";
    }

    @GetMapping("{id}/delete")
    public String deleteById(@PathVariable("id") int id) {
        roomService.deleteById(id);
        return "redirect:/rooms";
    }
}


























