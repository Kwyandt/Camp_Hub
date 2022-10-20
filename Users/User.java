package Users;
import java.util.*;

/**
 * @author Katelyn Wyandt
 * Description: The user class contains the variables for all classes that inherit user, includng id,
 * email, phone, password, name, birthday, and security question
 */

 public abstract class User {
    protected UUID id;
    protected String email;
    protected String phone;
    protected String password;
    protected String firstName;
    protected String lastName;
    protected Date birthDate;
    protected Map <String, String> securityQuestions;

    /**
     * Constructor for the User class to create a new user
     * @param email email address of the user
     * @param pass password for login to the web console, specific for each user
     * @param first first name of user
     * @param last last name of user
     * @param phone phone number of user
     * @param birthDate birthday of user
     * @param question security question for user login
     */
    public User (String email, String pass, String first, String last, String phone, Date birthDate, Map<String, String> question){

    }

    /**
     * Method for user to change login password
     */
    public void changePassword() {

    }

    public abstract UserType getUserType();

 }