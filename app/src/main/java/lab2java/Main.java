package lab2java;

import java.time.LocalDate;
import java.io.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {

        Guest person = new Guest.GuestBuilder()
            .firstName("John")
            .lastName("Doe")
            .middleName("Smith")
            .build();
        Guest person1 = new Guest.GuestBuilder()
            .firstName("John2")
            .lastName("Doe")
            .middleName("Smith")
            .build();

         Guest person2 = new Guest.GuestBuilder()
            .firstName("Mayk")
            .lastName("Doesde")
            .middleName("Orle")
            .build();


        RoomType room = new RoomType.RoomTypeBuilder()
            .number("101")
            .bedCount(2)
            .type(RoomClass.Standart)
            .build();
        RoomType room1 = new RoomType.RoomTypeBuilder()
            .number("102")
            .bedCount(2)
            .type(RoomClass.Standart)
            .build();


        Reservation reserv = new Reservation.ReservationBuilder()
            .roomNumber(room)
            .guest(person)
            .checkInDate(LocalDate.of(2023,10,01))
            .checkOutDate(LocalDate.of(2023, 10, 03))
            .build();
         Reservation reserv1 = new Reservation.ReservationBuilder()
            .roomNumber(room1)
            .guest(person1)
            .checkInDate(LocalDate.of(2023,10,01))
            .checkOutDate(LocalDate.of(2023, 10, 03))
            .build();  

        System.out.println(room);
        System.out.println(reserv);
        System.out.println(person);

        System.out.println("\nCheck room");
        System.out.println(room.equals(room1));
        System.out.println(room.hashCode());
        System.out.println(room1.hashCode());   

        System.out.println("\nCheck reserv");
        System.out.println(reserv.equals(reserv1));
        System.out.println(reserv.hashCode());
        System.out.println(reserv1.hashCode());  

        System.out.println("\nCheck guest");
        System.out.println(person.equals(person1));
        System.out.println(person.hashCode());
        System.out.println(person1.hashCode());


        try {
            String jsonFileName = "person1.json";
            JSONSerialize<Guest> serializer = new JSONSerialize<>();
            serializer.serialize(jsonFileName, person);
            Guest deserializedPjson = serializer.deserialize(jsonFileName, Guest.class);

            System.out.println("Deserialized person object: " + deserializedPjson.getFirstName() + ", " + deserializedPjson.getLastName() + ", " + deserializedPjson.getMiddleName());



            GuestSerializationToTxt txtSerializer = new GuestSerializationToTxt();


            String txtFileName = "person.txt";
            serializer.serialize(jsonFileName, person);



            txtSerializer.serialize(txtFileName, person);


            Guest deserializedPerson = txtSerializer.deserialize(txtFileName,Guest.class);


            System.out.println("Deserialized person object: " + deserializedPerson.getFirstName() + ", " + deserializedPerson.getLastName() + ", " + deserializedPerson.getMiddleName());



        } catch (IOException e) {
            e.printStackTrace();
        }



        try {
              String xmlFileName = "person.xml";
              GuestXmlSerializer xmlSerializer = new GuestXmlSerializer();

            xmlSerializer.serialize(xmlFileName, person);


            Guest deserializedPerson = xmlSerializer.deserialize(xmlFileName, Guest.class);


            System.out.println("Deserialized person object: " + deserializedPerson.getFirstName() + ", " + deserializedPerson.getLastName() + ", " + deserializedPerson.getMiddleName());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
