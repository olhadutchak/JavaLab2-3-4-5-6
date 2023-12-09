package lab6;

import java.sql.SQLException;
import java.time.LocalDate;

import lab2java.*;

public class MainUpdate {
    public static void main(String[] args) {
    try {
        UpdateTable updateTable = new UpdateTable();
        Guest person = new Guest.GuestBuilder()
                    .firstName("Johniiii")
                    .lastName("Doe")
                    .middleName("Smith")
                    .build();
        updateTable.updateGuest(1, person);
        RoomType room = new RoomType.RoomTypeBuilder()
        .number("101")
        .bedCount(2)
        .type(RoomClass.Standart)
        .build();
        updateTable.updateRoomType(room);

        Reservation reserv1 = new Reservation.ReservationBuilder()
            .roomNumber(room)
            .guest(person)
            .checkInDate(LocalDate.of(2023,10,01))
            .checkOutDate(LocalDate.of(2023, 10, 03))
            .build();  
        updateTable.updateReservation(1,  1, reserv1);
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}

