package lab2java;
import java.util.Objects;
public class Guest  {

    private String firstName;
    private String lastName;
    private String middleName;
    public static class GuestBuilder {
        private Guest newGuest;
    public GuestBuilder(){
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
        return newGuest;
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

