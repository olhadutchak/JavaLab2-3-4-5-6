package lab6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import lab5.DatabaseConfig;

public class DeleteFromTable {
    public void deleteGuest(int guestId) {
        try {
            deleteRecord("guests", "id", guestId, "Guest");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRoomType(String number) {
        try {
            deleteRecord("room_types", "number", number, "RoomType");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteReservation(int reservationId) {
        try {
            deleteRecord("reservations", "id", reservationId, "Reservation");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRoomClass(String roomClass) {
       try (Connection connection = DriverManager.getConnection(DatabaseConfig.url, DatabaseConfig.user, DatabaseConfig.password)) {
                    deleteRecords("room_types", "class", roomClass);
        
                    String deleteSQL = "DELETE FROM room_classes WHERE class = ?";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
                        setParameter(preparedStatement, 1, roomClass);
                        int rowsDeleted = preparedStatement.executeUpdate();
            
                        printResult(rowsDeleted, "RoomClass");
                    }
               
            }catch (SQLException e) {
                 e.printStackTrace();
    }
}

    private void deleteRecord(String tableName, String columnName, Object value, String entityName) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DatabaseConfig.url, DatabaseConfig.user, DatabaseConfig.password)) {
            deleteRecords(tableName, columnName, value);
            String deleteSQL = "DELETE FROM " + tableName + " WHERE " + columnName + " = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
                setParameter(preparedStatement, 1, value);
                int rowsDeleted = preparedStatement.executeUpdate();

                printResult(rowsDeleted, entityName);
            }
        }
    }

    private void deleteRecords(String tableName, String columnName, Object value) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DatabaseConfig.url, DatabaseConfig.user, DatabaseConfig.password)) {
            String deleteSQL = "DELETE FROM " + tableName + " WHERE " + columnName + " = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
                setParameter(preparedStatement, 1, value);
                int rowsDeleted = preparedStatement.executeUpdate();
                System.out.println("Deleted " + rowsDeleted + " records from " + tableName + ".");
            }
        }
    }

    private void setParameter(PreparedStatement preparedStatement, int index, Object value) throws SQLException {
        if (value instanceof Integer) {
            preparedStatement.setInt(index, (Integer) value);
        } else if (value instanceof String) {
            preparedStatement.setString(index, (String) value);
        }
    }

    private void printResult(int rowsDeleted, String entityName) {
        if (rowsDeleted > 0) {
            System.out.println(entityName + " deleted successfully.");
        } else {
            System.out.println("No " + entityName + " found with specified criteria.");
        }
    }
}
