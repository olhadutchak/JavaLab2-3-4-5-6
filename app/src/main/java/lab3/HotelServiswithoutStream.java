package lab3;
import java.util.*;
import lab2java.Reservation;
import java.time.LocalDate;
public class HotelServiswithoutStream  implements HotelService{
    private List<Reservation> reserv;

    public HotelServiswithoutStream(List<Reservation> reserv){
        this.reserv = reserv;
    }
    public boolean isRoomFree(LocalDate date, Reservation reservation) {
        return reservation != null && (date.isBefore(reservation.getCheckIn()) || date.isAfter(reservation.getCheckOut()));
    }
    
    @Override
    public List<Reservation> SortFreeRoombybedcount(LocalDate date){
         // Filter out reservations for the specified date
         List<Reservation> freeRooms = new ArrayList<>();
         for (Reservation reservation : reserv) {
             if (reservation.isRoomFree(date)) {
                 freeRooms.add(reservation);
             }
         }
 
         // Sort the list of free rooms by bed count
         Collections.sort(freeRooms, Comparator.comparingInt(reservation -> reservation.getRoom().getBedCount()));
 
         return freeRooms;
     }
    };
    @Override
    public List<Reservation> AllRoomRorBook() {
        
        return null;
    }

    @Override
    public List<Reservation> FreeRoomByType() {
        
        return null;
    }

    @Override
    public List<Reservation> FreeRomForGiveDate() {
        
        return null;
    }
}
