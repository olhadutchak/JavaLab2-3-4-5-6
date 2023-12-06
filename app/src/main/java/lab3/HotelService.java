package lab3;
import lab2java.Reservation;
import lab2java.RoomClass;

import java.time.LocalDate;
import java.util.List;
public interface HotelService {
    List <Reservation> sortFreeRoomByBedCount(LocalDate date);
    List <Reservation> allRoomBook(LocalDate date);
    List <Reservation> freeRoomByType(RoomClass roomClass, LocalDate date);
    List <Reservation> freeRomForGiveDate(int bedCount, LocalDate date); 
}
