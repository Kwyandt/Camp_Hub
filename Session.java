import java.util.Date;
import java.util.NoSuchElementException;
import java.util.ArrayList;

import Users.*;

/***
 * @author Jackson
 */

public class Session {

    private double price;
    private String theme;
    private String description;
    private ArrayList<Cabin> cabins;
    private Date priorityDeadline;
    private Date regularDeadline;
    private Date startDate;
    private Date endDate;

    /***
     * Constructor to create a new session
     * @param theme theme of session
     * @param description description of session
     * @param priorityDeadline priority registration deadline
     * @param regularDeadline regular registration deadline
     * @param startDate start date of session
     * @param endDate end date of session
     */
    public Session(String theme, String description, Date priorityDeadline, Date regularDeadline, Date startDate, Date endDate) {
        this.setPrice(1000);
        this.theme = theme;
        this.description = description;
        this.priorityDeadline = priorityDeadline;
        this.regularDeadline = regularDeadline;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cabins = new ArrayList<Cabin>();
    }


    /***
     * Constructor to load a pre-existing session
     * @param price price of session
     * @param theme theme of session
     * @param description description of session
     * @param cabins cabins specific to session
     * @param priorityDeadline priority registration deadline
     * @param regularDeadline regular registration deadline
     * @param startDate start date of session
     * @param endDate end date of session
     */
    public Session(double price, String theme, String description, ArrayList<Cabin> cabins, Date priorityDeadline, Date regularDeadline, Date startDate, Date endDate) {
        this.price = price;
        this.theme = theme;
        this.description = description;
        this.cabins = cabins;
        this.priorityDeadline = priorityDeadline;
        this.regularDeadline = regularDeadline;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /***
     * Displays all counselors and campers registered for the session
     */
    public String viewAllParticipants(){
        //iteratre through all cabins for the session
        String toReturn = "";
        for(Cabin cabin: cabins) {
            toReturn = toReturn + cabin.toString();
        }
        return toReturn;
    }

    /***
     * Adds a counselor to a cabin if there is space
     * @param counselor counselor to be added
     * @return true if counselor was added, false otherwise
     */
    public boolean addCounselor(Counselor counselor) {
        //should this automatically add counselor to a session?
        //does this need to check if there is already a counselor assigned?
        for(Cabin cab: cabins) {
            if(cab.getCounselor() == null) {
                cab.setCounselor(counselor);
                return true;
            }
        }
        // All cabins already have counselors assigned
        return false;
    }

    /***
     * Adds a camper to a cabin if there is space
     * @param camper camper to be added
     * @return True iff camper was placed in a cabin
     */
    public boolean addCamper(Camper camper) {
        int age = camper.getAge();
        for (Cabin cabin : cabins) {
            if (age >= cabin.getMinAge() && age <= cabin.getMaxAge()) {
                // Camper is eligible, so try adding them
                if (cabin.addCamper(camper))
                    // It worked!
                    return true;
                else
                    // Cabin was full, so keep trying
                    continue;
            } else
                // Not a suitable age, so keep trying
                continue;
        }
        // We didn't find a suitable cabin, so return false
        return false;
    }

    /***
     * Accessor method for price
     * @return price of the session
     */
    public double getPrice() {
        return price;
    }

    /***
     * Accessor method for theme
     * @return theme of the session
     */
    public String getTheme() {
        return theme;
    }

    /**
     * Accessor method for description
     * @return description of the session
     */
    public String getDescription() {
        return description;
    }

    /***
     * Accessor method for cabins
     * @return cabins of the session
     */
    public ArrayList<Cabin> getCabins() {
        return cabins;
    }

    /**
     * Accessor for cabin of a specific number
     * @param number Number of desired Cabin
     * @return Cabin with desired number (if it exists)
     * @throws NoSuchElementException If cabin is not found
     */
    public Cabin getCabin(int number) throws NoSuchElementException {
        for (Cabin cabin : cabins)
            if (cabin.getCabinNumber() == number)
                return cabin;
        // We didn't find the cabin
        throw new NoSuchElementException();
    }

    /***
     * Accessor method for priorityDeadline
     * @return priorityDeadline of the session
     */
    public Date getPriorityDeadline() {
        return priorityDeadline;
    }

    /***
     * Accessor method for regularDeadline
     * @return regularDeadline of the session
     */
    public Date getRegularDeadline() { 
        return regularDeadline;
    }

    /***
     * Accessor method for startDate
     * @return startDate of the session
     */
    public Date getStartDate() {
        return startDate;
    }

    /***
     * Accessor method for endDate
     * @return endDate of the session
     */
    public Date getEndDate() {
        return endDate;
    }

    /***
     * Mutator for price
     * @param price new price of session
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /***
     * Mutator for theme
     * @param theme new theme of session
     */
    public void setTheme(String theme) {
        this.theme = theme;
    }

    /**
     * Mutator for description
     * @param description new description of session
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /***
     * Mutator for priorityDeadline
     * @param priorityDeadline new priorityDeadline of session
     */
    public void setPriorityDeadline(Date priorityDeadline) {
        this.priorityDeadline = priorityDeadline;
    }

    /***
     * Mutator for regularDeadline
     * @param regularDeadline new regularDeadline of session
     */
    public void setRegularDeadline(Date regularDeadline) {
        this.regularDeadline = regularDeadline;
    }

    /***
     * Mutator for startDate
     * @param startDate new startDate of session
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /***
     * Mutator for endDate
     * @param endDate new endDate of session
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * equals method for session class
     * @param aSession to be compared to session
     * @return boolean true or false
     */
    public boolean equals(Session aSession) {
        return aSession != null &&
        this.price == aSession.getPrice() &&
        this.theme.equals(aSession.getTheme()) &&
        this.description.equals(aSession.getDescription()) &&
        this.cabins.equals(aSession.getCabins()) &&
        this.priorityDeadline.equals(aSession.getPriorityDeadline()) &&
        this.regularDeadline.equals(aSession.getRegularDeadline()) &&
        this.startDate.equals(aSession.getStartDate()) &&
        this.endDate.equals(aSession.getEndDate());
    }
    /***
     * Provides String representation of the session
     * @return String representation of session
     */
    public String toString() {
        return theme + " " + price + " " + startDate + " - " + endDate;
    }
}
