import java.util.ArrayList;
import java.util.UUID;

import Users.Camper;
import Users.Parent;
import Users.User;
import Users.UserType;

public class UserList {
    
    private ArrayList<User> users;
    private static UserList userList;
    
    /** 
     * Private constructor for singleton
     */
    private UserList() {
        users = DataReader.getAllUsers();
    }
    
    /** 
     * Get singleton instance
     * @return UserList
     */
    public static UserList getInstance() {
        if (userList == null) {
            userList = new UserList();
        }
        return userList;
    }

    /**
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
        for(User user : users){
            if(user.getEmail().equals(email)){
                return user;
            }
        }
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
            if (user.getUserType() == type) {
                usersOfType.add(user);
            }
        }
        return usersOfType;
    }

    /**
     * Return the camper with the given id
     * @param id UUID of camper
     * @return Camper with id
     */
    public Camper getCamperByUUID(UUID id) {
        for (User parent : getUsersOfType(UserType.PARENT))
            for (Camper camper : ((Parent)parent).getChildren())
                if (camper.getUuid().equals(id))
                    return camper;
        return null;
    }

    /**
     * Replace the user with the given ID in the last with the
     * parameter user
     * @param user User to replace user in list
     * @return True iff the edit was successful
     */
    public boolean editUser(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (user.getUuid().equals(users.get(i).getUuid())) {
                users.set(i, user);
                return false;
            }
        }
        // We didn't find the user, so return false
        return false;
    }

    /**
     * Return list of all users
     * @return ArrayList of Users
     */
    public ArrayList<User> getAllUsers() {
        return users;
    }

    /**
     * Save users to JSON files
     * @return True if saving was successful and false otherwise
     */
    public boolean saveAllUsers() {
        return DataWriter.saveAllUsers(users);
    }

    public static void clear() {
        userList = new UserList();
    }

}
