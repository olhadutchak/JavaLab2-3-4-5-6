package lab4;

import java.util.Objects;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.*;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class RoomType {
    @NotBlank(message = "Room number cannot be blank")
    @Pattern(regexp = "[0-9]{1,50}", message = "Room number must contain only numbers > 0")
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
            validate();
            return newRoomType;
        }

        private void validate() {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<RoomType>> violations = validator.validate(newRoomType);

            if (!violations.isEmpty()) {
                StringBuilder errorMessage = new StringBuilder();
                for (ConstraintViolation<RoomType> violation : violations) {
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
        return "RoomType{" +
                "roomnumber='" + number + '\'' +
                ", bedCount=" + bedCount +
                ", Class='" + type + '\'' +
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
        RoomType other = (RoomType) obj;
        return Objects.equals(number, other.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    public int getBedCount() {
        return bedCount;
    }

    public String getNumber() {
        return number;
    }

    public RoomClass getType() {
        return type;
    }
}
