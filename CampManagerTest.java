import static org.junit.jupiter.api.Assertions.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class to test CampManager
 * @author Nathan Bickel and Luna Lamere
 */
public class CampManagerTest {

    @BeforeClass
	public static void oneTimeSetup() {
		
	}

    @AfterClass
	public static void oneTimeTearDown() {
		
	}
	
	@BeforeEach
	public static void setup() {
		Camp.getInstance();
	}
	
	@AfterEach
	public static void tearDown() {
		
	}

    public void testCreateUserValidDirector() {

    }
    
    public void testCreateUserDuplicateEmailCounselor() {

    }

    public void testCreateUserInvalidEmailParent() {

    }

    public void testCreateUserInvalidDateCounselor() {
        
    }

}