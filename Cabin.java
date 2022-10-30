import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

import Users.*;

/***
 * @author Jackson
 */
public class Cabin {
    private int cabinNumber;
    private Schedule schedule;
    private Counselor counselor;
    private Camper[] campers = new Camper[8];
    private int minAge;
    private int maxAge;

    /***
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

    /***
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

    /***
     * Mutator method for counselor
     * @param counselor new counselor for cabin
     */
    public void setCounselor(Counselor counselor){
        this.counselor = counselor;
    }

    /***
     * Adds camper to campers if there is space
     * @param camper potential camper for cabin
     */
    public boolean addCamper(Camper camper){
        for(int i =0; i<campers.length; i++){
            if(campers[i]==null){
                campers[i] = camper;
                return true;
            }
        }
        return false;
    }

    /***
     * Removes camper from cabin if they are present
     * @param camper camper to be removed from cabin
     */
    public boolean removeCamper(Camper camper){
        for(int i=0; i<campers.length; i++){
            if(campers[i].equals(camper)){
                campers[i] = null;
                for(int j=i; j<campers.length-1; j++)
                    campers[j] = campers[j+1];
                campers[campers.length] = null;
                 return true;
             }
         }
         return false;
    }

    /***
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

    /***
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

    /***
     * Displays the schedule of the cabin
     */
    public String viewSchedule(){
        String toReturn = "";
        for(int i =0; i<schedule.getActivities().size(); i++) {
            toReturn = toReturn + "\n" + schedule.toString();
        }
        return toReturn;
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
    public Camper[] getCampers(){
        return campers;
    }

    public int getMinAge () {
        return minAge;
    }

    public int getMaxAge () {
        return maxAge;
    }

    /**
     * Equals method for cabin
     * @param cabin to be compared to this.cabin
     * @return boolean true or false
     */
    public boolean equals (Cabin cabin) {
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

    /***
     * toString for Cabin
     * @return String representation of Cabin
     */
    public String toString(){
        return "Cabin:\n\t" + this.cabinNumber + "\n\t" + this.schedule + "\n\t" + this.counselor + "\n\t" + Arrays.toString(this.campers);
    }
}

