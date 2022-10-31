import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Camp object that stores general camp information
 * @author Kat, Jackson, Nathan
 */
public class Camp {

    private static Camp campInstance;
    
    private String name;
    private Map<String, String> faqs;
    private ArrayList<String> securityQuestions;
    private ArrayList<Activity> activities;
    private String officePhone;
    private ArrayList<String> packingList;

    /**
     * 
     * @return
     */
    public static Camp getInstance(){
        if(campInstance==null)
            campInstance = DataReader.getCamp();
        return campInstance;
    }

    /**
     * Constructor for creating a new camp object
     * @param name name of camp
     * @param officePhone phone number for the camp
     */
    public Camp(String name, String officePhone) {
        this.setName(name);
        this.faqs = new HashMap<String, String>();
        this.securityQuestions = new ArrayList<String>();
        this.activities = new ArrayList<Activity>();
        this.setOfficePhone(officePhone);
        this.packingList = new ArrayList<String>();
    }

    /**
     * Constructor for loading a pre-existing camp
     * @param name name of camp
     * @param faqs faqs about the camp
     * @param securityQuestions possible security questions for users
     * @param officePhone phone number for the camp
     * @param packingList items campers should pack
     */
    public Camp(String name, Map<String, String> faqs,
                ArrayList<String> securityQuestions,
                ArrayList<Activity> activities, String officePhone, 
                ArrayList<String> packingList) {
        this.setName(name);
        this.faqs = faqs;
        this.securityQuestions = securityQuestions;
        this.activities = activities;
        this.setOfficePhone(officePhone);
        this.packingList = packingList;
    }

    /**
     * Finds a certain activity with a given UUID and returns it
     * @param id Gets a particular activity by UUID
     * @return Activity with given UUID, or null if not found
     */
    public Activity getActivityByUUID(UUID id) {
        for (Activity activity : activities)
            if (activity.getId().equals(id))
                return activity;
        return null;
    }
    
    /**
     * Adds activity to activities ArrayList 
     * @param activity activity to be added
     */
    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    /** 
     * Removes activity with specified UUID
     * @param id UUID of activity to be removed
     * @return True iff removal was sucesssful
     */
    public boolean removeActivity(UUID id) {
        Activity toRemove = getActivityByUUID(id);
        if (toRemove == null)
            return false;
        activities.remove(toRemove);
        return true;
    }

    /**
     * Removes activity at specified index
     * @param index index of activity to be removed
     * @return True iff removal was successful
     */
    public boolean removeActivity(int index) {
        try {
            activities.remove(index);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Adds FAQ to faqs
     * @param question question to be added
     * @param answer answer to question
     */
    public void addFAQ(String question, String answer) {
        this.faqs.put(question, answer);
    }

    /**
     * Removes FAQ at index
     * @param question FAQ to be removed
     */
    public void removeFAQ(String question) {
        this.faqs.remove(question);
    }

    /**
     * Adds packingItem to packingList
     * @param packingItem packingItem to be added
     */
    public void addPackingItem(String packingItem) {
        this.packingList.add(packingItem);
    }

    /**
     * Removes packingItem at index
     * @param index index of packingItem to be removed
     * @return True iff removal was sucessful
     */
    public boolean removePackingItem(int index) {
        try {
            this.packingList.remove(index);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Removes packingItem with given name
     * @param item Name of item to remove
     * @return True iff removal was successful
     */
    public boolean removePackingItem(String item) {
        try {
            this.packingList.remove(item);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Accessor method for name
     * @return name of the camp
     */
    public String getName() {
        return name;
    }

    /**
     * Mutator method for name
     * @param name New name for camp
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Accessor method for faqs
     * @return faqs about the camp
     */
    public Map<String, String> getFAQs() {
        return faqs;
    }

    /**
     * Accessor method for securityQuestions
     * @return  securityQuestions for users of the camp
     */
    public ArrayList<String> getSecurityQuestions() {
        return securityQuestions;
    }

    /**
     * Accessor method for activities
     * @return activities of the camp
     */
    public ArrayList<Activity> getActivities() {
        return activities;
    }

    /**
     * Accessor method for officePhone
     * @return office phone number of the camp
     */
    public String getOfficePhone() {
        return officePhone;
    }

    /**
     * Mutator for officePhone
     * @param officePhone new office phone number
     */
    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    /**
     * Accessor method for packingList
     * @return packingList for campers
     */
    public ArrayList<String> getPackingList() {
        return packingList;
    }

    /**
     * Provides a String representation of the camp
     * @return String representation of the camp
     */
    public String toString() {
        return this.name + "\n" + this.faqs + "\n" + this.securityQuestions 
        + "\n" + activities + "\n" + this.officePhone 
        + "\n" + this.packingList;
    }
}
