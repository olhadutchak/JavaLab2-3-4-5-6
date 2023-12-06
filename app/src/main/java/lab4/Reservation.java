package lab4;
import java.time.LocalDate;
import javax.validation.*;
import javax.validation.constraints.NotNull;

import java.util.*;
public class Reservation {
    @NotNull(message = "Room type cannot be null")
    private RoomType room;

    @NotNull(message = "Guest cannot be null")
    private Guest guest;
 
   @NotNull(message = "Check-out date cannot be null")
    private LocalDate checkIn;

    @NotNull(message = "Check-out date cannot be null")
    private LocalDate checkOut;


    public static class ReservationBuilder {
        private Reservation newReservation;

        public ReservationBuilder() {
            newReservation = new Reservation();
        }

        public ReservationBuilder roomNumber(RoomType room) {
            newReservation.room = room;
            return this;
        }

        public ReservationBuilder guest(Guest guest) {
            newReservation.guest = guest;
            return this;
        }

        public ReservationBuilder checkInDate(LocalDate checkIn) {
            newReservation.checkIn = checkIn;
            return this;
        }

        public ReservationBuilder checkOutDate(LocalDate checkOut) {
            newReservation.checkOut = checkOut;
            return this;
        }

        public Reservation build() {
            validateReservation(newReservation);
            return newReservation;
        }
    
        private static void validateReservation(Reservation reservation) {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<Reservation>> violations = validator.validate(reservation);
        
            if (!violations.isEmpty()) {
                List<ConstraintViolation<Reservation>> violationList = new ArrayList<>(violations);
                violationList.sort(Comparator.comparing(v -> v.getPropertyPath().toString()));
        
                StringBuilder errorMessage = new StringBuilder();
                for (ConstraintViolation<Reservation> violation : violationList) {
                    errorMessage.append(violation.getPropertyPath())
                            .append(": ")
                            .append(violation.getMessage())
                            .append("; ");
                }
                throw new IllegalArgumentException(errorMessage.toString());
            }
        }
        
    }
    @Override
    public String toString() {
        return "Reservation{" +
                "room=" + room +
                ", guest=" + guest +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Reservation other = (Reservation) obj;
        boolean roomEqual = Objects.equals(room, other.room);
        boolean guestEqual = Objects.equals(guest, other.guest);
        boolean checkInEqual = Objects.equals(checkIn, other.checkIn);

        return roomEqual && guestEqual && checkInEqual;
    }

    @Override
    public int hashCode() {
        return Objects.hash(room, guest, checkIn);
    }

    public RoomType getRoom() {
        return room;
    }

    public Guest getGuest() {
        return guest;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public boolean isRoomFree(LocalDate date) {
        return date.isBefore(checkIn) || date.isAfter(checkOut);
    }
}
