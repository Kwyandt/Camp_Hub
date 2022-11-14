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

    /**
     * Creates a new director 
     * @param email
     * @param pass
     * @param first
     * @param last
     * @param phone
     * @param birthDate
     * @param question
     */
    public Director(String email, String pass, String first, String last, String phone, Date birthDate, Map<String, String> question) {
        super(email, pass, first, last, phone, birthDate, question);
        this.notes = new ArrayList<String>();
    }

    /**
     * Calls a previously existing director
     * @param id
     * @param email
     * @param pass
     * @param first
     * @param last
     * @param phone
     * @param birthDate
     * @param question
     * @param bio
     * @param notes
     */
    public Director(UUID id, String email, String pass, String first, String last, String phone, Date birthDate, Map<String, String> question, String bio, ArrayList<String> notes) {
        super(email, pass, first, last, phone, birthDate, question);
        this.id = id;
        this.bio = bio;
        this.notes = notes;
    }
    
    
    /**
     * Method to add biography to camp website
     * @param bio string for biography of camp/director for parents to see
     */
    public void addBio(String bio) {
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

    public UserType getUserType() {
        return UserType.DIRECTOR;
    }
    public String getBio() {
        return this.bio;
    }

    public ArrayList<String> getNotes() {
        return this.notes;
    }

    /**
     * equals method for director
     * @param aDirector to be compared to this.director
     * @return boolean true or falseS
     */
    public boolean equals (Director aDirector) {
        return aDirector != null &&
        this.bio.equals(aDirector.getBio()) &&
        this.notes.equals(aDirector.getNotes()) &&
        super.equals(aDirector);
    }

    /**
     * Provites string to represent director
     * @return String to represent director
     */
    public String toString() {
        return "Director:" + super.toString() + "\n\t" + bio.toString() + "\n\t" + notes.toString();
    }
}