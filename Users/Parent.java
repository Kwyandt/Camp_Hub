package Users;
import java.util.*;

/**
 * @author Katelyn Wyandt
 * Parent class for creating parent users with ability to create campers
 */

 public class Parent extends User{
    private ArrayList<Camper> children;
    private double discount;
    private boolean isReturning = true;
    
    /**
     * Constructor initalized the parent user and each function
     * @param email email of parent
     * @param pass password of parent login
     * @param first first name of parent
     * @param last last name of parent
     * @param phone phone number of parent
     * @param birthDate birthday of parent
     * @param question secruity question for parent login
     */
    public Parent (String email, String pass, String first, String last, String phone, Date birthDate, Map<String, String> question) {
        super(email, pass, first, last, phone, birthDate, question);
    }

    /**
     * Method for parent to create camper
     * @param camper the camper being initialized by parent
     * @return returns the updated arraylist of campers
     */
    public ArrayList<Camper> createCamper (Camper camper) {
        return children;
    }

    public UserType getUserType() {
        return UserType.PARENT;
    }
    
 }