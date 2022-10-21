import java.util.ArrayList;
import java.util.Arrays;
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
            JSONArray usersJSON = (JSONArray)parser.parse(reader);
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
                        continue;
                }
                String email = (String)userJSON.get(USER_EMAIL);
                String phone = (String)userJSON.get(USER_PHONE);
                String password = (String)userJSON.get(USER_PASSWORD);
                String firstName = (String)userJSON.get(USER_FIRST_NAME);
                String lastName = (String)userJSON.get(USER_LAST_NAME);
                Date birthDate = new SimpleDateFormat("dd-MMM-yyyy").parse((String)userJSON.get(USER_BIRTH_DATE));
                Map<String, String> securityQuestions = new HashMap<String, String>();
                JSONObject questionsJSON = (JSONObject)userJSON.get(USER_SECURITY_QUESTIONS);
                securityQuestions.putAll(questionsJSON); 
                if (type == UserType.DIRECTOR) {
                    String bio = (String)userJSON.get(DIRECTOR_BIO);
                    ArrayList<String> notes = JSONArrToArrayList(userJSON.get(DIRECTOR_NOTES));
                    users.add(new Director(id, email, password, firstName, lastName, phone, birthDate, securityQuestions,
                                           bio, notes));
                } else if (type == UserType.PARENT) {
                    ArrayList<Camper> children = new ArrayList<Camper>();
                    JSONArray campersJSON = (JSONArray)userJSON.get(PARENT_CHILDREN);
                    for (int c = 0; c < campersJSON.size(); c++) {
                        JSONObject camperJSON = (JSONObject)campersJSON.get(c);
                        UUID camperId = UUID.fromString((String)camperJSON.get(CAMPER_ID));
                        String camperFirstName = (String)camperJSON.get(CAMPER_FIRST_NAME);
                        String camperLastName = (String)camperJSON.get(CAMPER_LAST_NAME);
                        Date camperBirthDate = new SimpleDateFormat("dd-MMM-yyyy").parse((String)camperJSON.get(CAMPER_BIRTH_DATE));
                        ArrayList<String> camperMeds = JSONArrToArrayList(camperJSON.get(CAMPER_MEDS));
                        ArrayList<String> camperAllergies = JSONArrToArrayList(camperJSON.get(CAMPER_ALLERGIES));
                        Map<Relationship, EmergencyContact> camperEmergencyContacts = new HashMap<Relationship, EmergencyContact>();
                        JSONObject camperContactsJSON = (JSONObject)camperJSON.get(CAMPER_EMERGENCY_CONTACTS);
                        camperEmergencyContacts.putAll(camperContactsJSON);
                        ArrayList<String> camperDietaryRestrictions = JSONArrToArrayList(camperJSON.get(CAMPER_DIETARY_RESTRICTIONS));
                        String camperTShirt = (String)camperJSON.get(CAMPER_T_SHIRT);
                        children.add(new Camper(camperId, camperFirstName, camperLastName, camperBirthDate, camperMeds, camperAllergies, camperEmergencyContacts, camperDietaryRestrictions, camperTShirt));
                    }
                    boolean isReturning = (boolean)userJSON.get(PARENT_IS_RETURNING);
                    users.add(new Parent(id, email, password, firstName, lastName, phone, birthDate, securityQuestions,
                                         children, isReturning));
                } else if (type == UserType.COUNSELOR) {
                    ArrayList<String> meds = JSONArrToArrayList(userJSON.get(COUNSELOR_MEDS));
                    ArrayList<String> allergies = JSONArrToArrayList(userJSON.get(COUNSELOR_ALLERGIES));
                    Map<Relationship, EmergencyContact> emergencyContacts = new HashMap<Relationship, EmergencyContact>();
                    JSONObject contactsJSON = (JSONObject)userJSON.get(COUNSELOR_EMERGENCY_CONTACTS);
                    emergencyContacts.putAll(contactsJSON);
                    ArrayList<String> dietaryRestrictions = JSONArrToArrayList(userJSON.get(COUNSELOR_DIETARY_RESTRICTIONS));
                    String tShirt = (String)userJSON.get(COUNSELOR_T_SHIRT);
                    String bio = (String)userJSON.get(COUNSELOR_BIO);
                    ArrayList<String> notes = JSONArrToArrayList(userJSON.get(COUNSELOR_NOTES));
                    users.add(new Counselor(id, email, phone, password, firstName, lastName, birthDate, securityQuestions, 
                                            meds, allergies, emergencyContacts, dietaryRestrictions, tShirt, bio, notes));
                } else {
                    continue;
                }
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
        try {
            FileReader reader = new FileReader(CAMP_FILE_PATH);
            JSONParser parser = new JSONParser();
            JSONObject campJSON = (JSONObject)parser.parse(reader);
            String name = (String)campJSON.get(CAMP_NAME);
            SessionList sessions = SessionList.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }

    private static ArrayList<String> JSONArrToArrayList(Object JSONArr) {
        JSONArray objectsJSON = (JSONArray)JSONArr;
        return new ArrayList<>(Arrays.asList(Arrays.copyOf(objectsJSON.toArray(), objectsJSON.size(), String[].class)));
    }

}