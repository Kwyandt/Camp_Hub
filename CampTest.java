/**
 * @author Chance Storey
 */
import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.jupiter.api.*;

public class CampTest{

    private Camp camp;
    private UUID validActivityUUID, invalidActivityUUID;

    @BeforeClass
    public  void oneTimeSetup() {
        validActivityUUID = UUID.fromString("421c8342-ab44-4ff1-81b6-02a10406ff98");
        invalidActivityUUID = UUID.fromString("7dd81db0-7467-462a-af4c-25c50ea8bb4f");
    }

    @BeforeEach
	public  void resetCamp() {
		Camp.clear();
        camp = Camp.getInstance();
	}

    @Test
    public  void testGetNullActivity(){
        assertEquals(null,camp.getActivityByUUID(null));
    }

    @Test
    public  void testGetValidActivity(){
        Activity expected = new Activity("Frisbee Golf","","Recreation Fields");
        assertEquals(expected,camp.getActivityByUUID(validActivityUUID));
    }

    @Test
    public void testGetInvalidActivity(){
        Activity expected = null;
        assertEquals(expected,camp.getActivityByUUID(invalidActivityUUID));
    }

    @Test
    public void testRemoveActivityValid(){
        int index = 8;
        Activity expected = new Activity("Frisbee Golf","","Recreation Fields");
        camp.removeActivity(index);
        assertFalse(camp.getActivities().contains(expected),"Removing activity at index 0 was successful");
    }

    @Test
    public void testRemoveActivityNeg(){
        int index = -1;
        assertFalse(camp.removeActivity(index),"Removing activity at index -1 was correctly unsuccessful");
    }

    @Test
    public void testRemoevActivityValidUUID(){
        Activity expected = new Activity("Frisbee Golf","","Recreation Fields");
        camp.removeActivity(validActivityUUID);
        assertFalse(camp.getActivities().contains(expected),"Removing activity by UUID was successful");
    }

    @Test
    public void testRemoveActivityInvalidUUID(){
        assertFalse(camp.removeActivity(UUID.fromString("7dd81db0-7467-462a-af4c-25c50ea8bb4f")),
            "Removing activity by invalid UUID was correctly unsuccessful");
    }

    @Test
    public void testRemoveActivityNullUUID(){
        assertFalse(camp.removeActivity(null),"Removing activity by null UUID was correctly unsuccessful");
    }

    @Test
    public void testRemovePackingItemValid(){
        assertTrue(camp.removePackingItem("1 jacket"),"Removing valid packing item was successful");
    }

    @Test
    public void testRemovePackingItemNull(){
        assertFalse(camp.removePackingItem(null),"Removing null packing item was correctly unsuccessful");
    }

    @Test
    public void testRemovePackingItemInvalid(){
        assertFalse(camp.removePackingItem("asdf"),"Removing invalid packing item was correctly unsuccessful");
    }

    @Test
    public void testRemovePackingItemIndexValid(){
        assertTrue(camp.removePackingItem(4),"Removing valid packing item index was successful");
    }

    @Test
    public void testRemovePackingItemIndexNeg(){
        assertFalse(camp.removePackingItem(-1),"Removing negative packing item index was correctly unsuccessful");
    }
    
    @Test
    public void testRemovePackingItemIndexOutOfBounda(){
        assertFalse(camp.removePackingItem(100),"Removing too big packing item index was correctly unsuccessful");
    }

    //Nathan, refactored for this test by Chance
    @Test
    public void testSetOfficePhoneValid0() {
        String valid = "(555) 555-5555";
        camp.setOfficePhone(valid);
        assertEquals(valid, camp.getOfficePhone());
    }

    // Nathan, refactored for this test by Chance
    @Test
    public void testSetOfficePhoneValid1() {
        String valid = "5555555555";
        camp.setOfficePhone(valid);
        assertEquals(valid, camp.getOfficePhone());
    }

    // Nathan, refactored for this test by Chance
    @Test
    public void testSetOfficePhoneValid2() {
        String valid = "555-555-5555";
        camp.setOfficePhone(valid);
        assertEquals(valid, camp.getOfficePhone());
    }

    // Nathan, refactored for this test by Chance
    @Test
    public void testSetOfficePhoneNull() {
        String invalid = null;
        camp.setOfficePhone(invalid);
        assertNotEquals(invalid, camp.getOfficePhone());
    }

    // Nathan, refactored for this test by Chance
    @Test
    public void testSetOfficePhoneEmpty() {
        String invalid = "";
        camp.setOfficePhone(invalid);
        assertNotEquals(invalid, camp.getOfficePhone());
    }

    // Nathan, refactored for this test by Chance
    @Test
    public void testSetOfficePhoneNonNumericCharacters0() {
        String invalid = "askjdfh9€htgUIDNVG09h2€)G";
        camp.setOfficePhone(invalid);
        assertNotEquals(invalid, camp.getOfficePhone());
    }

    // Nathan, refactored for this test by Chance
    @Test
    public void testSetOfficePhoneNonNumericCharacters1() {
        String invalid = "5555555555LoLtHiSiSnOtAnUmBeRyOuCaNdIaLoNaFlIpPhOnE";
        camp.setOfficePhone(invalid);
        assertNotEquals(invalid, camp.getOfficePhone());
    }
}