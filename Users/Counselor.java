package Users;
import java.util.*;

/**
 * @author Katelyn Wyandt
 * Counselor class for user type of counselor
 */

 public class Counselor extends User {
    private ArrayList<String> meds;
    private ArrayList<String> allergies;
    private HashMap<Relationship, EmergencyContact> emergencyContacts;
    private ArrayList<String> dietaryRestrictions;
    private String tShirt;
    private String bio;
    private ArrayList<String> notes;

    /**
     * Constructor for creating instance of counselor
     * @param email email of counselor
     * @param pass password for counselor login
     * @param first first name of counselor
     * @param last last name of counselor
     * @param phone phone number of counselor
     * @param birthDate counselor birthday
     * @param question security question for login
     */
    public Counselor (String email, String pass, String first, String last, String phone, Date birthDate, Map<String, String> question) {
        super(email, pass, first, last, phone, birthDate, question);
    }

     /**
     * Method to add biography to camp website
     * @param bio string for biography of counselor
     */
    public void addBio(String bio) {
        //does this need to return a string or do we need to have an array to modify here?
    }

    /**
     * Method to add note to directors and counselors for personal view
     * @param note
     */
    public ArrayList <String> addNote (String note) {
        //notes.add(note);
        //should this be void or return the array?
        return notes;
    }

    /**
     * Method to remove note from notes array at specified index
     * @param index index of array where note is store
     */
    public ArrayList <String> removeNote(int index) {
        //should this be a void class or return the modified array?
        return notes;
    }

    /**
     * Method to view notes
     * @return concantenated string of notes, include numbered list?
     */
    public String viewNotes() {
        //bio is only placeholder right now
        return bio;
    }

    /**
     * Method to return type of user
     * @return counselor, type of user in this class
     */
    public UserType getUserType() {
        return UserType.COUNSELOR;
    }

    public ArrayList<String> getMeds() {
        return meds;
    }
    public ArrayList<String> getAllergies() {
        return allergies;
    }
    public HashMap<Relationship, EmergencyContact> getEmergenctContacts () {
        return  emergencyContacts;
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