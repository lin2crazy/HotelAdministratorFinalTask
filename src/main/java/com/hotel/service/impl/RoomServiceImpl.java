package com.hotel.service.impl;

import com.hotel.exception.EntityNotFoundException;
import com.hotel.exception.NullEntityReferenceException;
import com.hotel.model.Guest;
import com.hotel.model.Room;
import com.hotel.repository.RoomRepository;
import com.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public List<Room> getAll() {
        return roomRepository.findAll();
    }

    @Override
    public List<Room> getAllAvailable() {
        return roomRepository.findAllByIsAvailable();
    }

    @Override
    @Transactional
    public Room create(Room room) {
        if (room != null) {
            room.setCreatedAt(LocalDateTime.now());
            room.setUpdatedAt(LocalDateTime.now());
            return roomRepository.save(room);
        }
        throw new NullEntityReferenceException("Room can't be null");
    }

    @Override
    public Room readById(int id) {
        return roomRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Room with id: " + id + " doesn't exist"));
    }

    @Override
    @Transactional
    public Room update(Room room) {
        if (room != null) {
            readById(room.getId()); // check that given room is exist
            room.setUpdatedAt(LocalDateTime.now());
            return roomRepository.save(room);
        }
        throw new NullEntityReferenceException("Room can't be null");
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        roomRepository.delete(readById(id));
    }
}
