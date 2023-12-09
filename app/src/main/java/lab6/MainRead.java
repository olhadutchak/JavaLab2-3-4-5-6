package lab6;

import java.util.List;
import java.sql.SQLException;
import lab2java.*;

public class MainRead {
    public static void main(String[] args) {
        Read reader = new Read();

        try {
            List<Guest> guests = reader.readGuests();
            System.out.println("Guests: " + guests);

            List<String> roomClasses = reader.readRoomClasses();
            System.out.println("Room Classes: " + roomClasses);

            List<RoomType> roomTypes = reader.readRoomTypes();
            System.out.println("Room Types: " + roomTypes);

            
            Reservation reservationById = reader.readReservationsById(1);
            System.out.println("Reservation by ID: " + reservationById);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
