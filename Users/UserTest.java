package Users;
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



public class UserTest {

    private static User u;

    private static Date getDate(String str) {
        try {
            return new SimpleDateFormat("dd-MMM-yyyy").parse(str);
        } catch (Exception e) {
            return null;
        } 
    }

    @BeforeAll
    public static void oneTimeSetup() {
        Map<String, String> securityQuestion = new HashMap<String, String>();
        securityQuestion.put("What was the name of your elementary school?", "Martinez Elementary School");
        securityQuestion.put("What was the make of your first car?", "1995 White Ford Ranger");
        u = new Director("dtest@gmail.com", "password", "Dir", "Ector", "5555555555", getDate("01-Jan-1970"), securityQuestion);
    }

    @AfterAll
    public static void oneTimeTearDown() {

    }

    @BeforeEach
    public void setup() {

    }

    @AfterEach
    public void tearDown() {

    }

    @Test
    public void TestChangePasswordCorrectly() {
        u.changePassword("password", "password1");
        assertEquals(u.getPassword(), "password1", "Password was changed");
    }

    @Test
    public void TestChangePasswordWithWrongCase() {
        u.changePassword("PASSWORD", "notAllowed");
        assertEquals(u.getPassword(), "password", "The password should not be changed");
    }

    @Test
    public void TestChangePasswordWithWrongPassword() {
        u.changePassword("wrong", "notAllowed");
        assertEquals(u.getPassword(), "password", "The password should not be changed");
    }

    @Test
    public void TestChangePasswordWithNullOldPassword() {
        try {
            u.changePassword(null, "notAllowed");
            assertEquals(u.getPassword(), "password", "The password should not be changed");
        }
        catch(NullPointerException e) {
            fail("Unhandled null pointer");
        }
    }

    @Test
    public void TestChangePasswordWithNullNewPassword() {
        u.changePassword("password", null);
        assertEquals(u.getPassword(), "password", "The password should not be changed");
    }
}
