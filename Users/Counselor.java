package Users;
import java.util.*;

/**
 * @author Katelyn Wyandt
 * Counselor class for user type of counselor
 */

 public class Counselor extends User {
    private ArrayList<String> meds;
    private ArrayList<String> allergies;
    private Map<Relationship, EmergencyContact> emergencyContacts;
    private ArrayList<String> dietaryRestrictions;
    private String tShirt;
    private String bio;
    private ArrayList<String> notes;

    /**
     * Constructor for creating instance of counselor
     * @param email email of counselor
     * @param password password for counselor login
     * @param firstName first name of counselor
     * @param lastName last name of counselor
     * @param phone phone number of counselor
     * @param birthDate counselor birthday
     * @param question security question for login
     */
    public Counselor (String email, String password, String firstName, String lastName, String phone, Date birthDate, Map<String, String> question) {
        super(email, password, firstName, lastName, phone, birthDate, question);
        this.meds = new ArrayList<String>();
        this.emergencyContacts = new TreeMap<Relationship, EmergencyContact>();
        this.allergies = new ArrayList<String>();
        this.dietaryRestrictions = new ArrayList<String>();
        this.notes = new ArrayList<String>();
        this.tShirt = "";
        this.bio = "";
    }

    public Counselor(UUID id, String email, String phone, String password, String firstName, String lastName, Date birthDate, Map<String, String> securityQuestions,
                     ArrayList<String> meds, ArrayList<String> allergies, Map<Relationship, EmergencyContact> emergencyContacts, ArrayList<String> dietaryRestrictions, String tShirt, String bio, ArrayList<String> notes) {
        super(email, password, firstName, lastName, phone, birthDate, securityQuestions);
        this.id = id;
        this.meds = meds;
        this.allergies = allergies;
        this.emergencyContacts = emergencyContacts;
        this.dietaryRestrictions = dietaryRestrictions;
        this.tShirt = tShirt;
        this.bio = bio;
        this.notes = notes;

    }

     /**
     * Method to add biography to camp website
     * @param bio string for biography of counselor
     */
    public void addBio(String bio) {
        //does this need to return a string or do we need to have an array to modify here?
        this.bio = bio;
    }

    /**
     * Method to add note to directors and counselors for personal view
     * @param note
     */
    public void addNote(String note) {
        this.notes.add(note);
    }

    /**
     * Method to remove note from notes array at specified index
     * @param index index of array where note is store
     */
    public void removeNote(int index) {
        this.notes.remove(index);
    }

    /**
     * Method to view notes
     * @return concantenated string of notes, include numbered list?
     */
    public String viewNotes() {
        String toReturn = "";
        for(String note : notes) {
            toReturn = toReturn + "\n" + note.toString();
        }
        return toReturn;
    }

    /**
     * Method to return type of user
     * @return counselor, type of user in this class
     */
    public UserType getUserType() {
        return UserType.COUNSELOR;
    }

    // Accessors for Counselor-specific variables
    public ArrayList<String> getMeds() {
        return meds;
    }
    public ArrayList<String> getAllergies() {
        return allergies;
    }
    public Map<Relationship, EmergencyContact> getEmergenctContacts () {
        return emergencyContacts;
    }
    public ArrayList<String> getDietaryRestrictions() {
        return dietaryRestrictions;
    }
    public String getTShirt () {
        return tShirt;
    }
    public void setTShirt(String tshirt){
        this.tShirt = tshirt;
    }
    public String getBio() {
        return bio;
    }
    public ArrayList<String> getNotes() {
        return notes;
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
      public void addDietaryRestriction(String dietaryRestriction) {
        this.dietaryRestrictions.add(dietaryRestriction);
      }
  
      //add for emergency contacts
      public void addContact(Relationship type, EmergencyContact name) {
        this.emergencyContacts.put(type, name);
      }
  
      //remove emergency contacts
      public boolean removeContact(Relationship type) {
        try {
            this.emergencyContacts.remove(type);
            return true;
        } catch (Exception e) {
            return false;
        }
      }

    /**
     * equals method for counselor
     * @param aCounselor to be compared to this.counselor
     * @return boolean true or false
     */
    public boolean equals(Counselor aCounselor) {
        return aCounselor != null &&
            super.equals(aCounselor) &&
            this.allergies.equals(aCounselor.getAllergies()) &&
            this.bio.equals(aCounselor.getBio()) &&
            this.dietaryRestrictions.equals(aCounselor.getDietaryRestrictions()) &&
            this.email.equals(aCounselor.getEmail()) &&
            this.emergencyContacts.equals(aCounselor.getEmergenctContacts()) &&
            this.tShirt.equals(aCounselor.getTShirt()) &&
            this.notes.equals(aCounselor.getNotes());
    }

    /**
     * Provides string representation for class
     * @retun String to represent counselor
     */
    public String toString() {
        return "Counselor: " + super.toString() + "\n\t" + meds.toString() + "\n\t" + allergies.toString() + "\n\t" + emergencyContacts.toString() + "\n\t" + dietaryRestrictions.toString() + "\n\t" + tShirt + "\n\t" + bio + "\n\t" + notes.toString();
    }

	public int getAge() {
        //get current date
        Date today = new Date();
        Calendar current = Calendar.getInstance();
        current.setTime(today);
        Calendar birth = Calendar.getInstance();
        birth.setTime(this.birthDate);
        //check the year, month, then day 
        int age = current.get(Calendar.YEAR) - birth.get(Calendar.YEAR) -1;
        if(birth.get(Calendar.MONTH) <= current.get(Calendar.MONTH) && birth.get(Calendar.DAY_OF_MONTH) <= current.get(Calendar.DAY_OF_MONTH))
          age++;
        return age;
        //for formatting/printing
        //DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");  
        //String strDate = dateFormat.format(date);
    }

	public Map<Relationship, EmergencyContact> getEmergencyContact() {
		return emergencyContacts;
	}
 }