package lab5;
import static lab5.DatabaseConfig.*;

public class CreateTable{
    public static final String CREATE_GUEST = "CREATE TABLE guests " +
            "(ID SERIAL PRIMARY KEY," +
            " FIRST_NAME VARCHAR(50), " +
            " LAST_NAME VARCHAR(50), " +
            " MIDDLE_NAME VARCHAR(50))";
    
    public static final String CREATE_ROOMCLASS ="CREATE TABLE room_classes " +
            "(CLASS VARCHAR(50) PRIMARY KEY)";
    public static final String CREATE_ROOMTYPE = "CREATE TABLE room_types " +
            "(NUMBER VARCHAR(50) PRIMARY KEY," +
            " BED_COUNT INT, " +
            " CLASS VARCHAR(50) REFERENCES room_classes(CLASS))";
    public static final String CREATE_RESERVATIONS = "CREATE TABLE reservations " +
            "(ID SERIAL PRIMARY KEY," +
            " ROOM_NUMBER VARCHAR(50) REFERENCES room_types(NUMBER)," +
            " GUEST_ID INT REFERENCES guests(ID)," +
            " CHECK_IN_DATE DATE," +
            " CHECK_OUT_DATE DATE)";

    public static void executeSQLs() {
        executeSQL(CREATE_GUEST);
        executeSQL(CREATE_ROOMCLASS);
        executeSQL(CREATE_ROOMTYPE);
        executeSQL(CREATE_RESERVATIONS);
    }



    public static void main(String[] args) {
        executeSQLs();
    }

    
}
