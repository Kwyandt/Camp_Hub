package Users;
/**
 * @author Jackson
 */
import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    private static User createBasicUser() {
        Map<String, String> securityQuestion = new HashMap<String, String>();
        securityQuestion.put("What street did you grow up on?", "Pinetree Dr");
        securityQuestion.put("What is your mother's maiden name?", "Ebersole");
        Director validDirector = new Director( 
        "jonathanconner@camptallrock.com", 
        "password", 
        "Jonathan", 
        "Conner", 
        "(304) 474-4938",
        getDate("04-Sep-1970"),
        securityQuestion);
        return validDirector;
    }

    @BeforeAll
    public static void oneTimeSetup() {

    }

    @AfterAll
    public static void oneTimeTearDown() {

    }

    @BeforeEach
    public void setup() {
        u = createBasicUser();
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
        System.out.println(u.getPassword());
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
