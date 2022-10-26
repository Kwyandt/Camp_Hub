import java.util.*;
import Users.*;

public class CampManager{
    private Camp camp;
    private User currentUser;
    private UserList users;

    public CampManager(){
        camp = DataReader.getCamp();
        users = UserList.getInstance();
    }

    public boolean createUser(UserType type, String email,  String pass, 
                        String first, String last, String phone, Date birth, 
                        Map<String, String> securityQuestion){
        
        if(users.getUser(email)!=null)
            //Can't create account because one with that email already exists!
            return false;
        //Creates the account
        switch(type){
            case PARENT:
                users.addUser(new Parent(email, pass, first, last, phone, birth, securityQuestion));
            break;
            case DIRECTOR:
                users.addUser(new Director(email, pass, first, last, phone, birth, securityQuestion));
            break;
            case COUNSELOR:
                users.addUser(new Counselor(email, pass, first, last, phone, birth, securityQuestion));
            break;
        }
        //Return that this operation was successful
        return true;
    }

    /**
     * Checks to see if login is valid. If it is, logs the user in.
     * Otherwise, nothing happens, return false.
     * @param email The email 
     * @param pass The password
     * @return boolean Whether the login was successful
     */
    public boolean loginUser(String email, String pass){
        User user = users.getUser(email);
        if(user!=null && user.getPassword().equals(pass)){
            this.currentUser = user;
            return true;
        }
        return false;
    }

    /**
     * Does whatever processes are necessary to 
     */
    public boolean logoutUser(){
        if (currentUser==null)
            //Logout wasn't successful, return false
            return false;

        // Insert save user info here?
        currentUser=null;
        //Logout was successful, return true
        return true;
    }


    //I DONT THINK THIS IS NEEDED ANYMORE, SINCE THE UI FLOW TECHINCALLY HANDLES THIS
    //(maybe keep it for "security" sake in case we ever decide to make a new UI?)
    /**
     * Verifies that the current user actually has the ability to perfom the action.
     * Each utility method in the manager will first check using this method that the currentUser's type is correct.
     * Some abilities have multiple allowed user types, so this should help that a little bit.
     * @param userTypes A String representing the allowed users. 'c' means counselors, 'p' parents, and 'd' directors
     * @return True if the currentUser's type matches one of the characters in the userType string
     */
    private boolean checkPermissions(String userTypes){
        switch(currentUser.getUserType()){
            case COUNSELOR:
                return userTypes.contains("c");
            case DIRECTOR:
                return userTypes.contains("d");
            case PARENT:
                return userTypes.contains("p");
            default: 
                System.out.println("SOMETHING WENT HORRIBLY WRONG!");
        }
        return false;
    }
    

    //Parent-specific methods
    public boolean addCamper(Parent parent, String first, String last, Date birth, 
                        String[] guardianContact, String[] doctorContact, String[] dentistContact,
                        String tshirt){
        if(!checkPermissions("p")){
            //The current user isn't allowed here, return false to indicate the operation failed
            return false; 
        }
        //Tell the appropriate classes to add a camper object
        
        //Return true since the operation was successful
        return true;
    }
    public void registerCamper(Camper camper, Session session){}
    public void unregisterCamper(Camper camper, Session session){}



    //director specific
    public void addActivity(String name, String description, String location){}
    public void removeActivity(int index){}
    public void addSession(String theme, Date priorityDate, Date regularDate, Date startDate){}
    public void removeSession(int index){}
    public void setDiscount(Parent parent, double discount){}
    public void addFAQ(String faq){}
    public void removeFAQ(int index){}
    public void addPackingItem(String item){}
    public void removePackingItem(int index){}
    public void assignCounselor(Session session, Counselor counselor, Cabin cabin){}
    public void assignCamper(Session session, Camper camper, Cabin cabin){}

    


    //counselor specific
    public void registerCounselor(Counselor counselor, Session session){}
    public void unregisterCounselor(Counselor counselor, Session session){}
    public void addNote(String note){}
    public void removeNote(int index){}
    
    //shared by certain users
    /**
     * Set's the current user's bio to the given bio (if they're a director or counselor only)
     * @param bio String the bio to set
     * @return boolean indicating if the operation was successful
     */
    public boolean setBio(String bio){
        if(!checkPermissions("dc")) 
            return false;
        if(currentUser.getUserType()==UserType.COUNSELOR)
            ((Counselor)currentUser).addBio(bio);
        else
            ((Director)currentUser).addBio(bio);
        return true;
    }

    public void viewRegistrations(){}
    public void viewAboutPage(){}



    // These methods may or may not actually be in the final version
    public void notifyParents(String message){}
    public void notifyCounselors(String message){}
    public void viewSessionInfo(Session session){}
    public void viewAllUsers(){}
    public void fillScheduleRandomly(){}


    // Getters..?
    public Camp getCamp(){
        return camp;
    }
    public User getUser(){
        return currentUser;
    }
    public UserType getType(){
        return currentUser.getUserType();
    }

    /**
     * Retrieves the user's bio, returning null if the user is a parent,
     * and an empty string if the user has not set thier bio
     * @return String the bio of the current user
     */
    public String getBio(){
        if(!checkPermissions("dc")) 
            return null;
        if(currentUser.getUserType()==UserType.COUNSELOR){
            if(((Counselor)currentUser).getBio()==null)
                return "";
            return ((Counselor)currentUser).getBio();
        }
        else{
            if(((Director)currentUser).getBio()==null)
                return "";
            return ((Director)currentUser).getBio();
        }
    }
}