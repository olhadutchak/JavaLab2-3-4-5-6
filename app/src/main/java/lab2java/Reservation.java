package lab2java;

import java.time.LocalDate;

import java.util.Objects;

public class Reservation {
    private RoomType room;
    private Guest guest;
    private LocalDate checkIn;
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
            return newReservation;
        }
    }


/**
     * Override the toString method to provide a string of the Reservation object.
     *
     * @return A string of the Reservation.
     */   
@Override
public String toString() {
        return "Reservation{" +
                "roomNumber='" + room + '\'' +
                ", Guest =" + guest +
                ",Indate='" + checkIn + '\'' +
                ",Outdate='" + checkOut + '\'' +
                '}';
    }

/**
     * Override the equals method for comparing Reservation objects by their room, guest, checkin, checkout.
     *
     * @param obj The object to compare.
     * @return true if the objects are the same by room, guest, checkin, checkout, or false if not.
     */
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
/**
     * Override the hashCode method to calculate the hash code by room, guest, checkin, checkout.
     *
     * @return The hash code of the Reservation object.
     */
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
