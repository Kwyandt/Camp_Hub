import java.util.ArrayList;

/***
 * @author Jackson
 */
public class Camp {
    private String name;
    private SessionList sessions;
    private ArrayList<String> faqs;
    private ArrayList<String> securityQuestions;
    private ArrayList<Activity> activities;
    private String officePhone;
    private ArrayList<String> packingList;

    /***
     * Constructor for creating a new camp object
     * @param name name of camp
     * @param officePhone phone number for the camp
     */
    public Camp(String name, String officePhone) {

    }

    /***
     * Constructor for loading a pre-existing camp
     * @param name name of camp
     * @param sessions sessions of the camp
     * @param faqs faqs about the camp
     * @param securityQuestions possible security questions for users
     * @param officePhone phone number for the camp
     * @param packingList items campers should pack
     */
    public Camp(String name, SessionList sessions, ArrayList<String> faqs, ArrayList<String> securityQuestions, ArrayList<Activity> activities, String officePhone, ArrayList<String> packingList) {

    }

    /***
     * Adds activity to activities ArrayList 
     * @param activity activity to be added
     */
    public void addActivity(Activity activity) {

    }

    /***
     * Removes activity at specified index
     * @param index index of activity to be removed
     */
    public void removeActivity(int index) {

    }

    /***
     * Adds session to SessionList
     * @param session session to be added
     */
    public void addSession(Session session) {

    }

    /***
     * Removes session at the index
     * @param index index of session to be removed
     */
    public void removeSession(int index) {

    }

    /**
     * Adds FAQ to faqs
     * @param faq faq to be added
     */
    public void addFAQ(String faq) {

    }

    /***
     * Removes FAQ at index
     * @param index index of FAQ to be removed
     */
    public void removeFAQ(int index) {

    }

    /***
     * Adds packingItem to packingList
     * @param packingItem packingItem to be added
     */
    public void addPackingItem(String packingItem) {

    }

    /***
     * Removes packingItem at index
     * @param index index of packingItem to be removed
     */
    public void removePackingItem(int index) {

    }

    /***
     * Accessor method for name
     * @return name of the camp
     */
    public String getName() {
        return name;
    }

    /***
     * Accessor method for faqs
     * @return faqs about the camp
     */
    public ArrayList<String> getFAQs() {
        return faqs;
    }

    /***
     * Accessor method for securityQuestions
     * @return  securityQuestions for users of the camp
     */
    public ArrayList<String> getSecurityQuestions() {
        return securityQuestions;
    }

    /***
     * Accessor method for activities
     * @return activities of the camp
     */
    public ArrayList<Activity> getActivities() {
        return activities;
    }

    /***
     * Accessor method for officePhone
     * @return office phone number of the camp
     */
    public String getOfficePhone() {
        return officePhone;
    }

    /***
     * Accessor method for packingList
     * @return packingList for campers
     */
    public ArrayList<String> getPackingList() {
        return packingList;
    }
}
