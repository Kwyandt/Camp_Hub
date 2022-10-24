import java.time.DayOfWeek;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/***
 * @author Jackson
 */
public class Schedule {
    private Map<Date, Activity> activities;

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
    }

    /**
     * View all activities occurring on the specified day
     */
    public void getEventsOfDay(DayOfWeek day) {

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
