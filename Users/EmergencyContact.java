package Users;
import java.nio.file.FileStore;
import java.util.*;

/**
 * @author Katelyn Wyandt
 * Class for creating emergency contact for camper
 */

 public class EmergencyContact {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String location;

    /**
     * Constructor for emergency contact class to create new contact
     * @param firstName first name of contact
     * @param lastName last name of contact
     * @param phoneNumber phone number of contact
     * @param location location of contact
     */
    public EmergencyContact (String firstName, String lastName, String phoneNumber, String location) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.phoneNumber = phoneNumber;
      this.location = location;
    }
    
    public String getFirst () {
      return firstName;
    }
    public String getLast() {
      return lastName;
    }
    public String getPhone(){
      return phoneNumber;
    }

    public String getLocation() {
      return location;
    }

    public String toString() {
      return firstName + " " + lastName + " - " + phoneNumber + "-" + location;
    }
 }