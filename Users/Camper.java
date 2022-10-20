package Users;
import java.util.*;

/**
 * @author Katelyn Wyandt
 * This is the class to create campers and collect all data
 */

 public class Camper {
    private UUID id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private ArrayList<String> meds;
    private ArrayList<String> allergies;
    private HashMap <Relationship, EmergencyContact> emergencyContacts;
    private ArrayList<String> dietaryRestrictions;
    private String tShirt;

    /**
     * Constructor for camper class, creates new camper 
     * @param firstName name of camper
     * @param lastName last name of camper
     * @param birthdate birthday of camper
     * @param emergencyContact emergeny contact of camper
     * @param doctorContact doctor of camper
     * @param dentistContact dentist of camper
     * @param tShirt tshirt size of camper
     */
    public Camper (String firstName, String lastName, Date birthdate, String [] emergencyContact, String[] doctorContact, String[] dentistContact, String tShirt) {

    }

    /**
     * Method to add medicine to arraulist of meds
     * @param med the medicine being added
     */
    public void addMed(String med) {

    }

    /**
     * Method to add allergy to list of allergies
     * @param allergy allergy of camper
     */
    public void addAllergy(String allergy) {

    }

    /**
     * Method to add dietary restriction fo to list
     * @param dietaryRestriction restriction of camper
     */
    public void addDietaryResriction (String dietaryRestriction) {

    }

    //setters later
    //getters for all variables
    public UUID getUuid () {
      return id;
    }
    public String getFirst () {
      return firstName;
    }
    public String getLast()  {
      return lastName;
    }
    public Date getBirth() {
      return birthDate;
    }
    public ArrayList<String> getMeds () {
      return meds;
    }
    public ArrayList<String> getAllergy() {
      return allergies;
    }
    public HashMap<Relationship, EmergencyContact> getEmergencyContact() {
      return emergencyContacts;
    }
    public ArrayList<String> getDietaryRestrictions () {
      return dietaryRestrictions;
    }
    public String getTShirt () {
      return tShirt;
    }
 }