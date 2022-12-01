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
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Users.Camper;
import Users.Counselor;
import Users.Director;
import Users.Parent;
import Users.User;
import Users.UserType;


public class UserListTest {

    private UserList userList = UserList.getInstance();
    private static ArrayList<User> newUsers = new ArrayList<User>();

    @BeforeAll
    public static void oneTimeSetup() {
        String date_string = "26-09-1979";
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");      
        Date dirDate = new Date();
        Date campDate = new Date();
        try {
            dirDate = formatter.parse(date_string);
            campDate = formatter.parse("31-10-2010");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Map<String, String> securityQuestion = new HashMap<String, String>();
        securityQuestion.put("What was the name of your elementary school?", "Martinez Elementary School");
        securityQuestion.put("What was the make of your first car?", "1995 White Ford Ranger");
        Director d = new Director("dtest@gmail.com", "kj;lfdkadfsjl", "Dir", "Ector", "5555555555", dirDate, securityQuestion);
        Parent p = new Parent("ptest@gmail.com", "fkj;lsdflkja", "Par", "Ent", "5555555555", dirDate, securityQuestion);
        Counselor c = new Counselor("ctest@gmail.com", "kj;ladfsk", "Coun", "Selor", "5555555555", dirDate, securityQuestion);
        Camper k = new Camper("Cam", "Per", campDate, null, "M");
        p.createCamper(k);
        newUsers.add(d);
        newUsers.add(p);
        newUsers.add(c);
    }

    @AfterAll
    public static void oneTimeTearDown() {

    }

    @BeforeEach
    public void setup() {
        UserList.clear();
    }

    @AfterEach
    public void tearDown() {

    }

    @Test
    public void TestAddValidDirector() {
        Director d = (Director)newUsers.get(0);
        userList.addUser(d);
        assertTrue(userList.getAllUsers().contains(d), "Director has been added");
    }

    @Test
    public void TestAddValidParent() {
        Parent p = (Parent)newUsers.get(1);
        userList.addUser(p);
        assertTrue(userList.getAllUsers().contains(p), "Parent has been added");
    }

    @Test
    public void TestAddValidCounselor() {
        Counselor c = (Counselor)newUsers.get(2);
        userList.addUser(c);
        assertTrue(userList.getAllUsers().contains(c), "Counselor has been added");
    }

    @Test
    public void TestAddNullUser() {
        userList.addUser(null);
        assertFalse(userList.getAllUsers().contains(null), "Null users should not be added");
    }

    @Test
    public void TestAddDuplicateUser() {
        User d = newUsers.get(0);
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

    @Test
    public void TestGetValidEmail() {
        userList.addUser(newUsers.get(1));
        User u = userList.getUser(newUsers.get(1).getEmail());
        assertTrue(u != null, "User was successfully accessed by email");
    }

    @Test
    public void TestGetNonexistentEmail() {
        User u = userList.getUser("thisemaildoesnotexist@gmail.com");
        assertTrue(u == null, "No user exists with this email");
    }

    @Test
    public void TestGetWithNullEmail() {
        User u = userList.getUser(null);
        assertTrue(u == null, "No user should have a null email");
    }

    @Test
    public void TestGetValidID() {
        userList.addUser(newUsers.get(1));
        User u = userList.getUserByUUID(newUsers.get(1).getUuid());
        assertTrue(u != null, "User was successfully accessed by UUID");
    }

    @Test
    public void TestGetNonexistentID() {
        User u = userList.getUserByUUID(UUID.fromString("b4ffe5eb-d6b3-49b3-8c71-049b37412d05"));
        assertTrue(u == null, "No user exists with this UUID");
    }

    @Test
    public void TestGetWithNullID() {
        User u = userList.getUser(null);
        assertTrue(u == null, "No user should have a null UUID");
    }

    @Test
    public void TestEditUserValid() {
        User u = userList.getAllUsers().get(0);
        u.setPhone("7777777777");
        userList.editUser(u);
        UUID id = u.getUuid();
        User edited = userList.getUserByUUID(id);
        assertTrue(u.equals(edited), "Edited user successfully");
    }

    @Test
    public void TestEditAbsentUser() {
        User u = newUsers.get(0);
        boolean wasEdited = userList.editUser(u);
        assertFalse(wasEdited, "No user exists so editing cannot happen");
    }

    @Test
    public void TestEditNullUser() {
        try {
            assertFalse(userList.editUser(null), "Cannot edit a null user");
        }
        catch(NullPointerException e) {
            fail("Null pointer exception");
        }
    }

    @Test
    public void TestGetAllDirectors() {
        int count = 0;
        for(User u: userList.getAllUsers()) {
            if(u.getUserType() == UserType.DIRECTOR) {
                count++;
            }
        }
        assertEquals(count, userList.getUsersOfType(UserType.DIRECTOR).size(), "Count should equal the amount of directors");
    }

    @Test
    public void TestGetAllParents() {
        int count = 0;
        for(User u: userList.getAllUsers()) {
            if(u.getUserType() == UserType.PARENT) {
                count++;
            }
        }
        assertEquals(count, userList.getUsersOfType(UserType.PARENT).size(), "Count should equal the amount of parents");
    }

    @Test
    public void TestGetAllCounselors() {
        int count = 0;
        for(User u: userList.getAllUsers()) {
            if(u.getUserType() == UserType.COUNSELOR) {
                count++;
            }
        }
        assertEquals(count, userList.getUsersOfType(UserType.COUNSELOR).size(), "Count should equal the amount of counselors");
    }

    @Test
    public void TestAccessCamperByValidUUID() {
        Parent p = (Parent)newUsers.get(1);
        Camper child = p.getChildren().get(0);
        userList.addUser(p);
        assertEquals(userList.getCamperByUUID(child.getUuid()), child, "Camper should be accessible through UUID");
    }

    @Test
    public void TestAccessCamperByNonexistentUUID() {
        // guarantees a camper exists
        Parent p = (Parent)newUsers.get(1);
        userList.addUser(p);
        assertEquals(userList.getCamperByUUID(UUID.randomUUID()), null, "With a random UUID there should not be a camper with that UUID");
    }
}
