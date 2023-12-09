package lab6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lab2java.Guest;
import lab2java.Reservation;
import lab2java.RoomType;
import lab5.DatabaseConfig;

public class InsertInTable {

    public void insertGuest(Guest guest) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DatabaseConfig.url, DatabaseConfig.user, DatabaseConfig.password)) {
            String insertGuestSQL = "INSERT INTO guests (first_name, last_name, middle_name) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertGuestSQL)) {
                preparedStatement.setString(1, guest.getFirstName());
                preparedStatement.setString(2, guest.getLastName());
                preparedStatement.setString(3, guest.getMiddleName());
                preparedStatement.executeUpdate();
                System.out.println("Guest inserted successfully.");
            }
        }
    }

    public void insertRoomType(RoomType roomType) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DatabaseConfig.url, DatabaseConfig.user, DatabaseConfig.password)) {
            String insertRoomTypeSQL = "INSERT INTO room_types (number, bed_count, class) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertRoomTypeSQL)) {
                preparedStatement.setString(1, roomType.getNumber());
                preparedStatement.setInt(2, roomType.getBedCount());
                preparedStatement.setString(3, roomType.getType().name());
                preparedStatement.executeUpdate();
                System.out.println("RoomType inserted successfully.");
            }
        }
    }

    public void insertReservation(Reservation reservation) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DatabaseConfig.url, DatabaseConfig.user, DatabaseConfig.password)) {
            String insertReservationSQL = "INSERT INTO reservations (room_number, guest_id, check_in_date, check_out_date) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertReservationSQL)) {
                preparedStatement.setString(1, reservation.getRoom().getNumber());

                Guest guest = reservation.getGuest();

                int guestId = getGuestId(connection, guest);
                if (guestId == -1) {
                    guestId = getGeneratedGuestId(connection);
                    insertGuest(guest);
                }

                preparedStatement.setInt(2, guestId);

                preparedStatement.setDate(3, java.sql.Date.valueOf(reservation.getCheckIn()));
                preparedStatement.setDate(4, java.sql.Date.valueOf(reservation.getCheckOut()));
                preparedStatement.executeUpdate();

                System.out.println("Reservation inserted successfully with guest ID: " + guestId);
            }
        }
    }

    private int getGuestId(Connection connection, Guest guest) throws SQLException {
        String selectGuestIdSQL = "SELECT id FROM guests WHERE first_name = ? AND last_name = ? AND middle_name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectGuestIdSQL)) {
            preparedStatement.setString(1, guest.getFirstName());
            preparedStatement.setString(2, guest.getLastName());
            preparedStatement.setString(3, guest.getMiddleName());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                } else {
                    return -1; 
                }
            }
        }
    }

    private int getGeneratedGuestId(Connection connection) throws SQLException {
        String selectLastGuestIdSQL = "SELECT currval('guests_id_seq')";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectLastGuestIdSQL)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                } else {
                    throw new SQLException("Failed to retrieve generated guest ID.");
                }
            }
        }
    }

    public void insertRoomClass(String roomClass) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DatabaseConfig.url, DatabaseConfig.user, DatabaseConfig.password)) {
            String insertRoomClassSQL = "INSERT INTO room_classes (class) VALUES (?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertRoomClassSQL)) {
                preparedStatement.setString(1, roomClass);
                preparedStatement.executeUpdate();
                System.out.println("RoomClass inserted successfully.");
            }
        }
    }

    
}
