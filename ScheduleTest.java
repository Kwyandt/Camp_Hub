/**
 * @author Chance Storey
 */
import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.jupiter.api.*;

public class ScheduleTest{

    Schedule sampleSchedule;

    @BeforeClass
    public  void oneTimeSetup() {
        
    }

    @BeforeEach
	public  void reset() {
        sampleSchedule = new Schedule();
        sampleSchedule.randomlyPopulate(getDate("07-Jul-2023"), getDate("13-Jul-2023"));
	}


    @Test
    public void testRemoveNullEvent(){
        assertFalse(sampleSchedule.removeEvent(null, null), 
            "Removing a null event successfully indicates failure");
    }

    @Test
    public void testRemoveValidEvent(){
        Activity a = sampleSchedule.getEventsOfDay(getDate("07-Jul-2023")).get(0);
        assertTrue(sampleSchedule.removeEvent(a, getDate("07-Jul-2023")), 
            "Removing a valid event is successful");
    }

    @Test
    public void testRemoveInvalidEvent(){
        Activity a = new Activity("I dont exist", "I dont exist", "I dont exist");
        assertFalse(sampleSchedule.removeEvent(a, getDate("10-Jul-2023")), 
            "Removing an invalid event successfully indicates failure");
    }

    @Test
    public void testAddNullEvent(){
        try{
            sampleSchedule.addEvent(null, null);
            assertFalse(sampleSchedule.getActivities().containsKey(null),
                "adding null event successfully didn't do anything");
        }
        catch(Exception e){
            fail("Adding null event caused an uncaught exception");
        }
    }

    @Test
    public void testAddValidEvent(){
        try{
            Activity a = new Activity("test", "test", "test");
            sampleSchedule.addEvent(a, getDate("06-Jul-2023"));
            assertTrue(sampleSchedule.getActivities().containsKey(getDate("06-Jul-2023")),
                "adding valid event was successful");
        }
        catch(Exception e){
            fail("Adding valid event caused an uncaught exception");
        }
    }

    @Test
    public void testGetActivitiesForDateNull(){
        try{
            ArrayList<Activity> a = sampleSchedule.getEventsOfDay(null);
            assertEquals(null, a);
        }
        catch(Exception e){
            fail("Trying to get events with null date caused uncaught exception");
        }
    }

    @Test
    public void testGetActivitiesForDateEmpty(){
        try{
            ArrayList<Activity> a = sampleSchedule.getEventsOfDay(getDate("06-Jul-2023"));
            assertTrue(a.isEmpty(), "Getting Activities for a day with no activities returns empty list");
        }
        catch(Exception e){
            fail("Trying to get activities for a day with no activities caused uncaught exception");
        }
    }


    private static Date getDate(String str) {
        try {
            return new SimpleDateFormat("dd-MMM-yyyy").parse(str);
        } catch (Exception e) {
            return null;
        } 
    }
}