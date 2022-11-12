/**
 * @author Jackson
 */
import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Users.Director;
import Users.Parent;
import Users.User;


public class UserListTest {

    private UserList userList = UserList.getInstance();
    private ArrayList<User> newUsers = new ArrayList<User>();

    @BeforeClass
    public void oneTimeSetup() {
        String date_string = "26-09-1979";
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");      
        Date dirDate = new Date();
        try {
            dirDate = formatter.parse(date_string);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Director d = new Director("dtest@gmail.com", "kj;lfdkadfsjl", "Dir", "Ector", "5555555555", dirDate, null);
        newUsers.add(d);
    }

    @AfterClass
    public void oneTimeTearDown() {

    }

    @BeforeEach
    public void setup() {

    }

    @AfterEach
    public void tearDown() {

    }

    @Test
    public void addDirector() {
        
    }
}
