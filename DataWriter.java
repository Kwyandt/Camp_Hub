import java.util.ArrayList;

import Users.*;

/***
 * @author Jackson
 */
public class DataWriter {
    
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
}
