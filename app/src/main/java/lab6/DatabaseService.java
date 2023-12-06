package lab6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;


public class DatabaseService {

    private final String url = "jdbc:postgresql://localhost/JavaHotel";
    private final String user = "teted";
    private final String password = "stick03";

    public void getFreeRoomsSortedByBedCountAndDate(LocalDate searchDate) {
        String query = "SELECT * FROM room_types " +
                       "WHERE NOT EXISTS (" +
                       "    SELECT 1 FROM reservations " +
                       "    WHERE room_number = room_types.number AND ? BETWEEN check_in_date AND check_out_date" +
                       ")" +
                       "ORDER BY bed_count DESC";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setDate(1, java.sql.Date.valueOf(searchDate));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String roomNumber = resultSet.getString("number");
                    int bedCount = resultSet.getInt("bed_count");
                    String roomClass = resultSet.getString("class");
                    System.out.println("Room Number: " + roomNumber);
                    System.out.println("Bed Count: " + bedCount);
                    System.out.println("Room Class: " + roomClass);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void allRoomBook(LocalDate searchDate) {
        String query = "SELECT * FROM reservations " +
                       "WHERE ? BETWEEN check_in_date AND check_out_date";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setDate(1, java.sql.Date.valueOf(searchDate));  // Дата для пошуку заброньованих номерів

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int reservationId = resultSet.getInt("id");
                    String roomNumber = resultSet.getString("room_number");
                    int guestId = resultSet.getInt("guest_id");
                    LocalDate checkInDate = resultSet.getDate("check_in_date").toLocalDate();
                    LocalDate checkOutDate = resultSet.getDate("check_out_date").toLocalDate();

                    System.out.println("Reservation ID: " + reservationId);
                    System.out.println("Room Number: " + roomNumber);
                    System.out.println("Guest ID: " + guestId);
                    System.out.println("Check-In Date: " + checkInDate);
                    System.out.println("Check-Out Date: " + checkOutDate);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void freeRoomByType(String roomClass, LocalDate date) {
        String query = "SELECT * FROM room_types " +
                       "WHERE class = ? AND NOT EXISTS (" +
                       "    SELECT 1 FROM reservations " +
                       "    WHERE room_number = room_types.number AND ? BETWEEN check_in_date AND check_out_date" +
                       ")";
    
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
    
            preparedStatement.setString(1, roomClass);
            preparedStatement.setDate(2, java.sql.Date.valueOf(date));
    
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String roomNumber = resultSet.getString("number");
                    int bedCount = resultSet.getInt("bed_count");
                    String roomClassResult = resultSet.getString("class");
    
                    System.out.println("Room Number: " + roomNumber);
                    System.out.println("Bed Count: " + bedCount);
                    System.out.println("Room Class: " + roomClassResult);
                }
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
