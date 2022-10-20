import java.util.ArrayList;
import java.util.UUID;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.HashMap;
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
        try {
            FileReader reader = new FileReader(USERS_FILE_PATH);
            JSONParser parser = new JSONParser();
            JSONArray usersJSON = (JSONArray)new JSONParser().parse(reader);
            for (int i = 0; i < usersJSON.size(); i++) {
                JSONObject userJSON = (JSONObject)usersJSON.get(i);
                UUID id = UUID.fromString((String)userJSON.get(USER_ID));
                UserType type;
                String typeString = (String)userJSON.get(USER_TYPE);
                switch (typeString) {
                    case "DIRECTOR":
                        type = UserType.DIRECTOR;
                        break;
                    case "PARENT":
                        type = UserType.PARENT;
                        break;
                    case "COUNSELOR":
                        type = UserType.COUNSELOR;
                        break;
                    default:
                        type = null;
                }
                String email = (String)userJSON.get(USER_EMAIL);
                String phone = (String)userJSON.get(USER_PHONE);
                String password = (String)userJSON.get(USER_PASSWORD);
                String firstName = (String)userJSON.get(USER_FIRST_NAME);
                String lastName = (String)userJSON.get(USER_LAST_NAME);
                Date birthDate = new SimpleDateFormat("dd-MMM-yyyy").parse((String)userJSON.get(USER_BIRTH_DATE));
                Map<String, String> securityQuestions = new HashMap<String, String>();
                JSONObject questionsJSON = (JSONObject)userJSON.get(USER_SECURITY_QUESTIONS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


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