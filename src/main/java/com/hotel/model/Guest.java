package com.hotel.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "guests")
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "The name mustn't be empty")
    @Size(min = 2, max = 30, message = "The name must contain from 2 to 30 characters")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "The surname mustn't be empty")
    @Size(min = 2, max = 30, message = "The surname must contain from 2 to 30 characters")
    @Column(name = "surname")
    private String surname;

    @NotNull(message = "Birth date can't be empty")
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Pattern(regexp = "/d+", message = "The Phone Number must contain only digits")
    @NotBlank(message = "The Phone Number mustn't be empty")
    @Column(name = "phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "identity_card")
    private IdentityCard identityCard;

    @NotBlank(message = "The card id mustn't be empty")
    @Column(name = "card_id")
    private String cardId;

    @Column(name = "arrival_date")
    private LocalDateTime arrivalDate;

    @Column(name = "departure_date")
    private LocalDateTime departureDate;

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Guest(String name, String surname, LocalDate birthDate, String phoneNumber, IdentityCard identityCard, String cardId, LocalDateTime arrivalDate, LocalDateTime departureDate) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.identityCard = identityCard;
        this.cardId = cardId;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
    }
}
