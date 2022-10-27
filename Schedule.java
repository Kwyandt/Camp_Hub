import java.time.DayOfWeek;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * @author Jackson
 */
public class Schedule {
    private Map<Date, Activity> activities;

    /**
     * Constructor to create new schedule
     */
    public Schedule() {
        this.activities = new TreeMap<Date, Activity>();
    }

    /**
     * Constructor to recreate pre-existing schedule
     * @param activities HashMap of dates and activities
     */
    public Schedule(Map<Date, Activity> activities) {
        this.activities = activities;
    }

    /**
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
    public ArrayList<Activity> getEventsOfDay(Date date) {
        ArrayList<Activity> toReturn = new ArrayList<Activity>();
        for(Date key : activities.keySet())
            if (date.toInstant().truncatedTo(ChronoUnit.DAYS).
                equals(key.toInstant().truncatedTo(ChronoUnit.DAYS)))
                toReturn.add(activities.get(key));
        return toReturn;
    }

    /**
     * Accessor for activities
     * @return the activities and their associated dates in a HashMap
     */
    public Map<Date, Activity> getActivities() {
        return activities;
    }

    /**
     * toString for Schedule
     * @return String representation of Schedule
     */
    public String toString() {
        return "Activities: " + this.activities.toString();
    }

    public String displayOrderedSchedule() {
        String str = "";
        for(Date d: activities.keySet()) {
            str += d.toString() + " - " + activities.get(d).getName() + " - " + activities.get(d).getLocation() + "\n";
        }
        return str;
    }

    public void printOrderedSchedule(int sessionNumber, int cabinNumber) {
        String str = this.displayOrderedSchedule();
        String fileName = "session" + sessionNumber + "cabin" + cabinNumber + "schedule.txt";
        try(FileWriter file = new FileWriter(fileName)) {
            file.write(str);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if schedule is equal to parameter schedule
     * @param otherSchedule Schedule to compare
     * @return True if schedule has same activities and false otherwise
     */
    public boolean equals(Schedule otherSchedule) {
        return activities.equals(otherSchedule.getActivities());
    }
}
