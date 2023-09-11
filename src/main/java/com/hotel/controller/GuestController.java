package com.hotel.controller;

import com.hotel.dto.GuestDto;
import com.hotel.dto.GuestMapper;
import com.hotel.dto.Reservation;
import com.hotel.model.Guest;
import com.hotel.model.IdentityCard;
import com.hotel.service.GuestService;
import com.hotel.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/guests")
public class GuestController {
    final private GuestService guestService;
    final private RoomService roomService;
    final private GuestMapper guestMapper;

    @Autowired
    public GuestController(GuestService guestService, RoomService roomService, GuestMapper guestMapper) {
        this.guestService = guestService;
        this.roomService = roomService;
        this.guestMapper = guestMapper;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("guests", guestService.getAll());
        return "guest/guest-list";
    }

    @GetMapping("/departure-today")
    public String getAllDepartureToday(Model model) {
        model.addAttribute("guests", guestService.getAllDepartureToday());
        return "guest/guest-list";
    }

    @GetMapping("/{id}")
    public String getOneById(@PathVariable("id") int id, Model model, @ModelAttribute("reservation") Reservation reservation) {
        model.addAttribute("guest", guestService.readById(id));
        model.addAttribute("rooms", roomService.getAllAvailable());
        return "guest/guest-info";
    }

    @GetMapping("/create")
    public String create(@ModelAttribute("guest") GuestDto guestDto, Model model) {
        model.addAttribute("identityCards", IdentityCard.values());
        return "guest/create-guest";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("guest") @Valid GuestDto guestDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("identityCards", IdentityCard.values());
            return "guest/create-guest";
        }
        guestService.create(guestMapper.convertToEntity(guestDto));
        return "redirect:/guests";
    }

    @GetMapping("{id}/update")
    public String update(@PathVariable("id") int id, Model model) {
        model.addAttribute("guest", guestService.readById(id));
        model.addAttribute("identityCards", IdentityCard.values());
        return "guest/update-guest";
    }

    @PostMapping("{id}/update")
    public String update(@PathVariable("id") int id, @ModelAttribute("guest") @Valid GuestDto guestDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("id", id);
            model.addAttribute("identityCards", IdentityCard.values());
            return "guest/update-guest";
        }
        Guest guest = guestMapper.convertToEntity(guestDto);
        guest.setId(id);
        guest.setCreatedAt(guestService.readById(id).getCreatedAt());
        guestService.update(guest);
        return "redirect:/guests/" + id;
    }

    @GetMapping("{id}/delete")
    public String deleteById(@PathVariable("id") int id) {
        guestService.deleteById(id);
        return "redirect:/guests";
    }

    @PostMapping("/search")
    public String searchGuests(Model model, @RequestParam(value = "name", required = false) String name) {
        if (name.contains(" ")) {
            model.addAttribute("guests", guestService.findByNameAndSurname(name.split(" ")[0], name.split(" ")[1]));
        } else {
            model.addAttribute("guests", guestService.findByNameOrSurname(name, name));
        }
        model.addAttribute("name", name);
        return "guest/guest-list";
    }

    @PostMapping("/choose-room")
    public String chooseRoom(@ModelAttribute("reservation") @Valid Reservation reservation, BindingResult bindingResult) {
        System.out.println(reservation.getRoomId());
        if (bindingResult.hasErrors()) {
            return "redirect:/guests/" + reservation.getGuestId();
        }
        guestService.chooseRoom(reservation);
        return "redirect:/guests/" + reservation.getGuestId();
    }

    @PostMapping("/get-receipt")
    public String getReceipt(@ModelAttribute("reservation") Reservation reservation, Model model) {
        model.addAttribute("guest", guestService.readById(reservation.getGuestId()));
        model.addAttribute("room", roomService.readById(reservation.getRoomId()));
        model.addAttribute("price", reservation.getTotalPrice());
        return "receipt/receipt-info";
    }

    @GetMapping("{guestId}/free-up/{roomId}")
    public String freeUpRoomWithoutRecalculation(@PathVariable("guestId") int guestId, @PathVariable("roomId") int roomId) {
        guestService.freeUpRoom(guestId, roomId);
        return "redirect:/guests/" + guestId;
    }

    @GetMapping("{id}/update-reservation")
    public String updateReservation(@ModelAttribute("reservation") Reservation reservation, @PathVariable("id") int id) {
        Guest guest = guestService.readById(id);

        reservation.setGuestId(guest.getId());
//        reservation.setRoomId(guest.getRoom().getId());
//        reservation.setArrivalDate(guest.getArrivalDate());
        reservation.setDepartureDate(guest.getDepartureDate());
//        reservation.setPrice(guest.getRoom().getPrice());

        return "receipt/update-receipt";
    }

    @PostMapping("/update-reservation")
    public String saveUpdatedReservation(@ModelAttribute("reservation") Reservation reservation) {
        guestService.updateReservation(reservation);
        return "redirect:/guests/" + reservation.getGuestId();
    }
}


































