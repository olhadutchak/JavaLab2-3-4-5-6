package lab6;

public class MainRead {
    public static void main(String[] args) {
        ReadFromDatabase reader = new ReadFromDatabase();
        reader.readGuestsData();
        reader.readRoomClassesData();
        reader.readRoomTypesData();
        reader.readReservationsData();
    }
}
