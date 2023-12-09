package lab6;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lab2java.*;
import lab5.DatabaseConfig;
public class DatabaseService {

    private final String SELECT_RESERVATIONS_QUERY =
            "SELECT * FROM reservations " +
            "INNER JOIN room_types ON reservations.room_number = room_types.number " +
            "INNER JOIN guests ON reservations.guest_id = guests.id " +
            "INNER JOIN room_classes ON room_types.class = room_classes.class ";

    public List<Reservation> freeRoomForGiveDateAndBedCount(int bedCount, LocalDate date) throws SQLException {
        List<Reservation> freeRoomsByBedCount = new ArrayList<>();
        String query = SELECT_RESERVATIONS_QUERY +
                "WHERE ? NOT BETWEEN reservations.check_in_date AND reservations.check_out_date " +
                "AND room_types.bed_count = ?";

        try (Connection connection = DriverManager.getConnection(DatabaseConfig.url, DatabaseConfig.user, DatabaseConfig.password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDate(1, java.sql.Date.valueOf(date));
            preparedStatement.setInt(2, bedCount);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
               freeRoomsByBedCount = processResultList(resultSet);
            }
        }

        return freeRoomsByBedCount;
    }
    public List<Reservation> allRoomBook(LocalDate date) throws SQLException {
        List<Reservation> allRoomBooklist = new ArrayList<>();

        String query = SELECT_RESERVATIONS_QUERY +
                "WHERE ? BETWEEN reservations.check_in_date AND reservations.check_out_date";

        try (Connection connection = DriverManager.getConnection(DatabaseConfig.url, DatabaseConfig.user, DatabaseConfig.password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setDate(1, java.sql.Date.valueOf(date));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                allRoomBooklist = processResultList(resultSet);
            }
        }

        return allRoomBooklist;
    }

    public List<Reservation> freeRoomByType(String roomClass, LocalDate date) throws SQLException {
        List<Reservation> freeRoomsByType = new ArrayList<>();
    
        String query = SELECT_RESERVATIONS_QUERY +
                "WHERE room_types.class = ? AND (" +
                "    ? < reservations.check_in_date OR ? > reservations.check_out_date" +
                ")";
        try (Connection connection = DriverManager.getConnection(DatabaseConfig.url, DatabaseConfig.user, DatabaseConfig.password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
    
            preparedStatement.setString(1, roomClass);
            preparedStatement.setDate(2, java.sql.Date.valueOf(date));
            preparedStatement.setDate(3, java.sql.Date.valueOf(date));
    
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                freeRoomsByType =  processResultList(resultSet);
            }
        }
    
        return freeRoomsByType;
    }

    private List<Reservation> processResultList(ResultSet resultSet) throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        try (resultSet) {
            while (resultSet.next()) {
                Reservation reservation = getReservationFromResultList(resultSet);
                reservations.add(reservation);
            }
            return reservations;
        }
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

    
}
