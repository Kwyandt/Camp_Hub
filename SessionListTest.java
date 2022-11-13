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

    @Test
    public void addSessionWithValidParameters() {
        sessionList.addSession("Nature",
        "Nature!",
        getDate("07-May-2023"),
        getDate("21-May-2023"),
        getDate("18-Jun-2023"),
        getDate("24-Jun-2023"));
        assertTrue(sessionList.getSession(getDate("18-Jun-2023")) != null, "Session was added");
    }

    @Test
    public void addSessionWithNullParameters() {
        try {
            sessionList.addSession(null, null, null, null, null, null);
            assertFalse(sessionList.getSession(null) == null, "Cannot add session with null parameters");
        }
        catch(Exception e) {
            fail("Cannot add session with null parameters");
        }
    }

    @Test
    public void addDuplicateSessionThroughParameters() {
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
    public void addValidSessionObject() {
        sessionList.addSession(s);
        assertTrue(sessionList.getAllSessions().contains(s), "Session was added succesfully");
    }

    @Test
    public void addNullSessionObject() {
        sessionList.addSession(null);
        assertFalse(sessionList.getAllSessions().contains(null), "Null sessions should not be added");
    }

    @Test
    public void addDuplicateSessionObject() {
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
}
