package lab3;

import java.util.*;
import lab2java.Reservation;
import lab2java.RoomClass;

import java.time.LocalDate;

public class HotelServiswithoutStream implements HotelService {
    private List<Reservation> reservations;
    
    public HotelServiswithoutStream(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public List<Reservation> sortFreeRoomByBedCount(LocalDate date) {
        List<Reservation> freeRooms = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.isRoomFree(date)) {
                freeRooms.add(reservation);
            }
        }

        Collections.sort(freeRooms, Comparator.comparingInt(reservation -> reservation.getRoom().getBedCount()));
        return freeRooms;
    }

    @Override
    public List<Reservation> allRoomBook(LocalDate date) {
        List<Reservation> activeReservations = new ArrayList<>();
    
        for (Reservation reservation : reservations) {
            if (!(date.isBefore(reservation.getCheckIn()) || date.isAfter(reservation.getCheckOut()))) {
                activeReservations.add(reservation);
            }
        }
    
        return activeReservations;
    }
    @Override
public List<Reservation> freeRoomByType(RoomClass roomClass, LocalDate date) {
   
    List<Reservation> freeRoomsByType = new ArrayList<>();

    
    for (Reservation reservation : reservations) {
        
        if (reservation.isRoomFree(date) && reservation.getRoom().getType() == roomClass) {
           
            freeRoomsByType.add(reservation);
        }
    }

   
    return freeRoomsByType;
}

@Override
public List<Reservation> freeRomForGiveDate(int bedCount, LocalDate date) {
  
    List<Reservation> freeRoomsByBedCount = new ArrayList<>();

    
    for (Reservation reservation : reservations) {
        
        if (reservation.isRoomFree(date) && reservation.getRoom().getBedCount() == bedCount) {
            
            freeRoomsByBedCount.add(reservation);
        }
    }

   
    return freeRoomsByBedCount;
}

}
