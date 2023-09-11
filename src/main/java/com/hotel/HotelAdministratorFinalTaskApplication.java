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

//    @Bean
//    public CommandLineRunner runner(RoomRepository roomRepository, GuestRepository guestRepository) {
//        return args -> {
//            addEntities(roomRepository, guestRepository);
//
//            roomRepository.findAll().forEach(System.out::println);
//            System.out.println();
//            guestRepository.findAll().forEach(System.out::println);
//        };
//    }
//
//    private void addEntities(RoomRepository roomRepository, GuestRepository guestRepository) {
//        Room r1 = new Room(1, RoomClass.STANDARD, 2, 2000, true);
//        Room r2 = new Room(2, RoomClass.PRESIDENT, 4, 10000, true);
//        Room r3 = new Room(3, RoomClass.SUPERIOR, 2, 4000, true);
//
//        Guest g1 = new Guest("Tom", "Ford", LocalDate.of(1992, 9, 19), "380123123123",
//                IdentityCard.PASSPORT, "123123", LocalDateTime.of(2023, 9, 2, 15, 25), LocalDateTime.of(2023, 9, 4, 12, 0));
//        Guest g2 = new Guest("Marcus", "Aurelius", LocalDate.of(1990, 2, 1), "3809887666482",
//                IdentityCard.PASSPORT, "123123", LocalDateTime.of(2023, 9, 2, 11, 50), LocalDateTime.of(2023, 9, 5, 13, 0));
//        Guest g3 = new Guest("Ryan", "Gosling", LocalDate.of(1985, 5, 28), "380999323224312",
//                IdentityCard.PASSPORT, "123123", LocalDateTime.of(2023, 9, 2, 16, 31), LocalDateTime.of(2023, 9, 6, 18, 0));
//
//        roomRepository.save(r1);
//        roomRepository.save(r2);
//        roomRepository.save(r3);
//
//        guestRepository.save(g1);
//        guestRepository.save(g2);
//        guestRepository.save(g3);
//    }
}
