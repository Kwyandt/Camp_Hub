import java.time.DayOfWeek;
import java.util.Date;
import java.util.HashMap;

/***
 * @author Jackson
 */
public class Schedule {
    private HashMap<Date, Activity> activities;

    /***
     * Constructor to create new schedule
     */
    public Schedule(){

    }

    /***
     * Constructor to recreate pre-existing schedule
     * @param activities HashMap of dates and activities
     */
    public Schedule(HashMap<Date, Activity> activities){

    }

    /***
     * Adds activity to the schedule at the set time
     * @param activity activity being added
     * @param date time the activity takes place
     */
    public void addEvent(Activity activity, Date date){

    }

    /**
     * View all activities occurring on the specified day
     */
    public void getEventsOfDay(DayOfWeek day){

    }

    /***
     * Accessor for activities
     * @return the activities and their associated dates in a HashMap
     */
    public HashMap<Date, Activity> getActivities(){
        return activities;
    }

    /***
     * toString for Schedule
     * @return String representation of Schedule
     */
    public String toString(){
        return "placeholder";
    }
}
