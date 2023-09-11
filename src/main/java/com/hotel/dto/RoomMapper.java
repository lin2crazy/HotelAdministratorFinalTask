package com.hotel.dto;

import com.hotel.model.Room;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class RoomMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public RoomMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public RoomDto convertToDto(Room room) {
        return modelMapper.map(room, RoomDto.class);
    }

    public Room convertToEntity(RoomDto roomDto) {
        return modelMapper.map(roomDto, Room.class);
    }
}
