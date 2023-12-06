package lab6;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReadFromDatabase {

    private final String url = "jdbc:postgresql://localhost/JavaHotel";
    private final String user = "teted";
    private final String password = "stick03";

    public void readGuestsData() {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String selectGuestsSQL = "SELECT * FROM guests";

            try (PreparedStatement preparedStatement = connection.prepareStatement(selectGuestsSQL)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("ID");
                        String firstName = resultSet.getString("FIRST_NAME");
                        String lastName = resultSet.getString("LAST_NAME");
                        String middleName = resultSet.getString("MIDDLE_NAME");
                        System.out.println("ID: " + id + ", First Name: " + firstName +
                                ", Last Name: " + lastName + ", Middle Name: " + middleName);
                    }
                }
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void readRoomClassesData() {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String selectRoomClassesSQL = "SELECT * FROM room_classes";

            try (PreparedStatement preparedStatement = connection.prepareStatement(selectRoomClassesSQL)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String roomClass = resultSet.getString("CLASS");
                        System.out.println("Room Class: " + roomClass);
                    }
                }
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void readRoomTypesData() {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String selectRoomTypesSQL = "SELECT * FROM room_types";

            try (PreparedStatement preparedStatement = connection.prepareStatement(selectRoomTypesSQL)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String number = resultSet.getString("NUMBER");
                        int bedCount = resultSet.getInt("BED_COUNT");
                        String roomClass = resultSet.getString("CLASS");
                        System.out.println("Room Type: Number - " + number + ", Bed Count - " + bedCount +
                                ", Class - " + roomClass);
                    }
                }
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void readReservationsData() {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String selectReservationsSQL = "SELECT * FROM reservations";

            try (PreparedStatement preparedStatement = connection.prepareStatement(selectReservationsSQL)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("ID");
                        String roomNumber = resultSet.getString("ROOM_NUMBER");
                        int guestId = resultSet.getInt("GUEST_ID");
                        java.sql.Date checkInDate = resultSet.getDate("CHECK_IN_DATE");
                        java.sql.Date checkOutDate = resultSet.getDate("CHECK_OUT_DATE");

                        System.out.println("Reservation ID: " + id + ", Room Number: " + roomNumber +
                                ", Guest ID: " + guestId + ", Check-In Date: " + checkInDate +
                                ", Check-Out Date: " + checkOutDate);
                    }
                }
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
