/**
 * @author Jackson
 */
import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Users.Counselor;
import Users.Director;
import Users.Parent;
import Users.User;


public class UserListTest {

    private UserList userList = UserList.getInstance();
    private ArrayList<User> newUsers = new ArrayList<User>();

    @BeforeAll
    public void oneTimeSetup() {
        System.out.println("This is running");
        String date_string = "26-09-1979";
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");      
        Date dirDate = new Date();
        try {
            dirDate = formatter.parse(date_string);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Map<String, String> securityQuestion = new HashMap<String, String>();
        securityQuestion.put("What was the name of your elementary school?", "Martinez Elementary School");
        securityQuestion.put("What was the make of your first car?", "1995 White Ford Ranger");
        Director d = new Director("dtest@gmail.com", "kj;lfdkadfsjl", "Dir", "Ector", "5555555555", dirDate, securityQuestion);
        Parent p = new Parent("ptest@gmail.com", "fkj;lsdflkja", "Par", "Ent", "5555555555", dirDate, securityQuestion);
        Counselor c = new Counselor("ctest@gmail.com", "kj;ladfsk", "Coun", "Selor", "5555555555", dirDate, securityQuestion);
        newUsers.add(d);
        newUsers.add(p);
        newUsers.add(c);
    }

    @AfterAll
    public void oneTimeTearDown() {

    }

    @BeforeEach
    public void setup() {
        UserList.clear();
    }

    @AfterEach
    public void tearDown() {

    }

    @Test
    public void addValidDirector() {
        Map<String, String> securityQuestion = new HashMap<String, String>();
        securityQuestion.put("What was the name of your elementary school?", "Martinez Elementary School");
        securityQuestion.put("What was the make of your first car?", "1995 White Ford Ranger");
        String date_string = "26-09-1979";
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");      
        Date birthday = new Date();
        try {
            birthday = formatter.parse(date_string);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Director d = new Director("dtest@gmail.com", "kj;lfdkadfsjl", "Dir", "Ector", "5555555555", birthday, securityQuestion);
        userList.addUser(d);
        assertTrue(userList.getAllUsers().contains(d), "Director has been added");
    }

    @Test
    public void addValidParent() {
        Map<String, String> securityQuestion = new HashMap<String, String>();
        securityQuestion.put("What was the name of your elementary school?", "Martinez Elementary School");
        securityQuestion.put("What was the make of your first car?", "1995 White Ford Ranger");
        String date_string = "26-09-1979";
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");      
        Date birthday = new Date();
        try {
            birthday = formatter.parse(date_string);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Parent p = new Parent("ptest@gmail.com", "kj;lfdkadfsjl", "Par", "Ent", "5555555555", birthday, securityQuestion);
        userList.addUser(p);
        assertTrue(userList.getAllUsers().contains(p), "Parent has been added");
    }

    @Test
    public void addValidCounselor() {
        Map<String, String> securityQuestion = new HashMap<String, String>();
        securityQuestion.put("What was the name of your elementary school?", "Martinez Elementary School");
        securityQuestion.put("What was the make of your first car?", "1995 White Ford Ranger");
        String date_string = "26-09-1979";
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");      
        Date birthday = new Date();
        try {
            birthday = formatter.parse(date_string);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Counselor c = new Counselor("ctest@gmail.com", "kj;lfdkadfsjl", "Coun", "Selor", "5555555555", birthday, securityQuestion);
        userList.addUser(c);
        assertTrue(userList.getAllUsers().contains(c), "Counselor has been added");
    }

    @Test
    public void addNullUser() {
        userList.addUser(null);
        assertFalse(userList.getAllUsers().contains(null), "Null users should not be added");
    }

    @Test
    public void addDuplicateUser() {
        Map<String, String> securityQuestion = new HashMap<String, String>();
        securityQuestion.put("What was the name of your elementary school?", "Martinez Elementary School");
        securityQuestion.put("What was the make of your first car?", "1995 White Ford Ranger");
        String date_string = "26-09-1979";
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");      
        Date birthday = new Date();
        try {
            birthday = formatter.parse(date_string);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        User d = new Director("dtest@gmail.com", "kj;lfdkadfsjl", "Dir", "Ector", "5555555555", birthday, securityQuestion);
        userList.addUser(d);
        userList.addUser(d);
        int count = 0;
        for(User u: userList.getAllUsers()) {
            if(u.equals(d)) {
                count++;
            }
        }
        assertTrue(count == 1, "Users should only be added once");
    }


}
