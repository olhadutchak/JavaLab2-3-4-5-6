package lab3;
import lab2java.Reservation;
import java.time.LocalDate;
import java.util.List;
public interface HotelService {
    List <Reservation> SortFreeRoombybedcount(LocalDate date);
    List <Reservation> AllRoomRorBook();
    List <Reservation> FreeRoomByType();
    List <Reservation> FreeRomForGiveDate(); 
}
