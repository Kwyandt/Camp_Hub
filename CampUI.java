import java.util.*;
import java.util.Map.Entry;

import Users.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParsePosition;


public class CampUI {
    private Scanner scan;
    private CampManager campManager;

    // A list of all of the 'menus', which are just text prompts (as strings)
    // ex: the first entry is what you see on the initial startup screen
    private final String[] menus = {
        // The start menu (0)
        "Please select:\n0. Quit\n1. Login\n2. Create Account\n",
        // The parent user menu (1)
        "Please select:\n0. Sign out\n1. Register for camp!\n2. View registrations\n"+
        "3. Manage children\n4. Contact/FAQ\n5. Manage account\n",
        // The counselor user menu (2)
        "Please select:\n0. Sign out\n1. Register for camp!\n2. View registrations\n"+
        "3. Manage Notes\n4. Manage Bio\n5. Manage account\n",
        // The director user menu (3)
        "Please select:\n0. Sign out\n1. Manage camp sessions\n2. Manage camp activities\n"+
        "3. Manage camp FAQ\n4. Manage bio\n5. Manage account\n",
        // The manage campers menu (4)
        "Please select:\n0. Go back\n1. Add camper\n2. Edit camper information\n",
        // The register camper menu (after you select a session to register for) (5)
        "Please select a camper to register:\n0. Go back\n1. Add camper",
        // The manage account menu (6)
        "Please select:\n0. Go back\n1. Update email\n3. Update password\n3. Update phone number\n4. Update security question\n",
        // The manage bio menu (7)
        "Please select:\n0. Go back\n1. Update bio\n",
        // The manage notes menu (8)
        "Please select:\n0. Go back\n1. Add note\n2. Remove note\n",
        // The manage discounts menu (9)
        "Please select:\n0. Go back\n1. Set discount\n",
        // The manage FAQs menu (10)
        "Please select:\n0. Go back\n1. Add FAQ\n2. Remove FAQ\n",
        // The camper edit menu (11)
        "Please select:\n0. Go back\n1. Update \n2. [add more]\n"
    };

