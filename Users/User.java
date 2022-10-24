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
    public Scanner scanner;

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
      //prompt for old password
      System.out.println("Please enter your old password: ");
      String input = scanner.nextLine();
      if(input.equalsIgnoreCase(password))
        setNewPass();
      else
        System.out.println("The password you have entered is incorrect. ");

    //should take back to menu/provide choice to change again
    //should this ask security question?
    }

    //ask for new password
    public void setNewPass () {
      System.out.println("Please enter your new password: ");
      String newPassword = scanner.nextLine();
      System.out.println("Please re-enter the new password: ");
      String newPassword2 = scanner.nextLine();
      //set new password
      boolean match = false;
      while(!match){
        if(newPassword.equals(newPassword2)){
          password = newPassword;
          match = true;
        }
        else {
          System.out.println("This does not match the new password. Please re-enter the new password: ");
          newPassword2 = scanner.nextLine();
        }
      }
    }

    public abstract UserType getUserType();

    public UUID getUuid() {
      return id;
    }

    public UserType getType() {
      return this.type;
    }

    public String getEmail() {
      return email;
    }

    public String getPhone() {
      return phone;
    }

    public String getPassword() {
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

    public String toString() {
      return this.firstName + " " + this.lastName + "\n\t" + this.id + "\n\t" + this.email + "\n\t" + this.phone + "\n\t" + this.birthDate + "\n\t" + this.securityQuestions;
    }
 }