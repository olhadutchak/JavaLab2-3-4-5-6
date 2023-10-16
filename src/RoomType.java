import java.util.Objects;

public class RoomType {
    private String Number;
    private int bedCount;
    private RoomClass Class;
    public static class RoomTypeBuilder {
        private RoomType newRoomType;

        public RoomTypeBuilder() {
            newRoomType = new RoomType();
        }

        public RoomTypeBuilder Number(String Number) {
            newRoomType.Number = Number;
            return this;
        }

        public RoomTypeBuilder bedCount(int bedCount) {
            newRoomType.bedCount = bedCount;
            return this;
        }

        public RoomTypeBuilder Class(RoomClass Class) {
            newRoomType.Class = Class;
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
                "roomNumber='" + Number + '\'' +
                ", bedCount=" + bedCount +
                ", Class='" + Class + '\'' +
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
    return Objects.equals(Number, other.Number);


}

/**
     * Override the hashCode method to calculate the hash code by room number.
     *
     * @return The hash code of the RoomType object.
     */
@Override
public int hashCode() {
    return Objects.hash(Number);
    }
   
}
