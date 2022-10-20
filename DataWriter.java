import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
                cabinDetails.put(CABIN_COUNSELOR, cab.getCounselor());
            }
        }
        return campDetails;
    }
}
