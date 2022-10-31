package Users;
import java.util.*;

/**
 * @author Katelyn Wyandt
 * Description: The user class contains the variables for all classes that inherit user, includng id,
 * email, phone, password, name, birthday, and security question
 */

 public abstract class User {
    protected UUID id;
    protected UserType type;
    protected String email;
    protected String phone;
    protected String password;
    protected String firstName;
    protected String lastName;
    protected Date birthDate;
    protected Map<String, String> securityQuestions;
  
    /**
     * Constructor for the User class to create a new user
     * @param email email address of the user
     * @param password password for login to the web console, specific for each user
     * @param firstName first name of user
     * @param lastName last name of user
     * @param phone phone number of user
     * @param birthDate birthday of user
     * @param securityQuestions security question for user login
     */
    public User(String email, String password, String firstName, String lastName, String phone, Date birthDate, Map<String, String> securityQuestions){
      this.email = email;
      this.password = password;
      this.firstName = firstName;
      this.lastName = lastName;
      this.phone = phone;
      this.birthDate = birthDate;
      this.securityQuestions = securityQuestions;
    }

    /**
     * Method for user to change login password
     */
    public boolean changePassword(String oldPass, String newPass) {
      //prompt for old password
      if(oldPass.equalsIgnoreCase(password)){
        this.password = newPass;
        return true;
      }
      return false;
    }

    public abstract UserType getUserType();

    public UUID getUuid() {
      return id;
    }

    public String getEmail() {
      return email;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    public String getPhone() {
      return phone;
    }

    public void setPhone(String phone) {
      this.phone = phone;
    }

    public String getPassword() {
      return password;
    }

    public void setPassword(String password) {
      this.password = password;
    }

    public String getFirstName() {
      return firstName;
    }

    public String getLastName() {
      return lastName;
    }

    public Date getBirthDate() {
      return birthDate;
    }

    public Map<String, String> getSecurityQuestions() {
      return securityQuestions;
    }

    /**
     * equals method for user class
     * @param aUser object to be compared to
     * @return boolean true or false
     */
    public boolean equals (User aUser) {
      return aUser != null &&
      this.type.equals(aUser.getUserType()) &&
      this.email.equals(aUser.getEmail()) &&
      this.phone.equals(aUser.getPhone()) &&
      this.password.equals(aUser.getPassword()) &&
      this.firstName.equals(aUser.getFirstName()) &&
      this.lastName.equals(aUser.getLastName()) &&
      this.birthDate.equals(aUser.getBirthDate());
    }

    /**
     * to string method for user class
     * @return string of user information
     */
    public String toString() {
      return this.firstName + " " + this.lastName + "\n\t" + this.id + "\n\t" + this.email + "\n\t" + this.phone + "\n\t" + this.birthDate + "\n\t" + this.securityQuestions;
    }
 }