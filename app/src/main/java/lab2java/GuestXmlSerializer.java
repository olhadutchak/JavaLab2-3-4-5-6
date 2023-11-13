package lab2java;
import java.io.*;
import java.util.stream.Collectors;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class GuestXmlSerializer implements Serializable<Guest> {
    public void serialize(String fileName, Guest guest) throws IOException {
        XStream xStream = new XStream(new DomDriver());
        String xml = xStream.toXML(guest);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(xml);
        }
    }

    public Guest deserialize(String fileName, Class<Guest> clazz) throws IOException, ClassNotFoundException {
        XStream xStream = new XStream(new DomDriver());

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String xml = reader.lines().collect(Collectors.joining("\n"));
            return (Guest) xStream.fromXML(xml);
        }
    }
}