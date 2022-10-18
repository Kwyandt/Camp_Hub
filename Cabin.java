import java.util.ArrayList;
import java.util.UUID;

import Users.*;

/***
 * @author Jackson
 */
public class Cabin {
    private UUID id;
    private int cabinNumber;
    private Schedule schedule;
    private Counselor counselor;
    private Camper[] campers;

    /***
     * Creates a new cabin
     * @param schedule schedule for the cabin
     * @param counselor counselor of the cabin
     */
    public Cabin(Schedule schedule, Counselor counselor){
        
    }

    /***
     * Loads a previously created cabin
     * @param id UUID of cabin
     * @param cabinNumber number of cabin
     * @param schedule schedule for the cabin
     * @param counselor counselor of the cabin
     * @param campers campers in the cabin
     */
    public Cabin(UUID id, int cabinNumber, Schedule schedule, Counselor counselor, Camper[] campers){

    }

    /***
     * Mutator method for counselor
     * @param counselor new counselor for cabin
     */
    public void setCounselor(Counselor counselor){

    }

    /***
     * Adds camper to campers if there is space
     * @param camper potential camper for cabin
     */
    public void addCamper(Camper camper){

    }

    /***
     * Removes camper from cabin if they are present
     * @param camper camper to be removed from cabin
     */
    public void removeCamper(Camper camper){

    }

    /***
     * Collects all meds used in the cabin
     * @return ArrayList containing all meds used
     */
    public ArrayList<String> getMeds(){
        return null;
    }

    /***
     * Collects all allergies of cabin members
     * @return ArrayList containing all allergies
     */
    public ArrayList<String> getAllergies(){
        return null;
    }

    /***
     * Displays the schedule of the cabin
     */
    public void viewSchedule(){

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

    /***
     * toString for Cabin
     * @return String representation of Cabin
     */
    public String toString(){
        return "placeholder";
    }
}

