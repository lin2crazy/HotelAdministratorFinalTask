package com.hotel.service;

import com.hotel.model.Guest;
import com.hotel.model.Room;

import java.util.List;

public interface RoomService {
    Room create(Room room);
    Room readById(int id);
    Room update(Room room);
    void deleteById(int id);
    List<Room> getAll();
    List<Room> getAllAvailable();
}
