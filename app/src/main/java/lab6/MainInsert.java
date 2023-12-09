package lab6;

import java.sql.SQLException;
import java.time.LocalDate;

import lab2java.Guest;
import lab2java.Reservation;
import lab2java.RoomClass;
import lab2java.RoomType;

public class MainInsert {

    public static void main(String[] args) {
        InsertInTable insertInTable = new InsertInTable();

        try {
            Guest person = new Guest.GuestBuilder()
                    .firstName("John")
                    .lastName("Doe")
                    .middleName("Smith")
                    .build();
            insertInTable.insertGuest(person);

            insertInTable.insertRoomClass("Lux");
            insertInTable.insertRoomClass("DeLux");
            insertInTable.insertRoomClass("Standart");
            insertInTable.insertRoomClass("Superior");

            RoomType room = new RoomType.RoomTypeBuilder()
                    .number("101")
                    .bedCount(2)
                    .type(RoomClass.Lux)
                    .build();

            insertInTable.insertRoomType(room);

            Reservation reserv = new Reservation.ReservationBuilder()
                    .roomNumber(new RoomType.RoomTypeBuilder()
                            .number("101")
                            .bedCount(2)
                            .type(RoomClass.Lux)
                            .build())
                    .guest(person)
                    .checkInDate(LocalDate.of(2023, 10, 01))
                    .checkOutDate(LocalDate.of(2023, 10, 03))
                    .build();
            insertInTable.insertReservation(reserv);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}