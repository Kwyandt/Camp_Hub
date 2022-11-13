/**
 * @author Katelyn Wyandt
 * 
 * Brief: Unit Testing of project, Data Writer Testing
 */

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.*;

import Users.User;
public class DataWriterTest {
    //test users, camp, cabin
    private UserList userList = UserList.getInstance();
    private static ArrayList<User> newUsers = new ArrayList<User>();
    private Camp camp = Camp.getInstance();

    @BeforeEach
    public void setup () {
        UserList.getInstance().getAllUsers().clear();
        DataWriter.saveAllUsers((newUsers));
        DataWriter.saveCamp(camp);
    }

    @AfterEach
    public void teadDown () {
        UserList.getInstance().getAllUsers().clear();
        DataWriter.saveAllUsers((newUsers));
        DataWriter.saveCamp(camp);
    }

    @Test
    void testWriteZero() {

    }

    @Test 
    void testWriteOne() {

    }

    @Test
    void testWriteFive() {

    }

    @Test
    void testWriteEmpty() {

    }

    @Test 
    void testWriteNull () {

    }

    @Test 
    void testWriteCabinRoster() {

    }

    @Test 
    void testWriteEmptyRoster() {

    }

    @Test
    void testWriteNullRoster() {

    }

    @Test 
    void testWriteCabinVitals() {

    }

    @Test 
    void testWriteEmptyVitals() {

    }

    @Test 
    void testWriteNullVitals() {

    }

    @Test 
    void testWriteCabinSchedule() {

    }

    @Test 
    void testWriteEmptySchedule() {

    }

    @Test 
    void testWriteNullSchedule() {

    }

}
