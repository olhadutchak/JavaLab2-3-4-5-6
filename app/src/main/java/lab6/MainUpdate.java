package lab6;

public class MainUpdate {
    public static void main(String[] args) {
        UpdateTable updateTable = new UpdateTable();
        updateTable.updateGuest(1, "NewJohn", "NewDoe", "NewMone");
        updateTable.updateRoomType("101", 4, "Lux");
        updateTable.updateReservation(1, "101", 1, "2023-12-02", "2023-12-07");
        updateTable.updateRoomClass("DeLux", "NewLux");
        
    }
}

