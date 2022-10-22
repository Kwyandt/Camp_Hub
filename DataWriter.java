import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.Date;
import java.util.HashMap;

import Users.*;

/***
 * @author Jackson
 */
@SuppressWarnings("unchecked")
public class DataWriter extends DataConstants {
    
    /***
     * Stores all users in users.json
     * @param users ArrayList of all users
     * @return if users were successfully saved
     */
    public static boolean saveAllUsers(ArrayList<User> users) {
        JSONArray userArray = new JSONArray();
        for(User u: users) {
            switch(u.getUserType()) {
                case DIRECTOR:
                    userArray.add(getDirectorJSON((Director)u));
                    break;
                case PARENT:
                    userArray.add(getParentJSON((Parent)u));
                    break;
                case COUNSELOR:
                    userArray.add(getCounselorJSON((Counselor)u));
            }
        }
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
                cabinDetails.put(CABIN_NUMBER, cab.getCabinNumber());
                JSONObject scheduleDetails = new JSONObject();
                Schedule sch = cab.getSchedule();
                for(Date d: sch.getActivities().keySet()) {
                    scheduleDetails.put(d, sch.getActivities().get(d).getId());
                }
                cabinDetails.put(CABIN_SCHEDULE, scheduleDetails);
                cabinDetails.put(CABIN_COUNSELOR, getCounselorJSON(cab.getCounselor()));
                JSONArray camperArray = new JSONArray();
                for(Camper cam: cab.getCampers()) {
                    camperArray.add(getCamperJSON(cam));
                }
                cabinDetails.put(CABIN_CAMPERS, camperArray);
                cabinArray.add(cabinDetails);
            }
            sessionDetails.put(SESSION_CABINS, cabinArray);
            sessionDetails.put(SESSION_PRIORITY_DEADLINE, s.getPriorityDeadline());
            sessionDetails.put(SESSION_REGULAR_DEADLINE, s.getRegularDeadline());
            sessionDetails.put(SESSION_START_DATE, s.getStartDate());
            sessionDetails.put(SESSION_END_DATE, s.getEndDate());
            sessionArray.add(sessionDetails);
        }
        campDetails.put(CAMP_SESSIONS, sessionArray);
        JSONArray faqArray = new JSONArray();
        for(String q: camp.getFAQs().keySet()) {
            JSONObject faqDetails = new JSONObject();
            faqDetails.put(q, camp.getFAQs().get(q));
            faqArray.add(faqDetails);
        }
        campDetails.put(CAMP_FAQS, faqArray);
        JSONArray secQArray = new JSONArray();
        for(String q: camp.getSecurityQuestions()) {
            secQArray.add(q);
        }
        campDetails.put(CAMP_SECURITY_QUESTIONS, secQArray);
        JSONArray activityArray = new JSONArray();
        for(Activity a: camp.getActivities()) {
            JSONObject activityDetails = new JSONObject();
            activityDetails.put(ACTIVITY_ID, a.getId());
            activityDetails.put(ACTIVITY_NAME, a.getName());
            activityDetails.put(ACTIVITY_DESCRIPTION, a.getDescription());
            activityDetails.put(ACTIVITY_LOCATION, a.getLocation());
            activityArray.add(activityDetails);
        }
        campDetails.put(CAMP_ACTIVITIES, activityArray);
        campDetails.put(CAMP_OFFICE_PHONE, camp.getOfficePhone());
        JSONArray packingArray = new JSONArray();
        for(String p: camp.getPackingList()) {
            packingArray.add(p);
        }
        campDetails.put(CAMP_PACKING_LIST, packingArray);
        return campDetails;
    }

    private static JSONObject getUserJSON(User user) {
        JSONObject userDetails = new JSONObject();
        userDetails.put(USER_ID, user.getUuid());
        userDetails.put(USER_EMAIL, user.getEmail());
        userDetails.put(USER_PHONE, user.getPhone());
        userDetails.put(USER_PASSWORD, user.getPassword());
        userDetails.put(USER_FIRST_NAME, user.getFirstName());
        userDetails.put(USER_LAST_NAME, user.getLastName());
        userDetails.put(USER_BIRTH_DATE, user.getBirthDate());
        JSONArray secQArray = new JSONArray();
        //HashMap<String, String> secQs = user.getSecurityQuestions();
        for(String q: user.getSecurityQuestions().keySet()) {
            JSONObject secQDetails = new JSONObject();
            secQDetails.put(q, user.getSecurityQuestions().get(q));
            secQArray.add(secQDetails);
        }
        userDetails.put(USER_SECURITY_QUESTIONS, secQArray);
        return userDetails;
    }

    private static JSONObject getDirectorJSON(Director director) {
        JSONObject directorDetails = getUserJSON(director);
        directorDetails.put(DIRECTOR_BIO, director.getBio());
        JSONArray noteArray = new JSONArray();
        for(String n: director.getNotes()) {
            noteArray.add(n);
        }
        directorDetails.put(DIRECTOR_NOTES, noteArray);
        return directorDetails;
    }

    private static JSONObject getParentJSON(Parent parent) {
        JSONObject parentDetails = getUserJSON(parent);
        JSONArray camperArray = new JSONArray();
        for(Camper c: parent.getChildren()) {
            camperArray.add(getCamperJSON(c));
        }
        parentDetails.put(PARENT_CHILDREN, camperArray);
        parentDetails.put(PARENT_DISCOUNT, parent.getDiscount());
        parentDetails.put(PARENT_IS_RETURNING, parent.getIsReturning());
        return parentDetails;
    }

    private static JSONObject getCounselorJSON(Counselor counselor) {
        JSONObject counselorDetails = getUserJSON(counselor);
        JSONArray medsArray = new JSONArray();
        for(String med: counselor.getMeds()) {
            medsArray.add(med);
        }
        counselorDetails.put(COUNSELOR_MEDS, medsArray);
        JSONArray allergyArray = new JSONArray();
        for(String allergy: counselor.getAllergies()) {
            allergyArray.add(allergy);
        }
        counselorDetails.put(COUNSELOR_ALLERGIES, allergyArray);
        JSONArray contactArray = new JSONArray();
        //HashMap<Relationship, EmergencyContact> contacts = camper.getEmergencyContact();
        for(Relationship relation : counselor.getEmergenctContacts().keySet()) {
            JSONObject contactDetails = new JSONObject();
            EmergencyContact ec = counselor.getEmergenctContacts().get(relation);
            contactDetails.put(EMERGENCY_CONTACT_ID, ec.getUuid());
            contactDetails.put(EMERGENCY_CONTACT_FIRST_NAME, ec.getFirst());
            contactDetails.put(EMERGENCY_CONTACT_LAST_NAME, ec.getLast());
            contactDetails.put(EMERGENCY_CONTACT_PHONE_NUMBER, ec.getPhone());
            JSONObject contactRelationDetails = new JSONObject();
            // organizes contact with its relation
            contactRelationDetails.put(relation, contactDetails);
            contactArray.add(contactRelationDetails);
        }
        counselorDetails.put(COUNSELOR_EMERGENCY_CONTACTS, contactArray);
        JSONArray dietArray = new JSONArray();
        for(String restriction: counselor.getDietaryRestrictions()) {
            dietArray.add(restriction);
        }
        counselorDetails.put(COUNSELOR_DIETARY_RESTRICTIONS, dietArray);
        counselorDetails.put(COUNSELOR_T_SHIRT, counselor.getTShirt());
        counselorDetails.put(COUNSELOR_BIO, counselor.getBio());
        JSONArray notesArray = new JSONArray();
        for(String n: counselor.getNotes()) {
            notesArray.add(n);
        }
        counselorDetails.put(COUNSELOR_NOTES, notesArray);
        return counselorDetails;
    }

    private static JSONObject getCamperJSON(Camper camper) {
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
        //HashMap<Relationship, EmergencyContact> contacts = camper.getEmergencyContact();
        for(Relationship relation : camper.getEmergencyContact().keySet()) {
            JSONObject contactDetails = new JSONObject();
            EmergencyContact ec = camper.getEmergencyContact().get(relation);
            contactDetails.put(EMERGENCY_CONTACT_ID, ec.getUuid());
            contactDetails.put(EMERGENCY_CONTACT_FIRST_NAME, ec.getFirst());
            contactDetails.put(EMERGENCY_CONTACT_LAST_NAME, ec.getLast());
            contactDetails.put(EMERGENCY_CONTACT_PHONE_NUMBER, ec.getPhone());
            JSONObject contactRelationDetails = new JSONObject();
            // organizes contact with its relation
            contactRelationDetails.put(relation, contactDetails);
            contactArray.add(contactRelationDetails);
        }
        camperDetails.put(CAMPER_EMERGENCY_CONTACTS, contactArray);
        JSONArray dietArray = new JSONArray();
        for(String restriction: camper.getDietaryRestrictions()) {
            dietArray.add(restriction);
        }
        camperDetails.put(CAMPER_DIETARY_RESTRICTIONS, dietArray);
        camperDetails.put(CAMPER_T_SHIRT, camper.getTShirt());
        return camperDetails;
    }
}
