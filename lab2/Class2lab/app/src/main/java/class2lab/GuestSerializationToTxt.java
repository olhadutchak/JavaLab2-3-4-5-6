package class2lab;
import java.io.*;

public class GuestSerializationToTxt implements Serializable<Guest> {

    
    public void serialize(String fileName, Guest guest) throws IOException {
        try (FileWriter fileWriter = new FileWriter(fileName);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            String guestInfo = guest.getFirstName() + "," + guest.getLastName() + "," + guest.getMiddleName();
            bufferedWriter.write(guestInfo);
        }
    }

    public Guest deserialize(String fileName, Class<Guest> clazz) throws IOException, ClassNotFoundException {
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line = bufferedReader.readLine();
            if (line == null) {
                throw new IOException("File is empty");
            }

            String[] parts = line.split(",");
            if (parts.length != 3) {
                throw new IOException("Invalid data format in the file");
            }

            return new Guest.GuestBuilder()
                .firstName(parts[0])
                .lastName(parts[1])
                .middleName(parts[2])
                .build();
        }
    }
}
