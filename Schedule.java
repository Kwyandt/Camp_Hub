import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

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
     * remove activity from schedule at specific time
     * @param activity activity being removed
     * @param date time of activity being removed
     * @return True iff removal was sucessful
     */
    public boolean removeEvent(Activity activity, Date date) {
        try {
            activities.remove(date, activity);
            return true;
        } catch (Exception e) {
            return false;
        }
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
            LocalDateTime ldt = LocalDateTime.ofInstant(i, ZoneId.systemDefault());
            // day has not been printed yet
            if(!days[dayToNumber(ldt.getDayOfWeek())]) {
                str += dashes + ldt.getDayOfWeek() + ", " + ldt.getMonth() + " " + ldt.getDayOfMonth() + dashes + "\n";
                days[dayToNumber(ldt.getDayOfWeek())] = true;
            }
            str += convertFromMilTime(ldt.getHour()) + ":" + getMinutesString(ldt.getMinute()) + " " + amPmFormat(ldt.getHour()) + " - " + activities.get(d).getName() + " at " + activities.get(d).getLocation() + "\n";
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

    /**
     * DELETES all current activities, and then populates meal times
     * and randomly chosen activities throughout the days.
     * @param start Start date of the session
     * @param end End date of the session
     */
    public void randomlyPopulate(Date start, Date end) {
        Camp camp = Camp.getInstance();
        // Deletes old activities
        this.activities = new TreeMap<Date, Activity>();
        // Set up constants: meals are in order breakfast, lunch, dinner
        Activity[] mealActivities = {
            new Activity("Breakfast", "", "Dining Hall"),
            new Activity("Lunch", "", "Dining Hall"),
            new Activity("Dinner", "", "Dining Hall")
        };
        int[] mealTimes = {8,13,18};
        int[] activityTimes = {9,10,15,16};
        LocalDateTime startLDT = LocalDateTime.ofInstant(start.toInstant(), ZoneId.systemDefault());
        LocalDateTime endLDT = LocalDateTime.ofInstant(end.toInstant(), ZoneId.systemDefault());
        int numDays = ((Number)ChronoUnit.DAYS.between(startLDT, endLDT)).intValue();
        // Dinner evening after arriving
        LocalDateTime dinnerStart = startLDT.withHour(mealTimes[2]);
        this.activities.put(Date.from(dinnerStart.atZone(ZoneId.systemDefault()).toInstant()), mealActivities[2]);
        // Loops through all the days between the start and end
        for (int day = 1; day < numDays; day++) {
            // Generate activities
            LocalDateTime dayLDT = startLDT.plusDays(day);
            LocalDateTime[] mealLDTs = new LocalDateTime[mealTimes.length];
            for (int meal = 0; meal < mealLDTs.length; meal++)
                mealLDTs[meal] = dayLDT.withHour(mealTimes[meal]);
            LocalDateTime[] activityLDTs = new LocalDateTime[activityTimes.length];
            for (int activity = 0; activity < activityLDTs.length; activity++)
                activityLDTs[activity] = dayLDT.withHour(activityTimes[activity]);
            Activity[] randomActivities = chooseKRandomActivities(activityLDTs.length);
            if (randomActivities.length < activityLDTs.length)
                return;
            // Add in activities
            this.activities.put(Date.from(mealLDTs[0].atZone(ZoneId.systemDefault()).toInstant()), mealActivities[0]);
            this.activities.put(Date.from(activityLDTs[0].atZone(ZoneId.systemDefault()).toInstant()), randomActivities[0]);
            this.activities.put(Date.from(activityLDTs[1].atZone(ZoneId.systemDefault()).toInstant()), randomActivities[1]);
            this.activities.put(Date.from(mealLDTs[1].atZone(ZoneId.systemDefault()).toInstant()), mealActivities[1]);
            this.activities.put(Date.from(activityLDTs[2].atZone(ZoneId.systemDefault()).toInstant()), randomActivities[2]);
            this.activities.put(Date.from(activityLDTs[3].atZone(ZoneId.systemDefault()).toInstant()), randomActivities[3]);
            this.activities.put(Date.from(mealLDTs[2].atZone(ZoneId.systemDefault()).toInstant()), mealActivities[2]);
        }
        // Breakfast morning before leaving
        LocalDateTime breakfastEnd = endLDT.withHour(mealTimes[0]);
        this.activities.put(Date.from(breakfastEnd.atZone(ZoneId.systemDefault()).toInstant()), mealActivities[0]);
    }

    // Helper method to convert DayOfWeek enum to number
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

    // Helper method to revert from 24-hour time
    private String convertFromMilTime(int hour) {

        return Integer.toString(hour % 12);
    }

    // Helper method for printing minutes in time format
    private String getMinutesString(int minutes) {
        if(minutes < 10) {
            return "0" + Integer.toString(minutes);
        }
        return Integer.toString(minutes);
    }

    // Helper method for checking if hour is AM or PM
    private String amPmFormat(int hour) {
        if(hour < 12) {
            return "AM";
        }
        return "PM";
    }

    // Returns k random non-meal activities from Camp.Activities. If there are
    // fewer than k activities, the whole list is returned.
    // Precondition: Camp.activities is not null
    private Activity[] chooseKRandomActivities(int k) {
        Camp camp = Camp.getInstance();
        ArrayList<Activity> campActivities = (ArrayList<Activity>)camp.getActivities().clone();
        if (k >= campActivities.size())
            return campActivities.toArray(new Activity[activities.size()]);
        Activity[] activitiesToReturn = new Activity[k];
        for (int i = 0; i < k; i++)
            activitiesToReturn[i] = campActivities.remove((int)(Math.random()*campActivities.size()));
        return activitiesToReturn;
    }

    /*public static void main(String[] args) {
        DataReader.getCamp();
        Session session = SessionList.getInstance().getSession(0);
        Schedule schedule = session.getCabin(0).getSchedule();
        schedule.randomlyPopulate(session.getStartDate(), session.getEndDate());
        System.out.println(schedule.displayOrderedSchedule(0, 0));
    }*/

}
