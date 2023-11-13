package lab2java;

import java.util.Objects;

public class RoomType {
    private String number;
    private int bedCount;
    private RoomClass type;
    public static class RoomTypeBuilder {
        private RoomType newRoomType;

        public RoomTypeBuilder() {
            newRoomType = new RoomType();
        }

        public RoomTypeBuilder number(String number) {
            newRoomType.number = number;
            return this;
        }

        public RoomTypeBuilder bedCount(int bedCount) {
            newRoomType.bedCount = bedCount;
            return this;
        }

        public RoomTypeBuilder type(RoomClass type) {
            newRoomType.type = type;
            return this;
        }

        public RoomType build() {
            return newRoomType;
        }
    }
/**
     * Override the toString method to provide a string of the RoomType object.
     *
     * @return A string of the RoomType.
     */
    @Override
    public String toString() {
        return "RoomType{" +   
                "roomnumber='" + number + '\'' +
                ", bedCount=" + bedCount +
                ", Class='" + type + '\'' +
                '}';
    }

/**
     * Override the equals method for comparing RoomType objects by their room number.
     *
     * @param obj The object to compare.
     * @return true if the objects are the same by room number, or false if not.
     */
@Override
public boolean equals(Object obj) {
    if (this == obj) {
        return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
        return false;
    }
    RoomType other = (RoomType) obj;
    return Objects.equals(number, other.number);


}

/**
     * Override the hashCode method to calculate the hash code by room number.
     *
     * @return The hash code of the RoomType object.
     */
@Override
public int hashCode() {
    return Objects.hash(number);
    }

}
