import java.util.Date;
import java.util.ArrayList;
import java.util.UUID;

import Users.*;

/***
 * @author Jackson
 */

public class Session {

    private UUID id;
    private double price;
    private String theme;
    private ArrayList<Cabin> cabins;
    private Date priorityDeadline;
    private Date regularDeadline;
    private Date startDate;
    private Date endDate;

    /***
     * Constructor to create a new session
     * @param theme theme of session
     * @param priorityDeadline priority registration deadline
     * @param regularDeadline regular registration deadline
     * @param startDate start date of session
     * @param endDate end date of session
     */
    public Session(String theme, Date priorityDeadline, Date regularDeadline, Date startDate, Date endDate) {
        this.theme = theme;
        this.priorityDeadline = priorityDeadline;
        this.regularDeadline = regularDeadline;
        this.startDate = startDate;
        this.endDate = endDate;    
    }


    /***
     * Constructor to load a pre-existing session
     * @param id id of session
     * @param price price of session
     * @param theme theme of session
     * @param cabins cabins specific to session
     * @param priorityDeadline priority registration deadline
     * @param regularDeadline regular registration deadline
     * @param startDate start date of session
     * @param endDate end date of session
     */
    public Session(UUID id, double price, String theme, ArrayList<Cabin> cabins, Date priorityDeadline, Date regularDeadline, Date startDate, Date endDate) {
        this.id = id;
        this.price = price;
        this.theme = theme;
        this.cabins = cabins;
        this.priorityDeadline = priorityDeadline;
        this.regularDeadline = regularDeadline;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /***
     * Displays all counselors and campers registered for the session
     */
    public void viewAllParticipants(){

    }

    /***
     * Adds a counselor to a cabin if there is space
     * @param counselor counselor to be added
     */
    public void addCounselor(Counselor counselor) {

    }

    /***
     * Adds a camper to a cabin if there is space
     * @param camper camper to be added
     */
    public void addCamper(Camper camper) {

    }

    /***
     * Accessor method for id
     * @return id of the session
     */
    public UUID getId() {
        return id;
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

    /***
     * Accessor method for cabins
     * @return cabins of the session
     */
    public ArrayList<Cabin> getCabins() {
        return cabins;
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

    }

    /***
     * Mutator for theme
     * @param theme new theme of session
     */
    public void setTheme(String theme) {
        
    }

    /***
     * Mutator for priorityDeadline
     * @param priorityDeadline new priorityDeadline of session
     */
    public void setPriorityDeadline(Date priorityDeadline) {
        
    }

    /***
     * Mutator for regularDeadline
     * @param regularDeadline new regularDeadline of session
     */
    public void setRegularDeadline(Date regularDeadline) {
        
    }

    /***
     * Mutator for startDate
     * @param startDate new startDate of session
     */
    public void setStartDate(Date startDate) {

    }

    /***
     * Mutator for endDate
     * @param endDate new endDate of session
     */
    public void setEndDate(Date endDate) {
        
    }

    /***
     * Provides String representation of the session
     * @return String representation of session
     */
    public String toString() {
        return "placeholder";
    }
}
