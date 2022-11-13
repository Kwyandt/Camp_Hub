/**
 * @author Katelyn Wyandt
 * 
 * Brief: Unit Testing of project, Data Writer Testing
 */

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.*;

import Users.Counselor;
import Users.Director;
import Users.Parent;
import Users.User;
public class DataWriterTest {
    //test users, camp, cabin
    private UserList userList = UserList.getInstance();
    private static ArrayList<User> newUsers = new ArrayList<User>();
    private Camp camp = Camp.getInstance();
    Map<String, String> questions = new HashMap<>();

    @BeforeEach
    public void setup () {
        //UserList.getInstance().getAllUsers().clear();
        UserList.clear();
        DataWriter.saveAllUsers((newUsers));
        DataWriter.saveCamp(camp);
    }

    @AfterEach
    public void tearDown () {
        //UserList.getInstance().getAllUsers().clear();
        UserList.clear();
        DataWriter.saveAllUsers((newUsers));
        DataWriter.saveCamp(camp);
    }

    @Test
    void testWriteZero() {
        //UserList.getInstance().getAllUsers().clear();
        UserList.clear();
        newUsers = DataReader.getAllUsers();
        assertEquals(0, newUsers.size());
    }

    @Test
    void testWriteOne() {
        //UserList.getInstance().getAllUsers().clear();
        UserList.clear();
        questions.put("question", "answer");
        questions.put("question2", "answer2");
        UserList.getInstance().addUser(new Counselor("counselor@email.com", "counselorPass", "aCounselor", "lastName", "000-000-0000", new Date(10-12-1999), questions));
        //newUsers.add(new Counselor("counselor@email.com", "counselorPass", "aCounselor", "lastName", "000-000-0000", new Date(10-12-1999), questions));
        DataWriter.saveAllUsers(newUsers);
        assertEquals("aCounselor", DataReader.getAllUsers().get(1).getFirstName());
    }
    
    @Test 
    void testWriteThree() {
        UserList.getInstance().getAllUsers().clear();
        questions.put("question", "answer");
        questions.put("question2", "answer2");
        newUsers.add(new Counselor("counselor@email.com", "counselorPass", "aCounselor", "lastName", "000-000-0000", new Date(10-12-1999), questions));
        //newUsers.add(new Director("director@email.com", "directorPass", "aCDirector", "lastName", "000-000-0000", new Date(10-12-1999), questions));
        newUsers.add(new Parent("parent@email.com", "parentPass", "aParent", "lastName", "000-000-0000", new Date(10-12-1999), questions));
        DataWriter.saveAllUsers(newUsers);
        assertEquals("aParent", DataReader.getAllUsers().get(1).getFirstName());
    }

    @Test
    void testWriteEmpty() {
        UserList.getInstance().getAllUsers().clear();
        questions.put("question", "answer");
        questions.put("question2", "answer2");
        newUsers.add(new Parent("","","","","",new Date(), questions));
        DataWriter.saveAllUsers(newUsers);
        assertEquals("", DataReader.getAllUsers().get(0).getFirstName());
    }   

    @Test 
    void testWriteNull () {
        UserList.getInstance().getAllUsers().clear();
        questions.put("question", "answer");
        questions.put("question2", "answer2");
        newUsers.add(new Parent(null, null,null,null,null, new Date(), questions));
        DataWriter.saveAllUsers(newUsers);
        assertEquals(null, DataReader.getAllUsers().get(0).getFirstName());
    }

}
