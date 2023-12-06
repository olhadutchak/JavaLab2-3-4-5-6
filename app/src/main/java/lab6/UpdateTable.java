package lab6;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateTable {

    private final String url = "jdbc:postgresql://localhost/JavaHotel";
    private final String user = "teted";
    private final String password = "stick03";

    public void updateGuest(int guestId, String newFirstName, String newLastName, String newMiddleName) {
        String updateGuestSQL = "UPDATE guests SET first_name = ?, last_name = ?, middle_name = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(updateGuestSQL)) {

            preparedStatement.setString(1, newFirstName);
            preparedStatement.setString(2, newLastName);
            preparedStatement.setString(3, newMiddleName);
            preparedStatement.setInt(4, guestId);

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Guest updated successfully.");
            } else {
                System.out.println("No guest found with ID: " + guestId);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    public void updateRoomType(String number, int bedCount, String classRoom) {
        String updateRoomTypeSQL = "UPDATE room_types SET bed_count = ?, class = ? WHERE number = ?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(updateRoomTypeSQL)) {

            preparedStatement.setInt(1, bedCount);
            preparedStatement.setString(2, classRoom);
            preparedStatement.setString(3, number);

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("RoomType updated successfully.");
            } else {
                System.out.println("No Room found with number: " + number);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    public void updateReservation(int reservationId, String newRoomNumber, int newGuestId, String newCheckInDate, String newCheckOutDate) {
        String updateReservationSQL = "UPDATE reservations SET room_number = ?, guest_id = ?, check_in_date = ?, check_out_date = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(updateReservationSQL)) {
    
            preparedStatement.setString(1, newRoomNumber);
            preparedStatement.setInt(2, newGuestId);
            preparedStatement.setDate(3, java.sql.Date.valueOf(newCheckInDate));
            preparedStatement.setDate(4, java.sql.Date.valueOf(newCheckOutDate));
            preparedStatement.setInt(5, reservationId);
    
            int rowsUpdated = preparedStatement.executeUpdate();
    
            if (rowsUpdated > 0) {
                System.out.println("Reservation updated successfully.");
            } else {
                System.out.println("No Reservation found with ID: " + reservationId);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    public void updateRoomClass(String oldClass, String newClass) {
        String updateRoomClassSQL = "UPDATE room_classes SET class = ? WHERE class = ?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(updateRoomClassSQL)) {
    
            preparedStatement.setString(1, newClass);
            preparedStatement.setString(2, oldClass);
    
            int rowsUpdated = preparedStatement.executeUpdate();
    
            if (rowsUpdated > 0) {
                System.out.println("RoomClass updated successfully.");
            } else {
                System.out.println("No RoomClass found with class: " + oldClass);
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
