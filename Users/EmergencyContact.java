package Users;

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

    /**
     * equals method for emergency contact
     * @param aContact to e compred to this.contact
     * @return boolean true or false
     */
    public boolean equals(EmergencyContact aContact) {
      return aContact != null &&
      this.firstName.equals(aContact.getFirst()) &&
      this.lastName.equals(aContact.getLast()) &&
      this.phoneNumber.equals(aContact.getPhone()) &&
      this.location.equals(aContact.getLocation());
    }


    /**
     * provides string to represent emergency contact
     * @return string to represent emergency contact
     */
    public String toString() {
      return firstName + " " + lastName + " - " + phoneNumber + "-" + location;
    }
 }