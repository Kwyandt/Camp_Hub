package Users;
import java.util.*;

/**
 * @author Katelyn Wyandt
 * Parent class for creating parent users with ability to create campers
 */

 public class Parent extends User{
    private ArrayList<Camper> children;
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
        children = new ArrayList<Camper>();
    }

    public Parent(UUID id, String email, String pass, String first, String last, String phone, Date birthDate, Map<String, String> question, ArrayList<Camper> children, boolean isReturning) {
        super(email, pass, first, last, phone, birthDate, question);
        this.id = id;
        this.type = UserType.PARENT;
        this.children = children;
        this.isReturning = isReturning;
    }

    /**
     * Method for parent to create camper
     * @param camper the camper being initialized by parent
     * @return returns the updated arraylist of campers
     */
    public ArrayList<Camper> createCamper (Camper camper) {
        this.children.add(camper);
        return this.children;
    }

    /**
     * method to calculate the discount for parent
     * @return value of discount for parent, not lower than 20% off
     */
    public double getDiscount() {
        double discount = 1.0;
        if(children.size() == 0)
            return discount;
        if(isReturning)
            discount -= 0.1;
        discount-=(children.size()-1)*0.05;
        return Math.max(0.8, discount);
        
    }

    public UserType getUserType() {
        return UserType.PARENT;
    }
    
    public ArrayList<Camper> getChildren () {
        return children;
    }

    public boolean getIsReturning () {
        return this.isReturning;
    }

    public boolean equals(Parent aParent) {
        return aParent != null &&
        this.children.equals(aParent.getChildren()) &&
        super.equals(aParent);
    }
    public String toString() {
        return this.id.toString() + "\n" + this.type + "\n" + this.email + "\n" + this.phone + "\n" + this.password + "\n" + this.firstName + "\n" + this.lastName + "\n" + this.birthDate.toString() + "\n" + this.securityQuestions.toString() + "\n" + this.children.toString() + "\n" + this.isReturning;
    }
 }