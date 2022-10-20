package Users;
import java.util.*;

/**
 * @author Katelyn Wyandt
 * The director class is for users with the specific type, Director. This gives special access
 * for directors of camps to edit and modify site
 */

public class Director extends User{
    private String bio;
    private ArrayList<String> notes;

    public Director(String email, String pass, String first, String last, String phone, Date birthDate, Map<String, String> question){
        super(email, pass, first, last, phone, birthDate, question);
    }
    
    /**
     * Method to add biography to camp website
     * @param bio string for biography of camp/director for parents to see
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

    public UserType getUserType() {
        return UserType.DIRECTOR;
    }
    public String getBio() {
        return bio;
    }
    public ArrayList<String> getNotes() {
        return notes;
    }
}