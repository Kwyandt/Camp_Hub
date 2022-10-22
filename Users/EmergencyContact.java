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

    /**
     * Constructor for emergency contact class to create new contact
     * @param firstName first name of contact
     * @param lastName last name of contact
     * @param phoneNumber phone number of contact
     */
    public EmergencyContact (String firstName, String lastName, String phoneNumber) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.phoneNumber = phoneNumber;
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
 }