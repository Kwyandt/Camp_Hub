/**
 * @author Katelyn Wyandt
 * 
 * Brief: Unit testing of project, data reader testing
 */

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.*;

import Users.Director;
import Users.Parent;
import Users.User;

public class DataReaderTest{
    private UserList userList = UserList.getInstance();
    private static ArrayList<User> newUsers = new ArrayList<User>();
    Map<String, String> questions = new HashMap<>();
    private Camp camp = Camp.getInstance();


    @BeforeEach
    public void setup () {
        newUsers.clear();
        questions.put("question", "answer");
        questions.put("question2", "answer2");
        newUsers.add(new Parent ("email@email.com", "password", "John", "Smith", "123-456-7890", new Date(9-12-2000),questions));
        newUsers.add(new Parent ("email2@email.com", "password2", "John2", "Smith2", "123-456-7892", new Date(9-12-2002),questions));
        newUsers.add(new Parent ("email2@email.com", "password2", null, null, "123-456-7892", new Date(9-12-2002),questions));
        DataWriter.saveAllUsers(newUsers);
        DataWriter.saveCamp(camp);
    }

    @AfterEach
    public void tearDown () {
        UserList.getInstance().getAllUsers().clear();
        DataWriter.saveAllUsers(newUsers);
        DataWriter.saveCamp(camp);
    }

    @Test
    void testReadUserSize() {
        newUsers = DataReader.getAllUsers();
        assertEquals(3, newUsers.size());
    }

    @Test 
    void testReadUserZero() {
        //UserList.getInstance().getAllUsers().clear();
        newUsers.clear();
        DataWriter.saveAllUsers(newUsers);
        newUsers = DataReader.getAllUsers();
        assertEquals(0, newUsers.size());
    }

    @Test 
    void testReadFirstName(){
        newUsers = DataReader.getAllUsers();
        assertEquals("John", newUsers.get(0).getFirstName());
    }

    @Test 
    void testReadFirstNameNull(){
        newUsers = DataReader.getAllUsers();
        assertEquals(null, newUsers.get(2).getFirstName());
    }

    @Test 
    void testGetCamp() {
        assertEquals("Camp Tall Rock", Camp.getInstance().getName());
    }

    @Test
    void testGetCampActivities() {
        /* ArrayList<Activity> campActivities = new ArrayList<Activity>();
        campActivities.add(new Activity("Badmiton", "description", "location")); */
        Camp.getInstance().getActivities().clear();
        Camp.getInstance().addActivity(new Activity("Badmitton", "description", "location"));
        assertEquals("Badmitton", Camp.getInstance().getActivities().get(0).getName());
    }

    @Test 
    void testGetNullActivities() {
        Camp.getInstance().getActivities().clear();
        Camp.getInstance().addActivity(new Activity(null, null, null));
        assertEquals(null, Camp.getInstance().getActivities().get(0).getName());
    }
}
