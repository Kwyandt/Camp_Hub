import java.util.*;
import java.io.IOException;


public class CampUI {
    private Scanner scan;
    private CampManager campManager;

    // A list of all of the 'menus', which are just text prompts (as strings)
    // ex: the first entry is what you see on the initial startup screen
    private final String[] menus = {
        "Please select:\n0. Quit\n1. Login\n2. Create Account\n"
    };

    // A massive list of 'forms', which are just arrays of prompts (strings)
    private final String[][] forms ={
        {   // The prompts for createUser()
            "Please enter user type:\n1. Parent\n2. Director\n3. Counselor\n",
            "Please enter email",
            "Please enter password:",
            "Please enter first name:",
            "Please enter last name:",
            "Please enter phone number:",
            "Please enter birthdate (MM/DD/YYYY):",
            "Please select security question:\n[LIST OF QUESTIONS]\n",
            "Please answer the question you selected:",
        },
        {   // The prompts for loginUser()
            "Please enter email:",
            "Please enter password:"
        }
    };

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
    public void start(){
        int selection = 0;
        do{
            clearScreen();
            System.out.println("Welcome to [Camp]'s Portal!");
            System.out.println(menus[0]);
            selection = promptInt(0,2);

            switch(selection){
                case 0: exit(); break;
                case 1: loginUser(); break;
                case 2: createUser(); break;
                default:
                    System.out.println("Something went wrong!");
            }

        }while(selection!=0);
    }

    public void exit(){
        // Run save commands here
        clearScreen();
        System.out.println("Goodbye!");
    }



    /**
     * Sends the user through the registration form and returns them to the start screen
     */
    public void createUser(){
        final int FORM = 0;
        Object[] data = new Object[forms[FORM].length];

        clearScreen();
        System.out.println("Registering New User:\n");

        for(int index = 0; index < forms[FORM].length; index++){
            System.out.println(forms[FORM][index]);
            data[index] = prompt();
        }

        //TODO: run checks to see if the given data is valid, make the user, etc.
        System.out.println("You completed the form! Press enter to continue...");
        scan.nextLine();
    }
    public void loginUser(){
        final int FORM = 1;

        clearScreen();
        System.out.println("Logging you in!\n");

        System.out.println(forms[FORM][0]);
        String email = prompt();
        System.out.println(forms[FORM][1]);
        String pass = prompt();

        //TODO: Ensure the user is a valid user, make campManager log them in
        System.out.println("You completed the form! Press enter to continue...");
        scan.nextLine();
    }

    public void parentMenu(){
        
    }
    public void directorMenu(){
        
    }
    public void counselorMenu(){
        
    }


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


    /**
     * Prompts the user for input, and returns the input string directly
     * @return The String that the user inputted
     */
    private String prompt(){
        System.out.print("> ");
        return scan.nextLine();
    }

    /**
     * Prompts the user for a date string, repeatedly asking until they supply a valid date
     * @return The Date the user inputted
     */
    private Date promptDate(){
        //TODO: implement this bad boy
        return null;
    }

    /**
     * Prompts the user for an integer input, repeatedly asking until the user gives a 
     * String that can be parsed as an int, and that is between the given bounds
     * @param min The minimum allowed integer
     * @param max The maximum allowed integer
     * @return The int that the user inputted
     */
    private int promptInt(int min, int max){
        System.out.print("> ");
        String input = scan.nextLine();
        //This is a regular expression designed to match a positive or negative number
        String filter = "-?\\d+";
        while(!input.matches(filter) || Integer.parseInt(input)<min || Integer.parseInt(input)>max){
            System.out.print("Invalid input, please try again: \n> ");
            input = scan.nextLine();
        }
        return Integer.parseInt(input);
    }

    /**
     * This should clear the console screen according to this post:
     * https://stackoverflow.com/questions/2979383/how-to-clear-the-console
     */
    private void clearScreen(){
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}
    }



    public static void main(String[] args){
        (new CampUI()).start();
    }
}