package lab2java;

import java.io.*;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONSerialize<T> implements Serializable<T> {

    public void serialize(String fileName, T object) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(fileName), object);
    }


    public T deserialize(String fileName, Class<T> clazz) throws IOException, ClassNotFoundException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(fileName), clazz);
    }
}
