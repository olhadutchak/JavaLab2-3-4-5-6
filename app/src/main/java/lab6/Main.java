package lab6;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import lab2java.Reservation;

public class Main {

    public static void main(String[] args) {
       
        try {
            DatabaseService databaseService = new DatabaseService();

           
            List<Reservation> freeRoomsForGivenDate = databaseService.freeRoomForGiveDateAndBedCount(2, LocalDate.of(2023, 10, 01));
            System.out.println("Free rooms for the given date and bed count: " + freeRoomsForGivenDate);

           
            List<Reservation> allBookedRooms = databaseService.allRoomBook(LocalDate.of(2023, 10, 01));
            System.out.println("All rooms booked for the given date: " + allBookedRooms);

            
            List<Reservation> freeRoomsByType = databaseService.freeRoomByType("Lux", LocalDate.of(2023, 10, 01));
            System.out.println("Free rooms of type Lux for the given date: " + freeRoomsByType);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