    // A massive list of 'forms', which are just arrays of prompts (strings)
    private final String[][] forms = {
        {   // The prompts for createUser()
            "Please enter user type:\n1. Parent\n2. Counselor\n",
            "Please enter email",
            "Please enter password:",
            "Please enter first name:",
            "Please enter last name:",
            "Please enter phone number:",
            "Please enter birthdate (MM/DD/YYYY):",
            "Please select security question:\n",
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
     * This is the first screen the user sees, where they are given
     * the option to sign in or create an account.
     */
    public void start(){
        final int MENU = 0;
        int selection = 0;
        do{
            clearScreen();
            System.out.println("Welcome to "+campManager.getCamp().getName()+"'s Portal!");
            System.out.println(menus[MENU]);
            selection = promptInt(0,2);

            switch(selection){
                case 0: exit(); break;
                case 1: loginUser(); break;
                case 2: createUser(); break;
                default: System.out.println("Something went wrong!");
            }
        }while(selection!=0);
    }

    /**
     * Does whatever processes are necessary to quit the program gracefully
     * (run whatever call will save the data to json, quit program)
     */
    public void exit(){
        // Run save commands here
        clearScreen();
        System.out.println("Goodbye!");
    }

    /**
     * Currently just requests the campManager to log the user out.
     * Might have to add some sort of save command?
     */
    public void logoutUser(){
        campManager.logoutUser();
    }


    /**
     * Sends the user through the registration form and returns them to the start screen
     */
    public void createUser(){
        final int FORM = 0;
        // a bulk storage object for the input loop, will be casted later when used.
        // (input types are guarunteed, so this works completely fine despite being kinda silly)
        Object[] data = new Object[forms[FORM].length];

        clearScreen();
        System.out.println("Registering New User:\n");

        for(int index = 0; index < forms[FORM].length; index++){
            System.out.println(forms[FORM][index]);
            switch(index){
                case 0:     // The user type selection prompt
                    data[index] = promptInt(1,2);
                    break;
                case 7:     // The security question selection prompt
                    ArrayList<String> questions = campManager.getCamp().getSecurityQuestions();
                    for(int i=0; i<questions.size();i++){
                        System.out.printf("%-4s%s%n",(i+1)+".", questions.get(i));
                    }
                    System.out.println(); // inserting a newline for readability
                    data[index] = promptInt(1, questions.size());
                    break;
                case 6:     // The birthdate selection prompt
                    data[index] = promptDate();
                    break;
                default:
                    data[index] = prompt();
            }
        }
        // grap the appropriate user type from input array
        UserType type = UserType.PARENT; 
        switch((int)data[0]){
            case 1: type = UserType.PARENT; break;
            case 2: type = UserType.COUNSELOR; break;
        }
        // grab the appropriate question from the input array (have to decrement b/c input is 1 indexed)
        String question = campManager.getCamp().getSecurityQuestions().get(((int)data[7]) - 1);

        Map<String, String> securityQuestion = new HashMap<String, String>();
        securityQuestion.put(question, (String) data[8]);

        // attempt to create the user now that we have all the necessary info
        boolean success = campManager.createUser(type, (String) data[1], (String) data[2], (String) data[3],
                                (String) data[4], (String) data[5], (Date) data[6], securityQuestion);

        if(success)
            System.out.println("Account created successfully! You can now proceed to login");
        else
            System.out.println("Account creation failed! Please try again with a different email or log in");
        prompt(true);
    }

    /**
     * Sends the user through the login form.
     * If successful, they will be sent to their user page.
     * If unsuccessful, they will be taken back to the start
     */
    public void loginUser(){
        final int FORM = 1;
        clearScreen();
        System.out.println("Logging you in!\n");
        System.out.println(forms[FORM][0]);
        String email = prompt();
        System.out.println(forms[FORM][1]);
        String pass = prompt();

        boolean success = campManager.loginUser(email, pass);

        if(success){
            userMenu();
        }
        else{
            System.out.println("Login was unsuccessful. Verify information and try again, or create an account.");
            prompt(true);
        }
    }

    /**
     * This handles the main screen the user sees when they log in for ALL user types
     */
    public void userMenu(){
        User user = campManager.getUser();
        int selection = 0;
        do{
            clearScreen();
            System.out.printf("Hello, %s %s! (%s)%n%n", user.getFirstName(),
                            user.getLastName(), user.getUserType());
            switch(user.getUserType()){
                case DIRECTOR:
                    System.out.println(menus[3]);
                    selection = promptInt(0,5);
                    switch(selection){
                        case 0: logoutUser(); break;
                        case 1: viewSessions(); break;
                        case 2: manageActivities(); break;
                        case 3: manageFAQ(); break;
                        case 4: manageBio(); break;
                        case 5: manageAccount(); break;
                    }
                break;
                case COUNSELOR:
                    System.out.println(menus[2]);
                    selection = promptInt(0,5);
                    switch(selection){
                        case 0: logoutUser(); break;
                        case 1: viewSessions(); break;
                        case 2: viewRegistrations(); break;
                        case 3: manageNotes(); break;
                        case 4: manageBio(); break;
                        case 5: manageAccount(); break;
                    }
                break;
                case PARENT:
                    System.out.println(menus[1]);
                    selection = promptInt(0,5);
                    switch(selection){
                        case 0: logoutUser(); break;
                        case 1: viewSessions(); break;
                        case 2: viewRegistrations(); break;
                        case 3: manageCampers(); break;
                        case 4: viewAboutPage(); break;
                        case 5: manageAccount(); break;
                    }
                break;
            }
        }while(selection!=0);
    }


    //The following are only accessible to the parent users
    public void manageCampers(){
        final int MENU = 4;
        int selection = 0;
        do{
            clearScreen();
            System.out.println("Viewing Campers:\n");
            ArrayList<Camper> kids = campManager.getChildren();
            for(int i = 0; i < kids.size(); i++){
                System.out.printf("%d. %s %s%n", i+1, kids.get(i).getFirst(), kids.get(i).getLast());
            }
            if(kids.size() == 0){
                System.out.println("No campers added yet.");
            }
            System.out.println("\n"+menus[MENU]);
            selection = promptInt(0,2);

            switch(selection){
                case 0: break;
                case 1: addCamper(); break;
                case 2: 
                    if(kids.size() == 0){
                        System.out.println("No campers to edit.");
                        prompt(true);
                        break;
                    }
                    System.out.println("Please select camper to edit: ");
                    int input = promptInt(1, kids.size());
                    editCamper(input - 1); 
                    break;
                default: System.out.println("Something went wrong!");
            }
        }while(selection!=0);
    }

    public void addCamper(){
        clearScreen();
        System.out.println("This is the Add Camper form. I don't do anything yet");
        prompt(true);
    }
    
    public void editCamper(int index){
        final int MENU = 11;
        int selection = 0;
        Camper camper = campManager.getChildren().get(index);
        DateFormat formatter = DateFormat.getDateInstance(DateFormat.MEDIUM);
        do{
            clearScreen();
            System.out.println("Viewing Camper:\n");
            System.out.printf("%-10s%s %s%n","Name: ", camper.getFirst(),camper.getLast());
            System.out.printf("%-10s%s (Age: %d)%n","Birth: ", formatter.format(camper.getBirth()),camper.getAge(camper.getBirth()));
            System.out.printf("%-10s%s%n","Tshirt: ",camper.getTShirt());
            System.out.println("Allergies:");
            for(int i = 0;  i < camper.getAllergy().size(); i++){
                System.out.printf("    %d. %s%n",i+1,camper.getAllergy().get(i));
            }
            System.out.println("Dietary Restrictions:");
            for(int i = 0;  i < camper.getDietaryRestrictions().size(); i++){
                System.out.printf("    %d. %s%n",i+1,camper.getDietaryRestrictions().get(i));
            }
            System.out.println("Medications:");
            for(int i = 0;  i < camper.getMeds().size(); i++){
                System.out.printf("    %d. %s%n",i+1,camper.getMeds().get(i));
            }
            System.out.println("\n"+menus[MENU]);

            selection = promptInt(0,2);


            switch(selection){
                case 0: break;
                case 1: 
                    // perform whatever action here
                break;
                case 2: 
                     
                break;
                default: System.out.println("Something went wrong!");
            }
        }while(selection!=0);
    }

    /**
     * Displays camp information, then returns user to the menu
     */
    public void viewAboutPage(){
        clearScreen();
        System.out.println(campManager.getAboutPage());
        prompt(true);
    }


    //These are accessible only by directors
    public void manageSession(int index){
        clearScreen();
        System.out.println("This is the manage sessions menu. I don't do anything yet");
        prompt(true);
    }
    
    public void addSession(){
        clearScreen();
        System.out.println("This is the add session form. I don't do anything yet");
        prompt(true);
    }
    
    public void editSession(){
        clearScreen();
        System.out.println("This is the session edit menu. I don't do anything yet");
        prompt(true);
    }

    public void manageActivities(){
        clearScreen();
        System.out.println("This is the manage activities menu. I don't do anything yet");
        prompt(true);
    }

    public void manageFAQ(){
        clearScreen();
        final int MENU = 10;
        int selection = 0;
        do{
            clearScreen();
            System.out.println("Manage FAQs:\n\nCurrent FAQs:");
            Map<String,String> faqs = campManager.getCamp().getFAQs();
            String[] questions = new String[faqs.size()];
            int index = 0;
            for(String key : faqs.keySet()){
                System.out.printf("%d. Q: %s%n      A: %s", index, key, faqs.get(key));
                questions[index] = key;
                index++;
            }

            System.out.println("\n"+menus[MENU]);
            selection = promptInt(0,2);

            switch(selection){
                case 0: exit(); break;
                case 1: 
                    System.out.println("Please enter question:");
                    String q = prompt();
                    System.out.println("Please enter answer:");
                    String a = prompt();
                    campManager.addFAQ(q, a);
                break;
                case 2:
                    System.out.println("Please enter a number to remove (0 to cancel)");
                    int input = promptInt(0, questions.length);
                    if(input == 0) 
                        break;
                    campManager.removeFAQ(questions[input]);
                default: System.out.println("Something went wrong!");
            }
        }while(selection!=0);
    }
    
    public void manageDiscounts(){
        clearScreen();
        System.out.println("This is the manage discounts menu. I don't do anything yet");
        prompt(true);
    }

    public void manageCabins(){
        clearScreen();
        System.out.println("This is the manage cabins menu. I don't do anything yet");
        prompt(true);
    }

    //These are accessible only by counselors
    public void manageNotes(){
        final int MENU = 10;
        int selection = 0;
        do{
            clearScreen();
            System.out.println("Manage Notess:\n\nCurrent Notes:");
            ArrayList<String> notes = campManager.getNotes();

            System.out.println("\n"+menus[MENU]);
            selection = promptInt(0,2);

            switch(selection){
                case 0: exit(); break;
                case 1: 
                    System.out.println("Please enter note:");
                    String note = prompt();
                    campManager.addNote(note);
                break;
                case 2:
                    System.out.println("Please enter a number to remove (0 to cancel)");
                    int input = promptInt(0, notes.size());
                    if(input == 0) 
                        break;
                    campManager.removeNote(input);
                default: System.out.println("Something went wrong!");
            }
        }while(selection!=0);
    }

    //This method is accessible to counselors and directors
    public void manageBio(){
        final int MENU = 7;
        int selection = 0;
        do{
            clearScreen();
            System.out.println("Manage Bio:\n\nCurrent bio:");
            System.out.println(campManager.getBio()+"\n");

            System.out.println(menus[MENU]);
            selection = promptInt(0,1);

            switch(selection){
                case 0: exit(); break;
                case 1: 
                    System.out.println("Please enter a new bio:");
                    String input = prompt();
                    boolean success = campManager.setBio(input);
                    if(!success){
                        System.out.println("Something went wrong saving the bio, try again.");
                        prompt(true);
                    }
                break;
                default: System.out.println("Something went wrong!");
            }
        }while(selection!=0);
    }

    /**
     * All users have some form of viewing and selecting from a list of sessions:
     *  - Parents & Counselors can register (in slightly different ways)
     *  - Directors can view a list of sessions and edit those sessions
     * 
     * So this method handles all of those abilities, similar to the userMenu
     */
    public void viewSessions(){
        int selection =0;
        do{
            System.out.println("Viewing Sessions:\n\n0. Go back");
            //print sessions
            System.out.println("\nPlease select a session to see more information:");
            selection = promptInt(0,0);
            if(selection==0)
                break;
            switch(campManager.getUser().getUserType()){
                case DIRECTOR:
                    manageSession(selection-1);
                    break;
                case PARENT:
                case COUNSELOR:
                    registerForSession(selection-1);
                    break;
            }
        }while(selection != 0);
    }

    public void registerForSession(int index){
        System.out.println("Registering for session!\n\n");
        // Grab session object, print information

        /* switch(campManager.getUser().getUserType()){
            case COUNSELOR:
                break;
            case PARENT:
                if(campManager.getChildren().size()==0)
                    System.out.println("(No campers registered yet, please add a camper)");
                else{
                    for(Camper c : ((Parent)campManager.getUser()).getChildren()){
                        System.out.printf("")
                    }
                }
                System.out.println("Please select")
                break;
            default:
        } */
    }

    //This method is accessible to counselors and parents
    public void viewRegistrations(){
        clearScreen();
        System.out.println("This is the view current registrations menu. I don't do anything yet");
        prompt(true);
    }

    //These methods are accessible to any logged in user
    public void manageAccount(){
        final int MENU = 6;
        int selection = 0;
        DateFormat formatter = DateFormat.getDateInstance();
        User user = campManager.getUser();
        String format = "%-10s%s%n";
        
        do{
            clearScreen();
            System.out.println("Viewing Account Information:\n");

            System.out.printf(format,"name:",user.getFirstName()+" "+user.getLastName());
            System.out.printf(format,"email:",user.getEmail());
            System.out.printf(format,"phone:",user.getPhone());
            //currently null errors for some reason
            System.out.printf(format,"birth:",formatter.format(user.getBirthDate()));

            System.out.println("\n"+menus[MENU]);
            selection = promptInt(0,4);

            switch(selection){
                case 0: exit(); break;
                case 1:
                    System.out.println("Please enter new email: ");
                    String input = prompt();
                    if(!campManager.setEmail(input)){
                        System.out.println("Email change unsuccessful. Likely another user with that email already exists!");
                        prompt(true);
                    }
                    break;
                case 2: 
                    System.out.println("Please enter old password: ");
                    String oldPass = prompt();
                    System.out.println("Please enter new password: ");
                    String newPass = prompt();
                    if(!campManager.setPass(oldPass, newPass)){
                        System.out.println("Password change unsuccessful.");
                        prompt(true);
                    }
                    System.out.println("Password change successful.");
                    prompt(true);
                    break;
                case 3:
                    // Prompt to update phone
                    break;
                case 4:
                    // Prompt to update question and answer
                    break;
                default: System.out.println("Something went wrong!");
            }
        }while(selection!=0);
    }


    /**
     * Prompts the user for input, and returns the input string directly.
     * Optional toggle to replace the '>' with 'Press enter to continue...'
     * @return The String that the user inputted
     */
    private String prompt(){
        return prompt(false);
    }

    /**
     * Prompts the user for input, and returns the input string directly.
     * Optional toggle to replace the '>' with 'Press enter to continue...'
     * @param hide A boolean
     * @return The String that the user inputted
     */
    private String prompt(boolean hide){
        if(!hide) 
            System.out.print("> ");
        else
            System.out.println("Press enter to continue...");
        return scan.nextLine();
    }

    /**
     * Prompts the user for a date string, repeatedly asking until they supply a valid date
     * @return The Date the user inputted
     */
    private Date promptDate() {
        DateFormat parser = DateFormat.getDateInstance(DateFormat.SHORT);
        ParsePosition pos = new ParsePosition(0);
        System.out.print("> ");
        String input = scan.nextLine();
        while(parser.parse(input,pos)==null){
            System.out.print("Invalid input, please try again: \n> ");
            input = scan.nextLine();
        }
        return parser.parse(input, pos);
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
        //(idk why we would allow negative numbers, but just in case i guess)
        String filter = "-?\\d+";
        while(!input.matches(filter) || Integer.parseInt(input)<min || Integer.parseInt(input)>max){
            System.out.print("Invalid input, please try again: \n> ");
            input = scan.nextLine();
        }
        return Integer.parseInt(input);
    }

    /**
     * This clears the console screen according to this post:
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