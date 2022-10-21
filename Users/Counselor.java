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
    }

    public Counselor(UUID id, String email, String phone, String password, String firstName, String lastName, Date birthDate, Map<String, String> securityQuestions,
                     ArrayList<String> meds, ArrayList<String> allergies, Map<Relationship, EmergencyContact> emergencyContacts, ArrayList<String> dietaryRestrictions, String tShirt, String bio, ArrayList<String> notes) {
        super(email, password, firstName, lastName, phone, birthDate, securityQuestions);
        this.id = id;
        this.type = UserType.COUNSELOR;
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
    public void addNote (String note) {
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

    // For these do we want to return the list or print out the list?
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
    public String getBio() {
        return bio;
    }
    public ArrayList<String> getNotes() {
        return notes;
    }
 }