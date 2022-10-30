
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import Users.Counselor;
import Users.EmergencyContact;
import Users.Relationship;
import Users.User;

/**
 * @author Hailey (Luna) Lamere
 * Main method class to test various logic
 */

public class Main {
    public static void main(String[] args) {
        ArrayList<User> users = DataReader.getAllUsers();

        int director = 0;
        User user0 = users.get(director);

        System.out.println("~~~~PRINTING DIRECTOR~~~~");
        System.out.println(user0.getUserType() + "\n"
        + user0.getUuid() + "\n"
        + user0.getEmail() + "\n"
        + user0.getPhone() + "\n"
        + user0.getPassword() + "\n"
        + user0.getFirstName() + "\n"
        + user0.getLastName() + "\n"
        + user0.getSecurityQuestions());
        System.out.println("~~~~CHANGING MUTABLE DATA FIELDS~~~~");
        user0.changePassword("ilovecamps", "ihatecamps");
        user0.setEmail("thisemail@email.com");
        user0.setPhone("555-555-5555");

        System.out.println(user0.getUserType() + "\n"
        + user0.getUuid() + "\n"
        + user0.getEmail() + "\n"
        + user0.getPhone() + "\n"
        + user0.getPassword() + "\n"
        + user0.getFirstName() + "\n"
        + user0.getLastName() + "\n"
        + user0.getSecurityQuestions());

        Map<String, String> testMap = new HashMap<>();
        Map<Relationship, EmergencyContact> testContact = new HashMap<>();
        Date testDate = new Date();
        UUID uuid = UUID.randomUUID();
        ArrayList<String> testMeds = new ArrayList<>();
        ArrayList<String> testAllergies = new ArrayList<>();
        ArrayList<String> testDiet = new ArrayList<>();
        ArrayList<String> testNotes = new ArrayList<>();
        EmergencyContact ec = new EmergencyContact("testEC", "testLastEC", "984-123-3451", "testECLocation");
        testMeds.add("Allergy medicine");
        testAllergies.add("Grass");
        testMap.put("Favorite animal?", "cat");
        testContact.put(Relationship.DOCTOR, ec);
        testDiet.add("peanuts");
        testNotes.add("this is a notes");

        System.out.println("~~~~CREATING NEW COUNSELOR AND PRINTING~~~~");
        Counselor user1 = new Counselor(uuid, "testemail.@test.com", "000-000-1234", "testpass", "testFirst", "testLast",  testDate, testMap, testMeds,
        testAllergies, testContact, testDiet, "tee shirt test", "this is a bio", testNotes);

        System.out.println(user1.getUserType() + "\n"
        + user1.getUuid() + "\n"
        + user1.getEmail() + "\n"
        + user1.getPhone() + "\n"
        + user1.getPassword() + "\n"
        + user1.getFirstName() + "\n"
        + user1.getLastName() + "\n"
        + user1.getSecurityQuestions() + "\n"
        + user1.getMeds() + "\n"
        + user1.getAllergies() + "\n"
        + user1.getEmergenctContacts() + "\n"
        + user1.getDietaryRestrictions() + "\n"
        + user1.getTShirt() + "\n"
        + user1.getBio() + "\n"
        + user1.getNotes());

        System.out.println("~~~~CAMP TESTING~~~~");
        Camp camp = DataReader.getCamp();
        System.out.println(camp.getName() + "\n"
        + camp.getFAQs() + "\n"
        + camp.getSecurityQuestions() + "\n"
        + camp.getActivities() + "\n"
        + camp.getOfficePhone() + "\n"
        + camp.getPackingList() + "\n");
        System.out.println("~~~~END MASSIVE CAMP PRINTOUT~~~~");

        System.out.println("~~~~CAMP ADD/REMOVE TEST~~~~");
        Activity activity = new Activity("test activity", "test description", "test location");
        camp.addActivity(activity);
        for(int i = 0; i < camp.getActivities().size(); i++) {
            System.out.println(camp.getActivities().get(i));
        }
        System.out.println("\n~~~~CAMP TEST ACTIVITY ADDED, REMOVING...~~~~\n");
        camp.removeActivity(camp.getActivities().size()-1);
        for(int i = 0; i < camp.getActivities().size(); i++) {
            System.out.println(camp.getActivities().get(i));
        }
    }

}
