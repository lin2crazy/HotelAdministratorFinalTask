package com.hotel.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Min(value = 1, message = "Number of room must be greater than 0")
    @Column(name = "room_number")
    private int roomNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_class")
    private RoomClass roomClass;

    @Min(value = 1, message = "Number of beds must be greater than 0")
    @Max(value = 6, message = "Number of beds mustn't be greater than 6")
    @Column(name = "beds_num")
    private int bedsNumber;

    @Min(value = 0, message = "The price must be greater than 0")
    @Column(name = "price")
    private double price;

    @Column(name = "is_available")
    private boolean isAvailable;

    @OneToMany(mappedBy = "room")
    private List<Guest> guestList = new ArrayList<>();

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Room(int roomNumber, RoomClass roomClass, int bedsNumber, double price, boolean isAvailable) {
        this.roomNumber = roomNumber;
        this.roomClass = roomClass;
        this.bedsNumber = bedsNumber;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public void addGuest(Guest guest) {
        guestList.add(guest);
    }

    public void removeGuest(Guest guest) {
        guestList.remove(guest);
        if(guestList.isEmpty())
            isAvailable = true;
    }
}
