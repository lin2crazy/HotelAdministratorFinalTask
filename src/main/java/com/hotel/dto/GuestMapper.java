package com.hotel.dto;

import com.hotel.model.Guest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class GuestMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public GuestMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public GuestDto convertToDto(Guest guest) {
        return modelMapper.map(guest, GuestDto.class);
    }

    public Guest convertToEntity(GuestDto guestDto) {
        return modelMapper.map(guestDto, Guest.class);
    }
}
