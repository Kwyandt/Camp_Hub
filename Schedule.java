import java.time.DayOfWeek;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;

/***
 * @author Jackson
 */
public class Schedule {
    private Map<Date, Activity> activities;
    public int size = 0;

    /***
     * Constructor to create new schedule
     */
    public Schedule() {
        this.activities = new HashMap<Date, Activity>();
    }

    /***
     * Constructor to recreate pre-existing schedule
     * @param activities HashMap of dates and activities
     */
    public Schedule(Map<Date, Activity> activities) {
        this.activities = activities;
    }

    /***
     * Adds activity to the schedule at the set time
     * @param activity activity being added
     * @param date time the activity takes place
     */
    public void addEvent(Activity activity, Date date) {
        activities.put(date, activity);
        size++;
    }

    /**
     * View all activities occurring on the specified day
     */
    public String getEventsOfDay(Date date) {
        //get all of the indeces with the date
        //concantenate then and return 
        //I'm pretty sure this isn;t possiblezz
        ArrayList<Activity> toReturn = new ArrayList<Activity> ();
        Set<Date> keys = activities.keySet();
        for(Date key : keys){
            if(key.equals(date))
                toReturn.add(activities.get(key));
        }
        return toReturn.toString();
    }

    /***
     * Accessor for activities
     * @return the activities and their associated dates in a HashMap
     */
    public Map<Date, Activity> getActivities() {
        return activities;
    }

    /***
     * toString for Schedule
     * @return String representation of Schedule
     */
    public String toString() {
        return "Activities: " + this.activities.toString();
    }
}
