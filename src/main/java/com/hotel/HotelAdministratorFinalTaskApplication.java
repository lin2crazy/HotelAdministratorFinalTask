package com.hotel;

import com.hotel.dto.GuestMapper;
import com.hotel.dto.RoomMapper;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HotelAdministratorFinalTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelAdministratorFinalTaskApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public RoomMapper roomTransformer() {
        return new RoomMapper(modelMapper());
    }

    @Bean
    public GuestMapper guestTransformer() {
        return new GuestMapper(modelMapper());
    }
}
