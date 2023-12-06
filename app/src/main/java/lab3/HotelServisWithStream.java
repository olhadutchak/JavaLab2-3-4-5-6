package lab3;
import java.util.*;
import lab2java.Reservation;
import lab2java.RoomClass;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.util.function.Predicate;

public class HotelServisWithStream implements HotelService {
    private List<Reservation> reservations;

    public HotelServisWithStream(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    private Predicate<Reservation> freeReservation(LocalDate date) {
        return reservation -> reservation.isRoomFree(date);
    }

    @Override
    public List<Reservation> sortFreeRoomByBedCount(LocalDate date) {
        return reservations.stream()
                .filter(freeReservation(date))
                .sorted(Comparator.comparingInt(reservation -> reservation.getRoom().getBedCount()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Reservation> allRoomBook(LocalDate date) {
        return reservations.stream()
                .filter(reservation -> !(date.isBefore(reservation.getCheckIn()) || date.isAfter(reservation.getCheckOut())))
                .collect(Collectors.toList());
    }

    @Override
    public List<Reservation> freeRoomByType(RoomClass roomClass, LocalDate date) {
        return reservations.stream()
                .filter(freeReservation(date))
                .filter(reservation -> reservation.getRoom().getType().equals(roomClass))
                .collect(Collectors.toList());
    }

    @Override
    public List<Reservation> freeRomForGiveDate(int bedCount, LocalDate date) {
        return reservations.stream()
                .filter(freeReservation(date))
                .filter(reservation -> reservation.getRoom().getBedCount() == bedCount) 
                .collect(Collectors.toList());
    }
}
