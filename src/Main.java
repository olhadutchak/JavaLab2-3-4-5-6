import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
       
        Guest guest = new Guest.GuestBuilder()
            .firstName("John")
            .lastName("Doe")
            .middleName("Smith")
            .build();
        Guest guest2 = new Guest.GuestBuilder()
            .firstName("John")
            .lastName("Doe")
            .middleName("Smith")
            .build();
        

        
        RoomType room = new RoomType.RoomTypeBuilder()
            .Number("101")
            .bedCount(2)
            .Class(RoomClass.Standart)
            .build();
        RoomType room1 = new RoomType.RoomTypeBuilder()
            .Number("102")
            .bedCount(2)
            .Class(RoomClass.Standart)
            .build();
        

        Reservation reserv = new Reservation.ReservationBuilder()
            .roomNumber(room)
            .guest(guest)
            .checkInDate(LocalDate.of(2023,10,01))
            .checkOutDate(LocalDate.of(2023, 10, 03))
            .build();
         Reservation reserv1 = new Reservation.ReservationBuilder()
            .roomNumber(room1)
            .guest(guest2)
            .checkInDate(LocalDate.of(2023,10,01))
            .checkOutDate(LocalDate.of(2023, 10, 03))
            .build();  
        
        System.out.println(room);
        System.out.println(reserv);
        System.out.println(guest);

        System.out.println("\nCheck room");
        System.out.println(room.equals(room1));
        System.out.println(room.hashCode());
        System.out.println(room1.hashCode());   
        
        System.out.println("\nCheck reserv");
        System.out.println(reserv.equals(reserv1));
        System.out.println(reserv.hashCode());
        System.out.println(reserv1.hashCode());  
        
        System.out.println("\nCheck guest");
        System.out.println(guest.equals(guest2));
        System.out.println(guest.hashCode());
        System.out.println(guest2.hashCode());

        
    }
}
