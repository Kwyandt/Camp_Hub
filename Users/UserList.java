package Users;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class UserList {
    
    private ArrayList<User> users;
    private UserList userList;

    
    /** 
     * Private constructor for singleton
     * @return UserList
     */
    private UserList UserList() {
        return null;
    }

    
    /** 
     * Get singleton instance
     * @return UserList
     */
    public static UserList getInstance() {
        return null;
    }

    
    /** 
     * Adds a user with the given information.
     * @param email
     * @param phone
     * @param password
     * @param firstName
     * @param lastName
     * @param birthdate
     * @param securityQuestion
     */
    public void addUser(String email, String phone, String password, String firstName, String lastName, Date birthdate, Map<String, String> securityQuestion) {

    }

    
    /** 
     * Returns the user given an email.
     * @param email
     * @return User
     */
    public User getUser(String email) {
        return null;
    }

    public void editUser() {

    }

    public void saveUsers() {

    }

}
