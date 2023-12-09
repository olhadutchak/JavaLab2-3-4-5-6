package lab5;


import static lab5.DatabaseConfig.*;

public class  DropTable {
    public static final String DELETE_RESERVATIONS_DATA = "DELETE FROM reservations";
    public static final String DELETE_ROOMTYPES_DATA = "DELETE FROM room_types";
    public static final String DELETE_GUESTS_DATA = "DELETE FROM guests";
    public static final String DELETE_ROOMCLASSES_DATA = "DELETE FROM room_classes";


    public static final String DROP_RESERVATIONS= "DROP TABLE IF EXISTS reservations";
    public static final String DROP_ROOMTYPES = "DROP TABLE IF EXISTS room_types";
    public static final String DROP_ROOMCLASSES = "DROP TABLE IF EXISTS room_classes";
    public static final String DROP_GUESTS = "DROP TABLE IF EXISTS guests";
    public static void  executeSQLs() {
         executeSQL(DELETE_RESERVATIONS_DATA);
         executeSQL(DELETE_ROOMTYPES_DATA );
         executeSQL(DELETE_GUESTS_DATA);
         executeSQL(DELETE_ROOMCLASSES_DATA);

         executeSQL(DROP_RESERVATIONS);
         executeSQL(DROP_ROOMTYPES);
         executeSQL(DROP_ROOMCLASSES);
         executeSQL(DROP_GUESTS);
    }

    public static void main(String[] args) {
         executeSQLs();
    }
}