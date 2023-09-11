package com.hotel.service.impl;

import com.hotel.dto.Reservation;
import com.hotel.exception.EntityNotFoundException;
import com.hotel.exception.NullEntityReferenceException;
import com.hotel.model.Guest;
import com.hotel.model.Room;
import com.hotel.repository.GuestRepository;
import com.hotel.service.GuestService;
import com.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class GuestServiceImpl implements GuestService {
    private final GuestRepository guestRepository;
    private final RoomService roomService;

    @Autowired
    public GuestServiceImpl(GuestRepository guestRepository, RoomService roomService) {
        this.guestRepository = guestRepository;
        this.roomService = roomService;
    }

    @Override
    public List<Guest> getAll() {
        return guestRepository.findAll();
    }

    @Override
    @Transactional
    public Guest create(Guest guest) {
        if(guest != null) {
            guest.setCreatedAt(LocalDateTime.now());
            guest.setUpdatedAt(LocalDateTime.now());
            return guestRepository.save(guest);
        }
        throw new NullEntityReferenceException("Guest can't be null");
    }

    @Override
    public Guest readById(int id) {
        return guestRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Guest with id: " + id + " doesn't exist"));
    }

    @Override
    @Transactional
    public Guest update(Guest guest) {
        if (guest != null) {
            readById(guest.getId()); // check that given guest is exist
            guest.setUpdatedAt(LocalDateTime.now());
            System.out.println(guest.getCreatedAt());
            return guestRepository.save(guest);
        }
        throw new NullEntityReferenceException("Guest can't be null");
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Guest guest = readById(id);
        if(guest.getRoom() != null)
            guest.getRoom().removeGuest(guest);
        guestRepository.delete(guest);
    }

    @Override
    public List<Guest> findByNameOrSurname(String name, String surname) {
        return guestRepository.findByNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(name, surname);
    }

    @Override
    public List<Guest> findByNameAndSurname(String name, String surname) {
        return guestRepository.findByNameContainingIgnoreCaseAndSurnameContainingIgnoreCase(name, surname);
    }

    @Override
    @Transactional
    public void chooseRoom(Reservation reservation) {
        Guest guest = readById(reservation.getGuestId());
        Room room = roomService.readById(reservation.getRoomId());
        guest.setRoom(room);
        room.addGuest(guest);
        room.setAvailable(false);
        guest.setArrivalDate(reservation.getArrivalDate());
        guest.setDepartureDate(reservation.getDepartureDate());
    }

    @Override
    @Transactional
    public void freeUpRoom(int guestId, int roomId) {
        Guest guest = readById(guestId);
        Room room = roomService.readById(roomId);
        room.setAvailable(true);
        guest.setRoom(null);
        room.getGuestList().remove(guest);
    }

    @Override
    public List<Guest> getAllDepartureToday() {
        return guestRepository.findAll().stream().filter(g -> g.getDepartureDate() != null).filter(g -> g.getDepartureDate().getDayOfYear() == LocalDateTime.now().getDayOfYear()).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateReservation(Reservation reservation) {
        readById(reservation.getGuestId()).setDepartureDate(reservation.getDepartureDate());
    }
}
