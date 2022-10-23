package Users;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class UserList {
    
    private ArrayList<User> users;
    private static UserList userList;

    
    /** 
     * Private constructor for singleton
     * @return UserList
     */
    private UserList() {
        users = new ArrayList<User>();
    }

    
    /** 
     * Get singleton instance
     * @return UserList
     */
    public static UserList getInstance() {
        if(userList == null) {
            userList = new UserList();
        }
        return userList;
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

    /***
     * Adds an already created user
     * @param user pre-existing user to be added
     */
    public void addUser(User user) {
        users.add(user);
    }
    
    /** 
     * Returns the user given an email.
     * @param email
     * @return User
     */
    public User getUser(String email) {
        return null;
    }

    /**
     * Returns the user given their UUID.
     * If no such camper is found, returns null.
     * @param id UUID of user
     * @return Appropriate user (or null)
     */
    public User getUserByUUID(UUID id) {
        for (User user : users) {
            if (user.getUuid().equals(id)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Returns the users matching the requested type.
     * @param type UserType to match (Director, Counsellor, or Parent)
     * @return ArrayList of Users matching type (empty if none)
     */
    public ArrayList<User> getUsersOfType(UserType type) {
        ArrayList<User> usersOfType = new ArrayList<User>();
        for (User user : users) {
            if (user.getType() == type) {
                usersOfType.add(user);
            }
        }
        return usersOfType;
    }

    public Camper getCamperByUUID(UUID id) {
        for (User parent : getUsersOfType(UserType.PARENT))
            for (Camper camper : ((Parent)parent).getChildren())
                if (camper.getUuid().equals(id))
                    return camper;
        return null;
    }

    public void editUser() {

    }

    public void saveUsers() {

    }

    public ArrayList<User> getAllUsers() {
        return users;
    }

}
