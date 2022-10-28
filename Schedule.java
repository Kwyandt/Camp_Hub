import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.Calendar;
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
        this.activities = new LinkedHashMap<Date, Activity>();
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

    /**
     * Creates a String visualizing the cabin's schedule
     * @param sessionNumber session of specific cabin
     * @param cabinNumber cabin number
     * @return String representation of schedule
     */
    public String displayOrderedSchedule(int sessionNumber, int cabinNumber) {
        String str = "";
        str += "Schedule for Session " + sessionNumber + ", Cabin " + cabinNumber + "\n\n\n";
        // array tracking if day has been printed yet
        boolean[] days = new boolean[7];
        String dashes = "---------------------";
        for(Date d: activities.keySet()) {
            Instant i = d.toInstant();
            LocalDateTime ldt = LocalDateTime.ofInstant(i, ZoneId.of("EST"));
            // day has not been printed yet
            if(!days[dayToNumber(ldt.getDayOfWeek())]) {
                str += dashes + ldt.getDayOfWeek() + ", " + ldt.getMonth() + ". " + ldt.getDayOfMonth() + dashes;
                days[dayToNumber(ldt.getDayOfWeek())] = true;
            }
            str += ldt.getHour() + ":" + ldt.getMinute() + " - " + activities.get(d).getName() + " at " + activities.get(d).getLocation() + "\n";
        }
        return str;
    }

    /**
     * Saves schedule to a txt file
     * @param sessionNumber session of specific cabin
     * @param cabinNumber cabin number
     */
    public void printOrderedSchedule(int sessionNumber, int cabinNumber) {
        String str = this.displayOrderedSchedule(sessionNumber, cabinNumber);
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

    private int dayToNumber(DayOfWeek day) {
        switch(day) {
            case SUNDAY:
                return 0;
            case MONDAY:
                return 1;
            case TUESDAY:
                return 2;
            case WEDNESDAY:
                return 3;
            case THURSDAY:
                return 4;
            case FRIDAY:
                return 5;
            case SATURDAY:
                return 6;
        }
        return -1;
    }
}
