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
      allergies = new ArrayList<String>();
      meds = new ArrayList<String>();
      dietaryRestrictions = new ArrayList<String>();
      emergencyContacts = new TreeMap<Relationship,EmergencyContact>();
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
     * Method to add medicine to arraylist of meds
     * @param med the medicine being added
     */
    public void addMed(String med) {
      this.meds.add(med);
    }

    /**
     * Method to remove medicine from list of meds
     * @param index index of medicine to be removed
     * @return false if index is out of range of meds, true otherwise
     */
    public boolean removeMed(int index) {
      if(index < 0 || index >= meds.size()) {
        return false;
      }
      this.meds.remove(index);
      return true;
    }

    /**
     * Method to add allergy to list of allergies
     * @param allergy allergy of camper
     */
    public void addAllergy(String allergy) {
      this.allergies.add(allergy);
    }

    /**
     * Method to remove allergy from list of allergies
     * @param index index of allergy to be removed
     * @return false if index is out of range of allergies, true otherwise
     */
    public boolean removeAllergy(int index) {
      if(index < 0 || index >= allergies.size()) {
        return false;
      }
      this.allergies.remove(index);
      return true;
    }

    /**
     * Method to remove dietary restriction from list of dietary restrictions
     * @param index index of dietary restriction to be removed
     * @return false if index is out of range of dietary restriction, true otherwise
     */
    public boolean removeDietaryRestsriction(int index) {
      if(index < 0 || index >= dietaryRestrictions.size()) {
        return false;
      }
      this.dietaryRestrictions.remove(index);
      return true;
    }

    /**
     * Method to add dietary restriction fo to list
     * @param dietaryRestriction restriction of camper
     */
    public void addDietaryResriction (String dietaryRestriction) {
      this.dietaryRestrictions.add(dietaryRestriction);
    }

    //add for emergency contacts
    public void addContact (Relationship type, EmergencyContact name) {
      this.emergencyContacts.put(type, name);
    }

    //remove emergency contacts
    public void removeContact (Relationship type) {
      this.emergencyContacts.remove(type);
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
      if (meds != null)
        return meds;
      return null;
    }

    public ArrayList<String> getAllergy() {
      if(meds != null)
        return allergies;
      return null;
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

    public int getAge() {
      //get current date
      Date today = new Date();
      Calendar current = Calendar.getInstance();
      current.setTime(today);
      Calendar birth = Calendar.getInstance();
      birth.setTime(birthDate);
      //check the year, month, then day 
      int age = current.get(Calendar.YEAR) - birth.get(Calendar.YEAR) -1;
      if(birth.get(Calendar.MONTH) <= current.get(Calendar.MONTH) && birth.get(Calendar.DAY_OF_MONTH) <= current.get(Calendar.DAY_OF_MONTH))
        age++;
      return age;
      //for formatting/printing
      //DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");  
      //String strDate = dateFormat.format(date);
    }

    public boolean equals (Camper aCamper) {
      return aCamper != null&&
        this.firstName == aCamper.getFirst()&&
        this.lastName == aCamper.getLast () &&
        this.birthDate == aCamper.getBirth() &&
        this.emergencyContacts == aCamper.getEmergencyContact()&&
        this.tShirt == aCamper.getTShirt();
    }

    public String toString() {
      return this.id + "\n" + this.firstName + "\n" + this.lastName + "\n" + this.birthDate.toString() + "\n" + this.meds.toString() + "\n" + this.allergies.toString() + "\n" + this.emergencyContacts.toString() + "\n" + this.dietaryRestrictions.toString() + "\n" + this.tShirt;
    }

 }