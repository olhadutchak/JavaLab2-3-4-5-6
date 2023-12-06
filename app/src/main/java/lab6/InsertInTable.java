package lab6;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertInTable {

    private final String url = "jdbc:postgresql://localhost/JavaHotel";
    private final String user = "teted";
    private final String password = "stick03";

    public void insertGuest(String firstName, String lastName, String middleName) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String insertGuestSQL = "INSERT INTO guests (first_name, last_name, middle_name) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertGuestSQL)) {
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);
                preparedStatement.setString(3, middleName);
                preparedStatement.executeUpdate();
                System.out.println("Guest inserted successfully.");
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void insertRoomClass(String roomClass) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String insertRoomClassSQL = "INSERT INTO room_classes (class) VALUES (?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertRoomClassSQL)) {
                preparedStatement.setString(1, roomClass);
                preparedStatement.executeUpdate();
                System.out.println("RoomClass inserted successfully.");
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void insertRoomType(String number, int bedCount, String roomClass) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String insertRoomTypeSQL = "INSERT INTO room_types (number, bed_count, class) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertRoomTypeSQL)) {
                preparedStatement.setString(1, number);
                preparedStatement.setInt(2, bedCount);
                preparedStatement.setString(3, roomClass);
                preparedStatement.executeUpdate();
                System.out.println("RoomType inserted successfully.");
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void insertReservation(String roomNumber, int guestId, String checkInDate, String checkOutDate) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String insertReservationSQL = "INSERT INTO reservations (room_number, guest_id, check_in_date, check_out_date) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertReservationSQL)) {
                preparedStatement.setString(1, roomNumber);
                preparedStatement.setInt(2, guestId);
                preparedStatement.setDate(3, java.sql.Date.valueOf(checkInDate));
                preparedStatement.setDate(4, java.sql.Date.valueOf(checkOutDate));
                preparedStatement.executeUpdate();
                System.out.println("Reservation inserted successfully.");
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