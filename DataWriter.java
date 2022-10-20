import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.Date;

import Users.*;

/***
 * @author Jackson
 */
public class DataWriter extends DataConstants{
    
    /***
     * Stores all users in users.json
     * @param users ArrayList of all users
     * @return if users were successfully saved
     */
    public static boolean saveAllUsers(ArrayList<User> users) {
        return true;
    }

    /***
     * Stores camp and associated data in camp.json
     * @param camp camp to be stored
     * @return if camp was successfully saved
     */
    public static boolean saveCamp(Camp camp) {
        return true;
    }

    private static JSONObject getCampJSON(Camp camp) {
        JSONObject campDetails = new JSONObject();
        campDetails.put(CAMP_NAME, camp.getName());
        JSONArray sessionArray = new JSONArray();
        SessionList sessionList = SessionList.getInstance();
        for(Session s: sessionList.getAllSessions()) {
            JSONObject sessionDetails = new JSONObject();
            sessionDetails.put(SESSION_THEME, s.getTheme());
            JSONArray cabinArray = new JSONArray();
            for(Cabin cab: s.getCabins()) {
                JSONObject cabinDetails = new JSONObject();
                cabinDetails.put(CABIN_ID, cab.getId().toString());
                cabinDetails.put(CABIN_NUMBER, cab.getCabinNumber());
                // TODO: schedule
                JSONObject scheduleDetails = new JSONObject();
                Schedule sch = cab.getSchedule();
                for(Date d: sch.getActivities().keySet()) {
                    scheduleDetails.put(d, sch.getActivities().get(d).getId());
                }
                cabinDetails.put(CABIN_SCHEDULE, scheduleDetails);
                //TODO: Write counselor JSON method and use below
                cabinDetails.put(CABIN_COUNSELOR, cab.getCounselor());
                JSONArray camperArray = new JSONArray();
                //TODO: Write camper JSON method and use below
                for(Camper cam: cab.getCampers()) {
                    
                }
            }
        }
        return campDetails;
    }

    public JSONObject getCamperJSON(Camper camper) {
        JSONObject camperDetails = new JSONObject();
        camperDetails.put(CAMPER_ID, camper.getUuid());
        camperDetails.put(CAMPER_FIRST_NAME, camper.getFirst());
        camperDetails.put(CAMPER_LAST_NAME, camper.getLast());
        camperDetails.put(CAMPER_BIRTH_DATE, camper.getBirth());
        JSONArray medsArray = new JSONArray();
        for(String med: camper.getMeds()) {
            medsArray.add(med);
        }
        camperDetails.put(CAMPER_MEDS, medsArray);
        JSONArray allergyArray = new JSONArray();
        for(String allergy: camper.getAllergy()) {
            allergyArray.add(allergy);
        }
        camperDetails.put(CAMPER_ALLERGIES, allergyArray);
        JSONArray contactArray = new JSONArray();
        for(Relationship relation : camper.getEmergencyContact().keySet()) {
            JSONObject contactDetails = new JSONObject();
            //contactDetails.put(EMERGENCY_CONTACT_ID ,camper.getEmergencyContact().get(relation).get)
        }
        return camperDetails;
    }
}
