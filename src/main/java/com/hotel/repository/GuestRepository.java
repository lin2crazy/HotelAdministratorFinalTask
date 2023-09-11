package com.hotel.repository;

import com.hotel.model.Guest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Integer> {
    List<Guest> findByNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(String name, String surname);
    List<Guest> findByNameContainingIgnoreCaseAndSurnameContainingIgnoreCase(String name, String surname);
}
