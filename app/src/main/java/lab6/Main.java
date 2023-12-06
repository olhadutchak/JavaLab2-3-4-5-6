package lab6;
import java.time.LocalDate;
public class Main {
    public static void main(String[] args) {
        DatabaseService databaseService = new DatabaseService();
        databaseService.getFreeRoomsSortedByBedCountAndDate(LocalDate.now());  
        databaseService.allRoomBook(LocalDate.now());
        databaseService.freeRoomByType("DeLux", LocalDate.now());
    }
}

