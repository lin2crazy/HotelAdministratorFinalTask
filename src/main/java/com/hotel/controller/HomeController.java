package com.hotel.controller;

import com.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final RoomService roomService;

    @Autowired
    public HomeController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        model.addAttribute("rooms", roomService.getAll());
        return "room/room-list";
    }
}
