package lab6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import lab2java.*;
import lab5.DatabaseConfig;

public class UpdateTable {

    public boolean updateGuest(int id, Guest updatedGuest) throws SQLException {
        String updateGuestSQL = "UPDATE guests SET first_name = ?, last_name = ?, middle_name = ? WHERE document_number = ?";
        
        try (Connection connection = DriverManager.getConnection(DatabaseConfig.url, DatabaseConfig.user, DatabaseConfig.password);
             PreparedStatement preparedStatement = connection.prepareStatement(updateGuestSQL)) {
    
            preparedStatement.setString(1, updatedGuest.getFirstName());
            preparedStatement.setString(2, updatedGuest.getLastName());
            preparedStatement.setString(3, updatedGuest.getMiddleName());
            preparedStatement.setInt(4, id);
    
            int rowsUpdated = preparedStatement.executeUpdate();
    
           
            return rowsUpdated > 0;
           
        }
    }

    public void updateRoomType(RoomType room) throws SQLException {
        String updateRoomTypeSQL = "UPDATE room_types SET bed_count = ?, class = ? WHERE number = ?";
        try (Connection connection = DriverManager.getConnection(DatabaseConfig.url, DatabaseConfig.user, DatabaseConfig.password);
             PreparedStatement preparedStatement = connection.prepareStatement(updateRoomTypeSQL)) {

            preparedStatement.setString(1, room.getNumber());
            preparedStatement.setInt(2, room.getBedCount());
            preparedStatement.setString(3, room.getType().name());

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("RoomType updated successfully.");
            } else {
                System.out.println("No Room found with number: " + room.getNumber());
            }
        }
    }

    public void updateReservation(int reservationId, int guestid, Reservation reserv) throws SQLException {
        String updateReservationSQL = "UPDATE reservations SET room_number = ?, guest_id = ?, check_in_date = ?, check_out_date = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(DatabaseConfig.url, DatabaseConfig.user, DatabaseConfig.password);
             PreparedStatement preparedStatement = connection.prepareStatement(updateReservationSQL)) {

            preparedStatement.setString(1, reserv.getRoom().getNumber());
            preparedStatement.setInt(2, guestid);
            preparedStatement.setDate(3, java.sql.Date.valueOf(reserv.getCheckIn()));
            preparedStatement.setDate(4, java.sql.Date.valueOf(reserv.getCheckOut()));
            preparedStatement.setInt(5, reservationId);

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Reservation updated successfully.");
            } else {
                System.out.println("No Reservation found with ID: " + reservationId);
            }
        }
    }
}
