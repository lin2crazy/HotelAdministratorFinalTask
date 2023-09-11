package com.hotel.service;

import com.hotel.dto.Reservation;
import com.hotel.model.Guest;
import com.hotel.model.Room;

import java.util.List;

public interface GuestService {
    Guest create(Guest guest);
    Guest readById(int id);
    Guest update(Guest guest);
    void deleteById(int id);
    List<Guest> getAll();
    List<Guest> findByNameOrSurname(String name, String surname);
    List<Guest> findByNameAndSurname(String name, String surname);
    void chooseRoom(Reservation reservation);
    void freeUpRoom(int guestId, int roomId);
    List<Guest> getAllDepartureToday();
    void updateReservation(Reservation reservation);
}
