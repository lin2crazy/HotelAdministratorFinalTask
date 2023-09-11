package com.hotel.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
public class Reservation {
    @NotNull(message = "Arrival date can't be empty")
    private LocalDateTime arrivalDate;
    @NotNull(message = "Departure date date can't be empty")
    private LocalDateTime departureDate;
    private int guestId;
    private int roomId;
    private double price;
    public double getTotalPrice() {
        return ChronoUnit.HOURS.between(arrivalDate, departureDate) / 24.0 * price;
    }
}
