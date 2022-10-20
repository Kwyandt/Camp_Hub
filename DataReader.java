import java.util.ArrayList;
import Users.*;

import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * @author Nathan Bickel
 * Class that reads in data from JSON files
 */
public class DataReader extends DataConstants {

    /**
     * Reads in user data and populates list of users
     * @return ArrayList containing each user
     */
    public static ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<User>();
        
        return users; 
    }

    /**
     * Reads in camp data and returns camp object
     * @return Camp object with associated data
     */
    public static Camp getCamp() {
        return null;
    }

}