import java.util.*;
import Users.*;

public class CampManager{
    //private Camp camp;
    private User currentUser;

    public CampManager(){

    }

    public void createUser(int type, String email, String phone, String pass, 
                        String first, String last, Date birth, 
                        Map<String, String> securityQuestion){
        
    }

    public void loginUser(String email, String pass){

    }

    public boolean isValidLogin(String email, String pass){
        return false;
    }

    public void addActivity(String name, String description, String location){}

    public void addSession(String theme, Date priorityDate, Date regularDate, Date startDate){

    }

    public void setDiscount(Parent parent, double discount){}

    // These methods may or may not actually be in the final version
    public void notifyParents(String message){}
    public void notifyCounselors(String message){}
    //public void viewSessionInfo(Session session){}
    public void viewAllUsers(){}
    public void fillScheduleRandomly(){}


    // Getters..?
    //public Camp getCamp(){return camp;}
    public User getUser(){return currentUser;}
}