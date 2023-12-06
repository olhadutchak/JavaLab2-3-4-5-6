package lab5;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {

    private final String url = "jdbc:postgresql://localhost/JavaHotel";
    private final String user = "teted";
    private final String password = "stick03";

    private static final String createGuestsTableSQL = "CREATE TABLE guests " +
            "(ID SERIAL PRIMARY KEY," +
            " FIRST_NAME VARCHAR(50), " +
            " LAST_NAME VARCHAR(50), " +
            " MIDDLE_NAME VARCHAR(50))";

    private static final String createRoomTypesTableSQL = "CREATE TABLE room_types " +
            "(NUMBER VARCHAR(50) PRIMARY KEY," +
            " BED_COUNT INT, " +
            " CLASS VARCHAR(50) REFERENCES room_classes(CLASS))";

    private static final String createRoomClassesTableSQL = "CREATE TABLE room_classes " +
            "(CLASS VARCHAR(50) PRIMARY KEY)";

    private static final String createReservationsTableSQL = "CREATE TABLE reservations " +
            "(ID SERIAL PRIMARY KEY," +
            " ROOM_NUMBER VARCHAR(50) REFERENCES room_types(NUMBER)," +
            " GUEST_ID INT REFERENCES guests(ID)," +
            " CHECK_IN_DATE DATE," +
            " CHECK_OUT_DATE DATE)";

    public static void main(String[] argv) throws SQLException {
        CreateTable createTableExample = new CreateTable();
        createTableExample.createTable();
    }

    public void createTable() throws SQLException {
        System.out.println(createGuestsTableSQL);
        System.out.println(createRoomTypesTableSQL);
        System.out.println(createRoomClassesTableSQL);
        System.out.println(createReservationsTableSQL);

        
        try (Connection connection = DriverManager.getConnection(url, user, password);
            
             Statement statement = connection.createStatement()) {

            
            statement.execute(createGuestsTableSQL);
            System.out.println("Guests table created successfully.");

            statement.execute(createRoomClassesTableSQL);
            System.out.println("RoomClasses table created successfully.");

            statement.execute(createRoomTypesTableSQL);
            System.out.println("RoomTypes table created successfully.");

            statement.execute(createReservationsTableSQL);
            System.out.println("Reservations table created successfully.");
        } catch (SQLException e) {
           
            printSQLException(e);
        }
    }

    public static void printSQLException(SQLException ex) {
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
