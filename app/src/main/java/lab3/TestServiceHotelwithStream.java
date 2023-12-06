package lab3;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lab2java.Guest;
import lab2java.Reservation;
import lab2java.RoomClass;
import lab2java.RoomType;

public class TestServiceHotelwithStream {

    public static void main(String[] args) {
       
        Guest person = new Guest.GuestBuilder()
                .firstName("John")
                .lastName("Doe")
                .middleName("Smith")
                .build();

       
        RoomType room = new RoomType.RoomTypeBuilder()
                .number("101")
                .bedCount(4)
                .type(RoomClass.Standart)
                .build();
        RoomType room2 = new RoomType.RoomTypeBuilder()
                .number("102")
                .bedCount(1)
                .type(RoomClass.Standart)
                .build();
        RoomType room3 = new RoomType.RoomTypeBuilder()
                .number("103")
                .bedCount(1)
                .type(RoomClass.Standart)
                .build();
        
       
        Reservation reserv = new Reservation.ReservationBuilder()
                .roomNumber(room)
                .guest(person)
                .checkInDate(LocalDate.of(2023, 10, 01))
                .checkOutDate(LocalDate.of(2023, 10, 03))
                .build();
        Reservation reserv2 = new Reservation.ReservationBuilder()
                .roomNumber(room2)
                .guest(person)
                .checkInDate(LocalDate.of(2023, 11, 19))
                .checkOutDate(LocalDate.of(2023, 11, 23))
                .build();
        Reservation reserv3 = new Reservation.ReservationBuilder()
                .roomNumber(room3)
                .guest(person)
                .checkInDate(LocalDate.of(2023, 10, 19))
                .checkOutDate(LocalDate.of(2023, 10, 23))
                .build();
        
      
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reserv);
        reservations.add(reserv2);
        reservations.add(reserv3);
        HotelServisWithStream hotelService = new HotelServisWithStream(reservations);

       
        LocalDate currentDate = LocalDate.now();
        List<Reservation> sortedFreeRooms = hotelService.sortFreeRoomByBedCount(currentDate);
        System.out.println("Free room for reservation " + currentDate + ":");
        for (Reservation reservation : sortedFreeRooms) {
            String roomInfo = "RoomNumber: " + reservation.getRoom().getNumber() +
                              ", BedCount: " + reservation.getRoom().getBedCount();
            System.out.println(roomInfo);
        }
      
        List<Reservation> activeReservations = hotelService.allRoomBook(currentDate);
        System.out.println("\nActive Reservations for Date " + currentDate + ":");
        for (Reservation reservation : activeReservations) {
            System.out.println(reservation);
        }
        
        RoomClass roomTypeToSearch = RoomClass.Standart;  
        List<Reservation> freeRoomsByType = hotelService.freeRoomByType(roomTypeToSearch, currentDate);
        System.out.println("\nFree rooms of type " + roomTypeToSearch + " for date " + currentDate + ":");
        for (Reservation reservation : freeRoomsByType) {
            String roomInfo = "RoomNumber: " + reservation.getRoom().getNumber() +
                              ", BedCount: " + reservation.getRoom().getBedCount() +
                              ", RoomType: " + reservation.getRoom().getType();
            System.out.println(roomInfo);
        }
        
        int targetBedCount = 4; 
        List<Reservation> freeRoomsByBedCount = hotelService.freeRomForGiveDate(targetBedCount, currentDate);
        System.out.println("\nFree rooms with " + targetBedCount + " beds on " + currentDate + ":");
        for (Reservation reservation : freeRoomsByBedCount) {
            String roomInfo = "RoomNumber: " + reservation.getRoom().getNumber() +
                              ", BedCount: " + reservation.getRoom().getBedCount();
            System.out.println(roomInfo);
        }
    }
}
