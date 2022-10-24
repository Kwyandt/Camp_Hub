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
    private Map<Relationship, EmergencyContact> emergencyContacts;
    private ArrayList<String> dietaryRestrictions;
    private String tShirt;

    /**
     * Constructor for camper class, creates new camper 
     * @param firstName name of camper
     * @param lastName last name of camper
     * @param birthDate birthday of camper
     * @param emergencyContacts emergeny contacts of camper
     * @param tShirt tshirt size of camper
     */
    public Camper (String firstName, String lastName, Date birthDate, Map<Relationship, EmergencyContact> emergencyContacts, String tShirt) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.birthDate = birthDate;
      this.emergencyContacts = emergencyContacts;
      this.tShirt = tShirt;
    }

    /**
     * Constructor for camper class, loads in current camper
     * @param firstName first name of camper
     * @param lastName last name of camper
     * @param birthDate birth date of camper
     * @param meds list of meds camper takes
     * @param allergies list of allergies for camper
     * @param emergencyContacts list of contacts for camper
     * @param dietaryRestrictions list of camper's dietary restrictions
     * @param tShirt tShirt size
     */
    public Camper(UUID id, String firstName, String lastName, Date birthDate, ArrayList<String> meds, ArrayList<String> allergies, Map<Relationship, EmergencyContact> emergencyContacts, ArrayList<String> dietaryRestrictions, String tShirt) {
      this.id = id;
      this.firstName = firstName;
      this.lastName = lastName;
      this.birthDate = birthDate;
      this.meds = meds;
      this.allergies = allergies;
      this.emergencyContacts = emergencyContacts;
      this.dietaryRestrictions = dietaryRestrictions;
      this.tShirt = tShirt;
    }

    /**
     * Method to add medicine to arraulist of meds
     * @param med the medicine being added
     */
    public void addMed(String med) {
      this.meds.add(med);
    }

    /**
     * Method to add allergy to list of allergies
     * @param allergy allergy of camper
     */
    public void addAllergy(String allergy) {
      this.allergies.add(allergy);
    }

    /**
     * Method to add dietary restriction fo to list
     * @param dietaryRestriction restriction of camper
     */
    public void addDietaryResriction (String dietaryRestriction) {
      this.dietaryRestrictions.add(dietaryRestriction);
    }

    //getters for all variables
    public UUID getUuid () {
      return id;
    }

    public String getFirst () {
      return firstName;
    }

    public String getLast() {
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

    public Map<Relationship, EmergencyContact> getEmergencyContact() {
      return emergencyContacts;
    }

    public ArrayList<String> getDietaryRestrictions() {
      return dietaryRestrictions;
    }

    public String getTShirt() {
      return tShirt;
    }

    public String toString() {
      return this.id + "\n" + this.firstName + "\n" + this.lastName + "\n" + this.birthDate.toString() + "\n" + this.meds.toString() + "\n" + this.allergies.toString() + "\n" + this.emergencyContacts.toString() + "\n" + this.dietaryRestrictions.toString() + "\n" + this.tShirt;
    }

 }