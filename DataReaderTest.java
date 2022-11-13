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
        newUsers.add(new Director("samsamuels@camptallrock.com", "ilovecamps", "Samantha", "Samuels", "(304) 474-4938", new Date(),questions));
        newUsers.add(new Parent ("email@email.com", "password", "John", "Smith", "123-456-7890", new Date(9-12-2000),questions));
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

    }

    @Test 
    void testReadUserZero() {

    }

    @Test 
    void testReadFirstName(){

    }

    @Test 
    void testReadFirstNameNull(){

    }

    @Test 
    void testGetCamp() {

    }

    @Test
    void testGetCampActivities() {

    }

    @Test 
    void testGetNullActivities() {

    }
}
