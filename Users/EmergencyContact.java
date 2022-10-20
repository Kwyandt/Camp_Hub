package Users;
import java.util.*;

/**
 * @author Katelyn Wyandt
 * Class for creating emergency contact for camper
 */

 public class EmergencyContact {
    private UUID id;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    /**
     * Constructor for emergency contact class to create new contact
     * @param name name of contact
     * @param relationship relationship of contact
     * @param phoneNumber phone number of contact
     */
    public EmergencyContact (String firstName, String lastName, String relationship, String phoneNumber) {

    }
    
    public EmergencyContact(String firstName, String lastName, String relationship, String phoneNumber, UUID id) {
      
    }

    public UUID getUuid () {
      return id;
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