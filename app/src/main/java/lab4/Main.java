package lab4;
import java.time.LocalDate;
import java.io.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {

    try{ Guest person = new Guest.GuestBuilder()
            .firstName("  ")
            .lastName("Doe")
            .middleName("Smith")
            .build();
    }catch(IllegalArgumentException exception ){
        System.out.println(exception.getMessage());
    }
        Guest person1 = new Guest.GuestBuilder()
            .firstName("John2")
            .lastName("Doe")
            .middleName("Smith")
            .build();

        
        RoomType room = new RoomType.RoomTypeBuilder()
            .number("F")
            .bedCount(2)
            .type(RoomClass.Standart)
            .build();
        RoomType room1 = new RoomType.RoomTypeBuilder()
            .number("102")
            .bedCount(2)
            .type(RoomClass.Standart)
            .build();


        
         Reservation reserv1 = new Reservation.ReservationBuilder()
            .roomNumber(room)
            .guest(person1)
            .checkInDate(LocalDate.of(2023,10,01))
            .checkOutDate(LocalDate.of(2023, 10, 03))
            .build();  
    }
}