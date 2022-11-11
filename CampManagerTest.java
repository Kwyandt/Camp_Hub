import static org.junit.jupiter.api.Assertions.*;

import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Users.Counselor;
import Users.Director;
import Users.UserType;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;

/**
 * Class to test CampManager
 * @author Nathan Bickel and Luna Lamere
 */
public class CampManagerTest {
	
	private static Camp camp;
    private static CampManager campManager = new CampManager();
    
    @BeforeClass
	public static void oneTimeSetup() {
		Camp.clear();
	}

    @BeforeClass
	public static void setup() {
		camp = Camp.getInstance();
	}

    // Nathan
    @Test
    public void testCreateUserValidCounselor() {
        Map<String, String> securityQuestion = new HashMap<String, String>();
        securityQuestion.put("What street did you grow up on?", "Pinetree Dr");
        securityQuestion.put("What is your mother's maiden name?", "Ebersole");
        boolean validDirector = campManager.createUser(UserType.DIRECTOR, 
                                                       "jonathanconner@camptallrock.com", 
                                                       "ilovecamps", 
                                                       "Jonathan", 
                                                       "Conner", 
                                                       "(304) 474-4938",
                                                       getDate("04-Sep-1970"),
                                                       securityQuestion);
        assertEquals(validDirector, true, "Director should be valid");
    }
    
    // Nathan
    @Test
    public void testCreateUserDuplicateEmailDirector() {
        Map<String, String> securityQuestion = new HashMap<String, String>();
        securityQuestion.put("What street did you grow up on?", "Pinetree Dr");
        securityQuestion.put("What is your mother's maiden name?", "Ebersole");
        boolean validDirector = campManager.createUser(UserType.DIRECTOR, 
                                                       "jonathanconner@camptallrock.com", 
                                                       "ilovecamps", 
                                                       "John", 
                                                       "Conner", 
                                                       "(304) 474-4938",
                                                       getDate("04-Sep-1970"),
                                                       securityQuestion);
        assertEquals(validDirector, false, "Account with duplicate email should not be allowed");
    }

    // Nathan
    @Test
    public void testCreateUserNullPasswordParent() {
        Map<String, String> securityQuestion = new HashMap<String, String>();
        securityQuestion.put("What high school did you attend?", "Hillcrest");
        securityQuestion.put("What is your mother's maiden name?", "Flannigan");
        boolean invalidParent = campManager.createUser(UserType.PARENT, 
                                                        "sharonfrizzell@gmail.com", 
                                                        null, 
                                                        "Sharon", 
                                                        "Frizzell", 
                                                        "(864) 961-5191",
                                                        getDate("12-Mar-1983"),
                                                        securityQuestion);
        assertEquals(invalidParent, false, "Password cannot be null");
    }

    // Nathan
    @Test
    public void testCreateUserInvalidEmailParent() {
        Map<String, String> securityQuestion = new HashMap<String, String>();
        securityQuestion.put("What high school did you attend?", "Hillcrest");
        securityQuestion.put("What is your mother's maiden name?", "Flannigan");
        boolean invalidParent = campManager.createUser(UserType.PARENT, 
                                                       "sharon2frizzell", 
                                                       "ilovemykids", 
                                                       "Sharon2", 
                                                       "Frizzell", 
                                                       "(864) 961-5191",
                                                       getDate("12-Mar-1983"),
                                                       securityQuestion);
        assertEquals(invalidParent, false, "Email should be in the form 'handle@url.com'");
    }

    // Nathan
    @Test
    public void testCreateUserInvalidDateCounselor() {
        Map<String, String> securityQuestion = new HashMap<String, String>();
        securityQuestion.put("What was the name of your elementary school?", "Martinez Elementary School");
        securityQuestion.put("What was the make of your first car?", "1995 White Ford Ranger");
        boolean invalidParent = campManager.createUser(UserType.COUNSELOR, 
                                                       "brandthony0johnson@gmail.com", 
                                                       "67@pdpN%#o2y4FEx", 
                                                       "Brandthony0", 
                                                       "Johnson", 
                                                       "(325) 234-9567",
                                                       getDate("18-Jan-2023"),
                                                       securityQuestion);
        assertEquals(invalidParent, false, "Birthdate cannot be in the future");
    }

    // Nathan
    @Test
    public void testCreateUserInvalidPhoneCounselor() {
        Map<String, String> securityQuestion = new HashMap<String, String>();
        securityQuestion.put("What was the name of your elementary school?", "Martinez Elementary School");
        securityQuestion.put("What was the make of your first car?", "1995 White Ford Ranger");
        boolean invalidParent = campManager.createUser(UserType.COUNSELOR, 
                                                       "brandthony1johnson@gmail.com", 
                                                       "67@pdpN%#o2y4FEx", 
                                                       "Brandthony1", 
                                                       "Johnson", 
                                                       "phone",
                                                       getDate("18-Jan-2003"),
                                                       securityQuestion);
        assertEquals(invalidParent, false, "Invalid phone number");
    }

    // Luna
    @Test
    public void testValidLoginUser() {
        Director validDirector = this.createBasicUser();
        UserList.getInstance().addUser(validDirector);
        boolean testLogin = campManager.loginUser(validDirector.getEmail(),
            validDirector.getPassword());

        assertTrue(testLogin, "Invalid Login");
    }
    
    // Luna
    @Test
    public void testInvalidLoginUserEmail() {
        Director validDirector = this.createBasicUser();
        UserList.getInstance().addUser(validDirector);
        boolean testLogin = campManager.loginUser("wrongemail@wrong.com",
            validDirector.getPassword());

        assertFalse(testLogin, "Invalid Login");
    }

    // Luna
    @Test
    public void testInvalidLoginUserPassword() {
        Director validDirector = this.createBasicUser();
        UserList.getInstance().addUser(validDirector);
        boolean testLogin = campManager.loginUser(validDirector.getEmail(),
            "password");

        assertFalse(testLogin, "Invalid Login");
    }

    // Luna
    @Test
    public void testLogout() {
        Director validDirector = this.createBasicUser();
        UserList.getInstance().addUser(validDirector);
        .loginUser(validDirector.getEmail(),
            validDirector.getPassword());
        boolea
    }

    private Date getDate(String str) {
        try {
            return new SimpleDateFormat("dd-MMM-yyyy").parse(str);
        } catch (Exception e) {
            return null;
        } 
    }

    private Director createBasicUser() {
        Map<String, String> securityQuestion = new HashMap<String, String>();
        securityQuestion.put("What street did you grow up on?", "Pinetree Dr");
        securityQuestion.put("What is your mother's maiden name?", "Ebersole");
        Director validDirector = new Director( 
        "jonathanconner@camptallrock.com", 
        "ilovecamps", 
        "Jonathan", 
        "Conner", 
        "(304) 474-4938",
        getDate("04-Sep-1970"),
        securityQuestion);
        return validDirector;
    }

}