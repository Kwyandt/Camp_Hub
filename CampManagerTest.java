import static org.junit.jupiter.api.Assertions.*;

import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Users.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

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
        UserList.getInstance().addUser(createBasicInitialUser());
        campManager.loginUser("jonathanconner0@camptallrock.com","ilovecamps");
	}

    // Nathan
    @Test
    public void testCreateUserValidCounselor() {
        Map<String, String> securityQuestion = new HashMap<String, String>();
        securityQuestion.put("What was the name of your elementary school?", "Martinez Elementary School");
        securityQuestion.put("What was the make of your first car?", "1995 White Ford Ranger");
        boolean validCounselor = campManager.createUser(UserType.COUNSELOR, 
                                                       "brandthony0johnson@gmail.com", 
                                                       "67@pdpN%#o2y4FEx", 
                                                       "Brandthony0", 
                                                       "Johnson", 
                                                       "(325) 234-9567",
                                                       getDate("18-Jan-2003"),
                                                       securityQuestion);
        assertTrue(validCounselor, "Birthdate cannot be in the future");
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
        assertFalse(validDirector, "Account with duplicate email should not be allowed");
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
        assertFalse(invalidParent, "Password cannot be null");
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
        assertFalse(invalidParent, "Email should be in the form 'handle@url.com'");
    }

    // Nathan
    @Test
    public void testCreateUserInvalidDateCounselor() {
        Map<String, String> securityQuestion = new HashMap<String, String>();
        securityQuestion.put("What was the name of your elementary school?", "Martinez Elementary School");
        securityQuestion.put("What was the make of your first car?", "1995 White Ford Ranger");
        boolean invalidParent = campManager.createUser(UserType.COUNSELOR, 
                                                       "brandthony1johnson@gmail.com", 
                                                       "67@pdpN%#o2y4FEx", 
                                                       "Brandthony1", 
                                                       "Johnson", 
                                                       "(325) 234-9567",
                                                       getDate("18-Jan-2023"),
                                                       securityQuestion);
        assertFalse(invalidParent, "Birthdate cannot be in the future");
    }

    // Nathan
    @Test
    public void testCreateUserInvalidPhoneCounselor() {
        Map<String, String> securityQuestion = new HashMap<String, String>();
        securityQuestion.put("What was the name of your elementary school?", "Martinez Elementary School");
        securityQuestion.put("What was the make of your first car?", "1995 White Ford Ranger");
        boolean invalidParent = campManager.createUser(UserType.COUNSELOR, 
                                                       "brandthony2johnson@gmail.com", 
                                                       "67@pdpN%#o2y4FEx", 
                                                       "Brandthony2", 
                                                       "Johnson", 
                                                       "phone",
                                                       getDate("18-Jan-2003"),
                                                       securityQuestion);
        assertFalse(invalidParent, "Invalid phone number");
    }

    // Luna
    @Test
    public void testValidLoginUser() {
        Director validDirector = createBasicUser();
        UserList.getInstance().addUser(validDirector);
        boolean testLogin = campManager.loginUser(validDirector.getEmail(),
            validDirector.getPassword());

        assertTrue(testLogin, "Invalid Login");
    }
    
    // Luna
    @Test
    public void testInvalidLoginUserEmail() {
        Director validDirector = createBasicUser();
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
        campManager.loginUser(validDirector.getEmail(),
            validDirector.getPassword());
        boolean logoutTest = campManager.logoutUser();
        assertTrue(logoutTest, "No logout");
    }

    // Luna
    @Test
    public void testValidAddActivity() {
        Director validDirector = this.createBasicUser();
        UserList.getInstance().addUser(validDirector);
        campManager.loginUser(validDirector.getEmail(), validDirector.getPassword());
        boolean test = campManager.addActivity("TestName", "TestDesc", "TestLoc");
        assertTrue(test, "Invalid activity");
    }

    // Luna
    @Test
    public void testValidAddActivityInvalidPermission() {
        Map<String, String> securityQuestion = new HashMap<String, String>();
        securityQuestion.put("What street did you grow up on?", "Pinetree Dr");
        securityQuestion.put("What is your mother's maiden name?", "Ebersole");
        Parent parent = new Parent( 
            "parentTest@camptallrock.com", 
            "testPass", 
            "Parent", 
            "Test", 
            "(304) 474-4938",
            getDate("04-Sep-1970"),
            securityQuestion);
        UserList.getInstance().addUser(parent);
        campManager.loginUser(parent.getEmail(), parent.getPassword());
        boolean test = campManager.addActivity("TestName", "TestDesc", "TestLoc");
        assertFalse(test);
    }

    // Nathan
    @Test
    public void testSetEmailValid0() {
        String valid = "jonathan@camptallrock.com";
        assertTrue(campManager.setEmail(valid), "This is a valid email format");
    }

    // Nathan
    @Test
    public void testSetEmailValid1() {
        String valid = "test@test.test";
        assertTrue(campManager.setEmail(valid), "This is a valid email format");
    }

    // Nathan
    @Test
    public void testSetEmailNull() {
        String invalid = null;
        assertFalse(campManager.setEmail(invalid), "Email cannot be null");
    }

    // Nathan
    @Test
    public void testSetEmailEmpty() {
        String invalid = "";
        assertFalse(campManager.setEmail(invalid), "Email cannot be empty");
    }

    // Nathan
    @Test
    public void testSetEmailWrongFormat0() {
        String invalid = "jonathanconner";
        assertFalse(campManager.setEmail(invalid), "Email needs a domain");
    }

    // Nathan
    @Test
    public void testSetEmailWrongFormat1() {
        String invalid = "jonathanconner@camptallrock";
        assertFalse(campManager.setEmail(invalid), "Email needs a domain url");
    }

    // Nathan
    @Test
    public void testSetEmailDuplicate() {
        UserList.getInstance().addUser(createBasicParent());
        String duplicate = UserList.getInstance().getUsersOfType(UserType.PARENT).get(0).getEmail();
        assertFalse(campManager.setEmail(duplicate), "This email is already in the system");
    }

    // Nathan
    @Test
    public void testSetPhoneValid0() {
        String valid = "(555) 555-5555";
        assertTrue(campManager.setPhone(valid), "This is a valid phone format");
    }

    // Nathan
    @Test
    public void testSetPhoneValid1() {
        String valid = "5555555555";
        assertTrue(campManager.setPhone(valid), "This is a valid phone format");
    }

    // Nathan
    @Test
    public void testSetPhoneValid2() {
        String valid = "555-555-5555";
        assertTrue(campManager.setPhone(valid), "This is a valid phone format");
    }

    // Nathan
    @Test
    public void testSetPhoneNull() {
        String invalid = null;
        assertFalse(campManager.setPhone(invalid), "Number cannot be null");
    }

    // Nathan
    @Test
    public void testSetPhoneEmpty() {
        String invalid = "";
        assertFalse(campManager.setPhone(invalid), "Number cannot be empty");
    }

    // Nathan
    @Test
    public void testSetPhoneNonNumericCharacters() {
        String invalid = "askjdfh9€htgUIDNVG09h2€)G";
        assertFalse(campManager.setPhone(invalid), "Phone number can't have letters");
    }

    // Nathan
    @Test
    public void testSetPhoneNonNumericCharacters0() {
        String invalid = "askjdfh9€htgUIDNVG09h2€)G";
        assertFalse(campManager.setPhone(invalid), "Phone number can't have letters");
    }

    // Nathan
    @Test
    public void testSetPhoneNonNumericCharacters1() {
        String invalid = "5555555555LoLtHiSiSnOtAnUmBeRyOuCaNdIaLoNaFlIpPhOnE";
        assertFalse(campManager.setPhone(invalid), "Phone numbers can't have letters");
    }
    
    // Nathan
    @Test
    public void testGetPricingNoChildren() {
        Parent parent = createBasicParent();
        Session session = createBasicSession();
        session.setPrice(1000);
        assertEquals(campManager.getPricing(session, parent), 1000.0, "Parent with no children should pay full price");
    }

    // Nathan
    @Test
    public void testGetPricingOneReturningChild() {
        Parent parent = createBasicParent();
        Session session = createBasicSession();
        parent.setIsReturning(true);
        parent.createCamper(createKBasicCampers(1)[0]);
        session.setPrice(1000);
        assertEquals(campManager.getPricing(session, parent), 900.0, "Parent with returning child should get 10% discount");
    }

    // Nathan
    @Test
    public void testGetPricing100ReturningChildren() {
        Parent parent = createBasicParent();
        Session session = createBasicSession();
        parent.setIsReturning(true);
        Camper[] campers = createKBasicCampers(100);
        for (int i = 0; i < 100; i++)
            parent.createCamper(campers[i]);
        session.setPrice(1000);
        assertEquals(campManager.getPricing(session, parent), 800.0, "Discount should max out at 20%");
    }

    // Luna
    @Test
    public void testAddValidCamper() {
        Parent parent = createBasicParent();
        UserList.getInstance().addUser(parent);
        Date date = new Date();
        campManager.loginUser(parent.getEmail(), parent.getPassword());
        String[] guard = {"guardian", "second guardian"};
        String[] doc = {"doc", "new doc"};
        String[] dent = {"dentist", "new dentist"};
        boolean test = campManager.addCamper("testFirst Child",
        "testLast Child",
        date,
        guard,
        doc,
        dent,
        "blue test tee");
        assertTrue(test, "incorrect login");
    }

    @Test
    public void testAddInvalidCamper() {
        Parent parent = createBasicParent();
        UserList.getInstance().addUser(parent);
        Date date = new Date();
        String[] guard = {"guardian", "second guardian"};
        String[] doc = {"doc", "new doc"};
        String[] dent = {"dentist", "new dentist"};
        boolean test = campManager.addCamper("testFirst Child",
        "testLast Child",
        date,
        guard,
        doc,
        dent,
        "blue test tee");
        assertTrue(test, "incorrect login");
    }

    private static Date getDate(String str) {
        try {
            return new SimpleDateFormat("dd-MMM-yyyy").parse(str);
        } catch (Exception e) {
            return null;
        } 
    }

    private static Director createBasicUser() {
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

    private static Director createBasicInitialUser() {
        Map<String, String> securityQuestion = new HashMap<String, String>();
        securityQuestion.put("What street did you grow up on?", "Pinetree Dr");
        securityQuestion.put("What is your mother's maiden name?", "Ebersole");
        Director validDirector = new Director( 
        "jonathanconner0@camptallrock.com", 
        "ilovecamps", 
        "Jonathan0", 
        "Conner", 
        "(304) 474-4938",
        getDate("04-Sep-1970"),
        securityQuestion);
        return validDirector;
    }

    private static Parent createBasicParent() {
        Map<String, String> securityQuestion = new HashMap<String, String>();
        securityQuestion.put("What high school did you attend?", "Hillcrest");
        securityQuestion.put("What is your mother's maiden name?", "Flannigan");
        return new Parent("sharonfrizzell@gmail.com",
                          "ilovemykids",
                          "Sharon",
                          "Frizzell",
                          "(864) 961-5191",
                          getDate("12-Mar-1983"),
                          securityQuestion);           
    }

    private static Camper[] createKBasicCampers(int k) {
        Camper[] camperList = new Camper[k];
        for (int i = 0; i < k; i++) {
            Map<Relationship,EmergencyContact> emergencyContacts = new HashMap<Relationship,EmergencyContact>();
            emergencyContacts.put(Relationship.DENTIST, new EmergencyContact("Dentist", 
                                                                             Integer.toString(k), 
                                                                             "(555) 555-5555", 
                                                                             "123 Heh Dr"));
            emergencyContacts.put(Relationship.DOCTOR, new EmergencyContact("Doctor", 
                                                                             Integer.toString(k), 
                                                                             "(555) 555-5555", 
                                                                             "123 Heh Dr"));
            emergencyContacts.put(Relationship.GUARDIAN, new EmergencyContact("Guardian", 
                                                                             Integer.toString(k), 
                                                                             "(555) 555-5555", 
                                                                             "123 Heh Dr"));                                                                
            camperList[i] = new Camper("Camper",
                                      Integer.toString(k),
                                      getDate("01-Jan-2010"),
                                      emergencyContacts,
                                      "S");
        }
        return camperList;
            
    }

    private static Session createBasicSession() {
        return new Session("Nature",
                           "Nature!",
                           getDate("07-May-2023"),
                           getDate("21-May-2023"),
                           getDate("18-Jun-2023"),
                           getDate("24-Jun-2023"));
    }

}