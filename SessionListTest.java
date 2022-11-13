/**
 * @author Jackson
 */
import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Users.*;

public class SessionListTest {

    private SessionList  sessionList = SessionList.getInstance();
    private static Session s;

    @BeforeAll
    public static void oneTimeSetup() {
       s = new Session("Nature",
       "Nature!",
       getDate("07-May-2023"),
       getDate("21-May-2023"),
       getDate("18-Jun-2023"),
       getDate("24-Jun-2023"));
    }

    @AfterAll
    public static void oneTimeTearDown() {

    }

    @BeforeEach
    public void setup() {
        SessionList.clear();
    }

    @AfterEach
    public void tearDown() {

    }

    private static Date getDate(String str) {
        try {
            return new SimpleDateFormat("dd-MMM-yyyy").parse(str);
        } catch (Exception e) {
            return null;
        } 
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

    public static Counselor createBasicCounselor() {
        Map<String, String> securityQuestion = new HashMap<String, String>();
        securityQuestion.put("What was the name of your elementary school?", "Martinez Elementary School");
        securityQuestion.put("What was the make of your first car?", "1995 White Ford Ranger");
        Counselor c = new Counselor("test@gmail.com", "kj;ladfss", "Coun", "Selor", "5555555555", getDate("01-Jan-2003"), securityQuestion);
        return c;
    }

    @Test
    public void TestAddSessionWithValidParameters() {
        sessionList.addSession("Nature",
        "Nature!",
        getDate("07-May-2023"),
        getDate("21-May-2023"),
        getDate("18-Jun-2023"),
        getDate("24-Jun-2023"));
        assertTrue(sessionList.getSession(getDate("18-Jun-2023")) != null, "Session was added");
    }

    @Test
    public void TestAddSessionWithNullParameters() {
        try {
            sessionList.addSession(null, null, null, null, null, null);
            assertFalse(sessionList.getSession(null) == null, "Cannot add session with null parameters");
        }
        catch(Exception e) {
            fail("Cannot add session with null parameters");
        }
    }

    @Test
    public void TestAddDuplicateSessionThroughParameters() {
        sessionList.addSession("Nature",
        "Nature!",
        getDate("29-Jun-2023"),
        getDate("22-Jun-2023"),
        getDate("07-Jul-2023"),
        getDate("13-Jul-2023"));
        Date start = getDate("07-Jul-2023");
        int count = 0;
        for(Session session: sessionList.getAllSessions()) {
            if(session.getStartDate().equals(start)) {
                count++;
            }
        }
        assertTrue(count == 1, "Two sessions should not have the same start date");
    }

    @Test
    public void TestAddValidSessionObject() {
        sessionList.addSession(s);
        assertTrue(sessionList.getAllSessions().contains(s), "Session was added succesfully");
    }

    @Test
    public void TestAddNullSessionObject() {
        sessionList.addSession(null);
        assertFalse(sessionList.getAllSessions().contains(null), "Null sessions should not be added");
    }

    @Test
    public void TestAddDuplicateSessionObject() {
        sessionList.addSession(s);
        sessionList.addSession(s);
        int count = 0;
        for(Session session: sessionList.getAllSessions()) {
            if(session.equals(s)) {
                count++;
            }
        }
        assertTrue(count <= 1, "Session objects should not be added twice");
    }

    @Test
    public void TestGetSessionByValidDate() {
        assertTrue(sessionList.getSession(getDate("07-Jul-2023")) != null, "Can access session through start date");
    }

    @Test
    public void TestInvalidStartDateGetSession() {
        assertTrue(sessionList.getSession(getDate("10-Jul-2003")) == null, "If no session has this start date it returns null");
    }

    @Test
    public void TestNullStartDateGetSession() {
        assertTrue(sessionList.getSession(getDate(null)) == null, "Null start date should not access a session");
    }

    @Test 
    public void TestGetSessionByValidIndex() {
        sessionList.addSession(s);
        assertTrue(sessionList.getSession(0) != null, "A session should be in SessionList");
    }

    @Test
    public void TestGetSessionByInvalidIndex() {
        try {
            assertTrue(sessionList.getSession(-1) == null, "This should not return a session");
        }
        catch(IndexOutOfBoundsException e) {
            fail("Unhandled index out of bounds exception");
        }
    }

    @Test
    public void TestEditExistingSession() {
        Session edited = sessionList.getSession(0);
        edited.setDescription("New description");
        Date start = edited.getStartDate();
        sessionList.editSession(edited);
        assertTrue(sessionList.getSession(start).getDescription().equals("New description"), "Edit was successful");
    }

    @Test
    public void TestEditAbsentSession() {
        assertFalse(sessionList.editSession(s), "Should return false if session is not added");
    }

    @Test
    public void TestEditNullSession() {
        try {
            assertFalse(sessionList.editSession(null), "Should return false if trying to edit null session");
        }
        catch(Exception e) {
            fail("Unhandled null pointer");
        }
    }

    @Test
    public void TestEditValidSessionAtValidIndex() {
        sessionList.editSession(0, s);
        assertTrue(sessionList.getSession(0).equals(s), "Edit was successful");
    }

    @Test
    public void TestEditValidSessionAtInvalidIndex() {
        assertFalse(sessionList.editSession(-1, s), "Should return false because index is out of bounds");
    }

    @Test
    public void TestEditNullSessionAtValidIndex() {
        assertFalse(sessionList.editSession(0, null), "Should return false because session is null");
    }

    @Test
    public void TestEditNullSessionAtInvalidIndex() {
        assertFalse(sessionList.editSession(-1, null), "Should return false because index is out of bounds and session is null");
    }

    @Test
    public void TestRemovePresentSessionByDate() {
        Date start = sessionList.getSession(0).getStartDate();
        sessionList.removeSession(start);
        assertTrue(sessionList.getSession(start) == null, "Should return null as session should be removed");
    }

    @Test
    public void TestRemoveAbsentSessionByDate() {
        assertFalse(sessionList.removeSession(s.getStartDate()), "Should be false as no session exists with that start date");
    }

    @Test
    public void TestRemoveSessionByNullDate() {
        assertFalse(sessionList.removeSession(null), "Should be false as no session has a null start date");
    }

    @Test
    public void TestRemoveSessionAtValidIndex() {
        Date start = sessionList.getSession(0).getStartDate();
        sessionList.removeSession(0);
        assertTrue(sessionList.getSession(start) == null, "Should return null as session should be removed");
    }

    @Test
    public void TestRemoveSessionAtInvalidIndex() {
        assertFalse(sessionList.removeSession(-551), "Should be false as this index is out of bounds");
    }

    @Test
    public void TestGetPresentCamperSessions() {
        Camper c = createKBasicCampers(1)[0];
        int count = 0;
        for(Session session: sessionList.getAllSessions()) {
            session.addCamper(c);
            sessionList.editSession(session);
            count++;
        }
        ArrayList<Session> cSessions = sessionList.getCamperSessions(c);
        assertEquals(cSessions.size(), count,  "Camper is in count number of sessions"); 
    }

    @Test
    public void TestGetAbsentCamperSessions() {
        Camper c = createKBasicCampers(1)[0];
        assertTrue(sessionList.getCamperSessions(c).size() == 0, "Camper is not in any sessions");
    }

    @Test
    public void TestGetNullCamperSessions() {
        assertTrue(sessionList.getCamperSessions(null).size() == 0, "Null campers are not in any sessions");
    }

    @Test
    public void TestGetPresentCounselorSessions() {
       Counselor c = createBasicCounselor();
       Session session = sessionList.getSession(0);
       session.addCounselor(c);
       sessionList.editSession(session);
       ArrayList<Session> cSessions = sessionList.getCounselorSessions(c);
       assertEquals(cSessions.size(),1, "Counselor is present in one session");
    }

    @Test
    public void TestGetAbsentCounselorSessions() {
        Counselor c = createBasicCounselor();
        assertEquals(sessionList.getCounselorSessions(c).size(), 0, "Counselor is not in any sessions");
    }

    @Test
    public void TestGetNullCounselorSessions() {
        try {
            assertEquals(sessionList.getCounselorSessions(null).size(), 0, "Null counselor is not in any session");
        }
        catch(Exception e) {
            fail("Unhandled null pointer");
        }
        
    }
}
