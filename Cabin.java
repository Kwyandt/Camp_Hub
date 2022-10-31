import java.util.ArrayList;
import java.util.Arrays;

import Users.*;

/**
 * @author Jackson
 */
public class Cabin {
    private int cabinNumber;
    private Schedule schedule;
    private Counselor counselor;
    private Camper[] campers = new Camper[8];
    private int minAge;
    private int maxAge;

    /**
     * Creates a new cabin
     * @param schedule schedule for the cabin
     * @param counselor counselor of the cabin
     */
    public Cabin(Schedule schedule, Counselor counselor, int minAge, int maxAge) {
        this.schedule = schedule;
        this.counselor = counselor;
        this.minAge = minAge;
        this.maxAge = maxAge;
    }

    /**
     * Loads a previously created cabin
     * @param cabinNumber number of cabin
     * @param schedule schedule for the cabin
     * @param counselor counselor of the cabin
     * @param campers campers in the cabin
     */
    public Cabin(int cabinNumber, Schedule schedule, Counselor counselor, Camper[] campers, int minAge, int maxAge){
        this.cabinNumber = cabinNumber;
        this.schedule = schedule;
        this.counselor = counselor;
        this.campers = campers;
        this.minAge = minAge;
        this.maxAge = maxAge;
    }

    /**
     * Mutator method for counselor
     * @param counselor new counselor for cabin
     */
    public void setCounselor(Counselor counselor){
        this.counselor = counselor;
    }

    /**
     * Adds camper to campers if there is space
     * @param camper potential camper for cabin
     */
    public boolean addCamper(Camper camper) {
        for (int i = 0; i < campers.length; i++) {
            if (campers[i] == null) {
                campers[i] = camper;
                return true;
            }
        }
        return false;
    }

    /**
     * Removes camper from cabin if they are present
     * @param camper camper to be removed from cabin
     */
    public boolean removeCamper(Camper camper){
        for (int i = 0; i < campers.length; i++) {
            if (campers[i].equals(camper)) {
                campers[i] = null;
                for (int j = i; j < campers.length - 1; j++)
                    campers[j] = campers[j+1];
                campers[campers.length] = null;
                return true;
            }
        }
        return false;
    }

    /**
     * Collects all meds used in the cabin
     * @return ArrayList containing all meds used
     */
    public ArrayList<String> getMeds(){
        ArrayList<String> meds = new ArrayList<String>();
        for(int i=0; i< campers.length; i++){
            if(campers[i].getMeds()!=null)
                meds.addAll(campers[i].getMeds());
        }
        return meds;
    }

    /**
     * Collects all allergies of cabin members
     * @return ArrayList containing all allergies
     */
    public ArrayList<String> getAllergies(){
        ArrayList<String> allergies = new ArrayList<String>();
        for(int i=0; i<campers.length; i++) {
            if(campers[i].getAllergy()!=null)
                allergies.addAll(campers[i].getAllergy());
        }
        return allergies;
    }

    /**
     * Displays the schedule of the cabin
     */
    public String viewSchedule(int sessionNumber) {
        return schedule.displayOrderedSchedule(sessionNumber, this.cabinNumber);
    }

    /***
     * Accessor method for cabinNumber
     * @return cabinNumber of cabin
     */
    public int getCabinNumber(){
        return cabinNumber;
    }

    /***
     * Accessor method for schedule
     * @return schedule of cabin
     */
    public Schedule getSchedule(){
        return schedule;
    }

    /***
     * Accessor method for counselor
     * @return counselor of cabin
     */
    public Counselor getCounselor(){
        return counselor;
    }

    /***
     * Accessor method for campers
     * @return campers of cabins
     */
    public Camper[] getCampers() {
        return campers;
    }

    /**
     * Gets INCLUSIVE min age of cabin
     * @return Min age of cabin
     */
    public int getMinAge() {
        return minAge;
    }

    /**
     * Gets INCLUSIVE max age of cabin
     * @return Max age of cabin
     */
    public int getMaxAge() {
        return maxAge;
    }

    /**
     * Sets INCLUSIVE min age of cabin (ex. if 12, campers are excluded
     * if they are 11.99... years old or younger)
     * @param age Min age of cabin
     */
    public void setMinAge(int age) {
        this.minAge = age;
    }

    /**
     * Sets INCLUSIVE max age of cabin (ex. if 15, campers are excluded
     * if they are 16.0 years old or older)
     * @param age Max of cabin
     */
    public void setMaxAge(int age) {
        this.maxAge = age;
    }

    /**
     * Equals method for cabin
     * @param cabin to be compared to this.cabin
     * @return boolean true or false
     */
    public boolean equals(Cabin cabin) {
        return false;
    }

    /**
     * Shows all vital info about each camper
     * @return formatted String detailing camper vitals
     */
    public String getAllCampersInfo() {
        String str = "";
        String dashes = "---------------------";
        for(Camper c: campers) {
            if(c == null) {
                continue;
            }
            str += dashes + " " + c.getFirst() + " " + c.getLast() + " " + dashes + "\n";
            str += "Allergies: ";
            for(String a: c.getAllergy()) {
                str += a + ", ";
            }
            // cuts off extra comma
            str = str.substring(0, str.length()-2);
            str += "\n";
            str += "Medications: ";
            for(String m: c.getMeds()) {
                str += m + ", ";
            }
            str = str.substring(0, str.length()-2);
            str += "\n";
            str += "Emergency contacts: ";
            for(Relationship r: c.getEmergencyContact().keySet()) {
                EmergencyContact ec = c.getEmergencyContact().get(r);
                str += r.toString().toLowerCase() + ": " + ec.getFirst() + " " + ec.getLast() + ", " + ec.getLocation() + ", " + ec.getPhone() + "\n";
            }
        }
        return str;
    }

    /**
     * Checks if camper is in cabin
     * @param c camper to check
     * @return true if c is in cabin, false otherwise
     */
    public boolean camperInCabin(Camper c) {
        /* if(c.getAge() < minAge || c.getAge() > maxAge) {
            // c is outside of valid age range for this cabin
            return false;
        } */
        for(Camper x: campers) {
            if(x != null && c.equals(x)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if counselor is in cabin
     * @param c counselor to check
     * @return true if c is cabin's counselor, false otherwise
     */
    public boolean counselorInCabin(Counselor c) {
        return this.counselor != null && c.equals(counselor);
    }

    /***
     * toString for Cabin
     * @return String representation of Cabin
     */
    public String toString(){
        return "Cabin:\n\t" + this.cabinNumber + "\n\t" + this.schedule + "\n\t" + this.counselor + "\n\t" + Arrays.toString(this.campers);
    }
}

