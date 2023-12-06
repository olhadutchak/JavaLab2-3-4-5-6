package lab4;
import javax.validation.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

public class Guest {
    @NotBlank(message = "name cannot be empty")
    @Size(min = 2, max = 30, message = "The name must be from 2 to 30 characters")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty")
    @Size(min = 2, max = 30, message = "he name must be from 2 to 30 characters")
    private String lastName;

    @Size(max = 30, message = "Middle Name must be from 2 to 30 characters")
    private String middleName;

    public static class GuestBuilder {
        private Guest newGuest;

        public GuestBuilder() {
            newGuest = new Guest();
        }

        public GuestBuilder firstName(String firstName) {
            newGuest.firstName = firstName;
            return this;
        }

        public GuestBuilder lastName(String lastName) {
            newGuest.lastName = lastName;
            return this;
        }

        public GuestBuilder middleName(String middleName) {
            newGuest.middleName = middleName;
            return this;
        }

        public Guest build() {
            validateGuest(newGuest);
            return newGuest;
        }

        private void validateGuest(Guest guest) {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<Guest>> violations = validator.validate(guest);

            if (!violations.isEmpty()) {
                StringBuilder errorMessage = new StringBuilder();
                for (ConstraintViolation<Guest> violation : violations) {
                    errorMessage.append(violation.getPropertyPath())
                            .append(": ")
                            .append(violation.getMessage())
                            .append("; ");
                }
                throw new IllegalArgumentException(errorMessage.toString());
            }
        }
    }

public String getFirstName() {
    return firstName;
}

public void setFirstName(String firstName) {
    this.firstName = firstName;
}

public String getLastName() {
    return lastName;
}

public void setLastName(String lastName) {
    this.lastName = lastName;
}

public String getMiddleName() {
    return middleName;
}

public void setMiddleName(String middleName) {
    this.middleName = middleName;
}
/**
     * * The toString method for the Guest class.
     *
     * @return A string representing the Guest object.
     */
@Override
    public String toString() {
        return "Guest{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                '}';
    }

/**
     * Override equals method for comparing Guest objects by Id.
     *
     * @param obj The object to compare.
     * @return true if the objects are the same by Id, or false if not.
     */

@Override
public boolean equals(Object obj) {
    if (this == obj) {
        return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
        return false;
    }
    Guest other = (Guest) obj;

    return Objects.equals(other.firstName, firstName)&& Objects.equals(other.middleName, middleName)&& Objects.equals(other.lastName, lastName);
}

/**
     * Override hashCode method to calculate hash code by Id.
     *
     * @return The hash code of the Guest object.
     */
@Override
public int hashCode() {
    return Objects.hash(firstName, middleName, lastName);
}
}
