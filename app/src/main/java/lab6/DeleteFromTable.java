package lab6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteFromTable {

    private final String url = "jdbc:postgresql://localhost/JavaHotel";
    private final String user = "teted";
    private final String password = "stick03";

    public void deleteGuest(int guestId) {
        deleteRecord("guests", "id", guestId, "Guest");
    }

    public void deleteRoomType(String number) {
        deleteRecord("room_types", "number", number, "RoomType");
    }

    public void deleteReservation(int reservationId) {
        deleteRecord("reservations", "id", reservationId, "Reservation");
    }

    public void deleteRoomClass(String roomClass) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            // Видаляємо записи з room_types, які мають зовнішній ключ на room_classes
            deleteRecords("room_types", "class", roomClass);

            // Тепер можемо видалити запис з room_classes
            String deleteSQL = "DELETE FROM room_classes WHERE class = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
                setParameter(preparedStatement, 1, roomClass);
                int rowsDeleted = preparedStatement.executeUpdate();

                if (rowsDeleted > 0) {
                    System.out.println("RoomClass deleted successfully.");
                } else {
                    System.out.println("No RoomClass found with class: " + roomClass);
                }
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    private void deleteRecord(String tableName, String columnName, Object value, String entityName) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            // Видаляємо записи з reservations, які мають зовнішній ключ на guests
            deleteRecords("reservations", columnName, value);

            // Тепер можемо видалити основний запис
            String deleteSQL = "DELETE FROM " + tableName + " WHERE " + columnName + " = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
                setParameter(preparedStatement, 1, value);
                int rowsDeleted = preparedStatement.executeUpdate();

                if (rowsDeleted > 0) {
                    System.out.println(entityName + " deleted successfully.");
                } else {
                    System.out.println("No " + entityName + " found with specified criteria.");
                }
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    private void deleteRecords(String tableName, String columnName, Object value) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String deleteSQL = "DELETE FROM " + tableName + " WHERE " + columnName + " = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
                setParameter(preparedStatement, 1, value);
                int rowsDeleted = preparedStatement.executeUpdate();
                System.out.println("Deleted " + rowsDeleted + " records from " + tableName + ".");
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    private void setParameter(PreparedStatement preparedStatement, int index, Object value) throws SQLException {
        if (value instanceof Integer) {
            preparedStatement.setInt(index, (Integer) value);
        } else if (value instanceof String) {
            preparedStatement.setString(index, (String) value);
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
