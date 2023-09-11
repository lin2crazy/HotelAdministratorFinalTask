package com.hotel.dto;

import com.hotel.model.IdentityCard;
import com.hotel.model.Room;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GuestDto {
    @NotBlank(message = "The name mustn't be empty")
    @Size(min = 2, max = 30, message = "The name must contain from 2 to 30 characters")
    private String name;

    @NotBlank(message = "The surname mustn't be empty")
    @Size(min = 2, max = 30, message = "The surname must contain from 2 to 30 characters")
    private String surname;

    @NotNull(message = "Birth date can't be empty")
    private LocalDate birthDate;

    //    @Pattern(regexp = "", message = "") // TODO: ADD A REGEXP
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private IdentityCard identityCard;

    @NotBlank(message = "The card id mustn't be empty")
    private String cardId;

    private LocalDateTime arrivalDate;

    private LocalDateTime departureDate;

    private Room room;
}
