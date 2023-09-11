package com.hotel.dto;

import com.hotel.model.Guest;
import com.hotel.model.RoomClass;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomDto {
    @Min(value = 1, message = "Number of room must be greater than 0")
    private int roomNumber;

    @Enumerated(EnumType.STRING)
    private RoomClass roomClass;

    @Min(value = 1, message = "Number of beds must be greater than 0")
    @Max(value = 6, message = "Number of beds mustn't be greater than 6")
    private int bedsNumber;

    @Min(value = 0, message = "The price must be greater than 0")
    private double price;

    private boolean isAvailable;

    private List<Guest> guestList;

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean available) {
        isAvailable = available;
    }
}
