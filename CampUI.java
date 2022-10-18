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
     * proper sub routine (like the registerUser form, for example).
     * Then once all the necessary info is given, the UI requests the 
     * CampManager to actually perform the desired action.
     */
    private void run(){
        System.out.println("Welcome to the [Camp]");
        System.out.println("I don't do anything yet");
    }

    /**
     * Asks the user to input the necessary information,
     * then tells the campManager to create the user
     */
    private void registerUser(){

    }

    private void loginUser(){

    }



    public static void main(String[] args){
        (new CampUI()).run();
    }
}