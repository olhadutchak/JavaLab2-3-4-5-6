package lab6;

public class MainInsert {

    public static void main(String[] args) {
        InsertInTable insertInTable = new InsertInTable();

        /*insertInTable.insertGuest("John", "Doe", "Mone");
        insertInTable.insertRoomClass("Lux");
        insertInTable.insertRoomClass("DeLux");
        insertInTable.insertRoomType("101", 2, "Lux");
        
        insertInTable.insertReservation("101", 2, "2023-12-01", "2023-12-07");*/
        insertInTable.insertRoomType("1", 3, "DeLux");
        insertInTable.insertReservation("1", 2, "2023-12-02", "2023-12-03");
    }
}