import java.util.*;
import Users.*;

public class CampManager{
    private Camp camp;
    private User currentUser;
    private UserList users;

    public CampManager(){

    }

    public void createUser(UserType type, String email, String phone, String pass, 
                        String first, String last, Date birth, 
                        Map<String, String> securityQuestion){
        
    }

    /**
     * Checks to see if login is valid. If it is, logs the user in.
     * Otherwise, nothing happens, return false.
     * @param email The email 
     * @param pass The password
     * @return boolean Whether the login was successful
     */
    public boolean loginUser(String email, String pass){
        return false;
    }
    private boolean checkPermissions(String userTypes){
        return false;
    }
    

    //Parent-specific methods
    public void addCamper(Parent parent, String first, String last, Date birth, 
                        String[] guardianContact, String[] doctorContact, String[] dentistContact,
                        String tshirt){
        
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
    public void deregisterCounselor(Counselor counselor, Session session){}
    public void addNote(String note){}
    public void removeNote(int index){}
    
    //shared by certain users
    public void setBio(String bio){}
    public void viewRegistrations(){}
    public void viewAboutPage(){}



    // These methods may or may not actually be in the final version
    public void notifyParents(String message){}
    public void notifyCounselors(String message){}
    public void viewSessionInfo(Session session){}
    public void viewAllUsers(){}
    public void fillScheduleRandomly(){}


    // Getters..?
    public Camp getCamp(){return camp;}
    public User getUser(){return currentUser;}
}