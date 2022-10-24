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
                Date birthDate = fromFormattedDate(userJSON.get(USER_BIRTH_DATE));
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
                        Date camperBirthDate = fromFormattedDate(camperJSON.get(CAMPER_BIRTH_DATE));
                        ArrayList<String> camperMeds = JSONArrToArrayList(camperJSON.get(CAMPER_MEDS));
                        ArrayList<String> camperAllergies = JSONArrToArrayList(camperJSON.get(CAMPER_ALLERGIES));
                        Map<Relationship, EmergencyContact> camperEmergencyContacts = new HashMap<Relationship, EmergencyContact>();
                        JSONObject camperContactsJSON = (JSONObject)camperJSON.get(CAMPER_EMERGENCY_CONTACTS);
                        for (Object key : camperContactsJSON.keySet()) {
                            String relationshipString = (String)key;
                            Relationship relationship = null;
                            switch (relationshipString) {
                                case "DOCTOR":
                                    relationship = Relationship.DOCTOR;
                                    break;
                                case "DENTIST":
                                    relationship = Relationship.DENTIST;
                                    break;
                                case "GUARDIAN":
                                    relationship = Relationship.GUARDIAN;
                                    break;
                            }
                            JSONObject contactJSON = (JSONObject)camperContactsJSON.get(relationshipString);
                            String ECfirstName = (String)contactJSON.get(EMERGENCY_CONTACT_FIRST_NAME);
                            String EClastName = (String)contactJSON.get(EMERGENCY_CONTACT_LAST_NAME);
                            String ECphoneNumber = (String)contactJSON.get(EMERGENCY_CONTACT_PHONE_NUMBER);
                            camperEmergencyContacts.put(relationship, new EmergencyContact(ECfirstName, EClastName, ECphoneNumber));
                        }
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
                    for (Object key : contactsJSON.keySet()) {
                        String relationshipString = (String)key;
                        Relationship relationship = null;
                        switch (relationshipString) {
                            case "DOCTOR":
                                relationship = Relationship.DOCTOR;
                                break;
                            case "DENTIST":
                                relationship = Relationship.DENTIST;
                                break;
                            case "GUARDIAN":
                                relationship = Relationship.GUARDIAN;
                                break;
                        }
                        JSONObject contactJSON = (JSONObject)contactsJSON.get(relationshipString);
                        String ECfirstName = (String)contactJSON.get(EMERGENCY_CONTACT_FIRST_NAME);
                        String EClastName = (String)contactJSON.get(EMERGENCY_CONTACT_LAST_NAME);
                        String ECphoneNumber = (String)contactJSON.get(EMERGENCY_CONTACT_PHONE_NUMBER);
                        emergencyContacts.put(relationship, new EmergencyContact(ECfirstName, EClastName, ECphoneNumber));
                    }
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
            UserList userList = UserList.getInstance();
            String name = (String)campJSON.get(CAMP_NAME);
            ArrayList<Activity> activities = new ArrayList<Activity>();
            JSONArray activitiesJSON = (JSONArray)campJSON.get(CAMP_ACTIVITIES);
            for (int a = 0; a < activitiesJSON.size(); a++) {
                JSONObject activityJSON = (JSONObject)activitiesJSON.get(a);
                String activityId = (String)activityJSON.get(ACTIVITY_ID);
                String activityName = (String)activityJSON.get(ACTIVITY_NAME);
                String activityDescription = (String)activityJSON.get(ACTIVITY_DESCRIPTION);
                String activityLocation = (String)activityJSON.get(ACTIVITY_LOCATION);
                activities.add(new Activity(activityId, activityName, activityDescription, activityLocation));
            }
            SessionList sessions = SessionList.getInstance();
            JSONArray sessionsJSON = (JSONArray)campJSON.get(CAMP_SESSIONS);
            for (int s = 0; s < sessionsJSON.size(); s++) {
                JSONObject sessionJSON = (JSONObject)sessionsJSON.get(s);
                double price = (double)sessionJSON.get(SESSION_PRICE);
                String theme = (String)sessionJSON.get(SESSION_THEME);
                ArrayList<Cabin> cabins = new ArrayList<Cabin>();
                JSONArray cabinsJSON = (JSONArray)sessionJSON.get(SESSION_CABINS);
                for (int c = 0; c < cabinsJSON.size(); c++) {
                    JSONObject cabinJSON = (JSONObject)cabinsJSON.get(c);
                    int cabinNumber = ((Number)cabinJSON.get(CABIN_NUMBER)).intValue();
                    Schedule schedule = new Schedule();
                    JSONObject scheduleJSON = (JSONObject)cabinJSON.get(CABIN_SCHEDULE);
                    JSONObject scheduleActivitiesJSON = (JSONObject)scheduleJSON.get(SCHEDULE_ACTIVITIES);
                    for (Object key : scheduleActivitiesJSON.keySet()) {
                        Date scheduleDate = fromFormattedDateTime(key);
                        UUID activityID = UUID.fromString((String)scheduleActivitiesJSON.get(key));
                        Activity scheduleActivity = null;
                        for (Activity a : activities) {
                            if (a.getId().equals(activityID)) {
                                scheduleActivity = a;
                                break;
                            }
                        }
                        schedule.addEvent(scheduleActivity, scheduleDate);
                    }
                    Counselor counselor;
                    if ((String)cabinJSON.get(CABIN_COUNSELOR) == null)
                        counselor = null;
                    else
                        counselor = (Counselor)userList.getUserByUUID(UUID.fromString((String)cabinJSON.get(CABIN_COUNSELOR)));
                    Camper[] campers = new Camper[8];
                    JSONArray campersJSON = (JSONArray)cabinJSON.get(CABIN_CAMPERS);
                    for (int i = 0; i < campersJSON.size(); i++) {
                        campers[i] = userList.getCamperByUUID(UUID.fromString((String)campersJSON.get(i)));
                    }
                    cabins.add(new Cabin(cabinNumber, schedule, counselor, campers));
                }
                Date priorityDeadline = fromFormattedDate(sessionJSON.get(SESSION_PRIORITY_DEADLINE));
                Date regularDeadline = fromFormattedDate(sessionJSON.get(SESSION_REGULAR_DEADLINE));
                Date startDate = fromFormattedDate(sessionJSON.get(SESSION_START_DATE));
                Date endDate = fromFormattedDate(sessionJSON.get(SESSION_END_DATE));
                sessions.addSession(new Session(price, theme, cabins, priorityDeadline, regularDeadline, startDate, endDate));
            }
            Map<String, String> faqs = new HashMap<String, String>();
            JSONObject faqsJSON = (JSONObject)campJSON.get(CAMP_FAQS);
            for (Object faq : faqsJSON.keySet())
                faqs.put((String)faq, (String)faqsJSON.get(faq));
            ArrayList<String> securityQuestions = JSONArrToArrayList(campJSON.get(CAMP_SECURITY_QUESTIONS));
            String officePhone = (String)campJSON.get(CAMP_OFFICE_PHONE);
            ArrayList<String> packingList = JSONArrToArrayList(campJSON.get(CAMP_PACKING_LIST));
            return new Camp(name, sessions, faqs, securityQuestions, activities, officePhone, packingList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }

    private static ArrayList<String> JSONArrToArrayList(Object JSONArr) {
        JSONArray objectsJSON = (JSONArray)JSONArr;
        return new ArrayList<>(Arrays.asList(Arrays.copyOf(objectsJSON.toArray(), objectsJSON.size(), String[].class)));
    }

    private static Date fromFormattedDate(Object dateString) {
        try {
            return new SimpleDateFormat("dd-MMM-yyyy").parse((String)dateString);
        } catch (Exception e) {
            return null;
        } 
    }

    private static Date fromFormattedDateTime(Object dateString) {
        try {
            return new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss a").parse((String)dateString);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        getCamp();
        Map<Date, Activity> act = SessionList.getInstance().getSession(fromFormattedDate("18-Jun-2023"))
                                        .getCabin(1).getSchedule().getActivities();
        System.out.println(act.toString());
        
    }

}