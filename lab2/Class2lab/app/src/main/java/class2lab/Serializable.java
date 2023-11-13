package class2lab;

import java.io.IOException;

public interface Serializable<T> {
    void serialize(String fileName, T object) throws IOException;
    T deserialize(String fileName, Class<T> clazz) throws IOException, ClassNotFoundException;
}
