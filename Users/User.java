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
    public void changePassword() {

    }

    public abstract UserType getUserType();

    public UUID getUuid () {
      return id;
    }

    public String getEmail () {
      return email;
    }

    public String getPhone () {
      return phone;
    }

    public String getPassword () {
      return password;
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
 }