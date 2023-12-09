package lab6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lab2java.*;
import static lab5.DatabaseConfig.*;

public class Read {

    private List<Reservation> readReservations(ResultSet resultSet) throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        while (resultSet.next()) {
            Reservation reservation = getReservationFromResultList(resultSet);
            reservations.add(reservation);
        }
        return reservations;
    }

    private Reservation getReservationFromResultList(ResultSet resultSet) throws SQLException {
        String roomNumber = resultSet.getString("room_number");
        int bedCountB = resultSet.getInt("bed_count");
        LocalDate checkInDate = resultSet.getDate("check_in_date").toLocalDate();
        LocalDate checkOutDate = resultSet.getDate("check_out_date").toLocalDate();
        String guestFirstName = resultSet.getString("first_name");
        String guestLastName = resultSet.getString("last_name");
        String guestMiddleName = resultSet.getString("middle_name");
        String roomClass = resultSet.getString("class");

        RoomType roomType = new RoomType.RoomTypeBuilder()
                .number(roomNumber)
                .bedCount(bedCountB)
                .type(RoomClass.valueOf(roomClass))
                .build();

        Guest guest = new Guest.GuestBuilder()
                .firstName(guestFirstName)
                .lastName(guestLastName)
                .middleName(guestMiddleName)
                .build();

        return new Reservation.ReservationBuilder()
                .roomNumber(roomType)
                .guest(guest)
                .checkInDate(checkInDate)
                .checkOutDate(checkOutDate)
                .build();
    }

    public List<Guest> readGuests() throws SQLException {
        List<Guest> guests = new ArrayList<>();
        String selectGuestsSQL = "SELECT * FROM guests";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(selectGuestsSQL)) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String firstName = resultSet.getString("FIRST_NAME");
                    String lastName = resultSet.getString("LAST_NAME");
                    String middleName = resultSet.getString("MIDDLE_NAME");

                    Guest guest = new Guest.GuestBuilder()
                            .firstName(firstName)
                            .lastName(lastName)
                            .middleName(middleName)
                            .build();

                    guests.add(guest);
                }
            }
        }
        return guests;
    }

    public List<String> readRoomClasses() throws SQLException {
        List<String> roomClasses = new ArrayList<>();
        String selectRoomClassesSQL = "SELECT * FROM room_classes";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(selectRoomClassesSQL)) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String roomClass = resultSet.getString("CLASS");
                    roomClasses.add(roomClass);
                }
            }
        }
        return roomClasses;
    }

    public List<RoomType> readRoomTypes() throws SQLException {
        List<RoomType> roomTypes = new ArrayList<>();
        String selectRoomTypesSQL = "SELECT * FROM room_types";
        try (Connection connection = DriverManager.getConnection(url, user,password);
             PreparedStatement preparedStatement = connection.prepareStatement(selectRoomTypesSQL)) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String number = resultSet.getString("NUMBER");
                    int bedCount = resultSet.getInt("BED_COUNT");
                    String roomClass = resultSet.getString("CLASS");

                    
                    RoomType roomType = new RoomType.RoomTypeBuilder()
                    .number(number)
                    .bedCount(bedCount)
                    .type(RoomClass.valueOf(roomClass))
                    .build();
                    roomTypes.add(roomType);
                }
            }
        }
        return roomTypes;
    }

    public List<Reservation> readReservations() throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        String selectReservationsSQL = "SELECT * FROM reservations";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(selectReservationsSQL)) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                reservations = readReservations(resultSet);
            }
        }
        return reservations;
    }
    public Reservation readReservationsById(int reservationId) throws SQLException {
       Reservation reservation = null;
        String selectReservationByIdSQL = "SELECT * FROM reservations WHERE ID = ?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(selectReservationByIdSQL)) {
    
            preparedStatement.setInt(1, reservationId);
    
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                     reservation = getReservationFromResultList(resultSet);
                  
                }
            }
        }
        return reservation;
    }
}