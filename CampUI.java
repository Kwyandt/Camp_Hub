import java.util.*;

public class CampUI {
    private Scanner scan;
    private CampManager campManager;

    private CampUI(){
        campManager = new CampManager();
        scan = new Scanner(System.in);
    }

    /**
     * TODO: 
     * This method is intended to be the "main" scope of the program
     * It will prompt for input, and then direct the program flow to the
     * proper sub routine (like the createUser form, for example).
     * Then once all the necessary info is given, the UI requests the 
     * CampManager to actually perform the desired action.
     */
    public void run(){
        System.out.println("Welcome to the [Camp]");
        System.out.println("I don't do anything yet");

        exit();
    }

    public void exit(){
        // Run save commands here
        System.out.println("Goodbye!");
    }





    //These are accessible by anyone who boots the program
    public void createUser(){}
    public void loginUser(){}



    //The following are only accessible to the parent users
    public void manageCampers(){}
    public void addCamper(){}
    public void editCamper(){}

    public void viewAboutPage(){}


    //These are accessible only by directors
    public void manageSessions(){}
    public void addSession(){}
    public void editSession(){}

    public void manageActivities(){}
    public void manageFAQ(){}
    public void manageDiscounts(){}
    public void manageCabins(){}

    //These are accessible only by counselors
    public void manageNotes(){}

    //This method is accessible to counselors and directors
    public void manageBio(){}

    //This method is accessible to counselors and parents
    public void registerForCamp(){}
    public void viewRegistrations(){}

    //These methods are accessible to any logged in user
    public void manageUserInfo(){}



    public static void main(String[] args){
        (new CampUI()).run();
    }
}