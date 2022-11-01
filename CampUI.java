import java.util.*;

import Users.*;

import java.io.IOException;
import java.text.SimpleDateFormat;


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
        "3. Manage Notes\n4. Manage Bio\n5. Manage account\n6. Edit medical information",
        // The director user menu (3)
        "Please select:\n0. Sign out\n1. Manage camp sessions\n2. Manage camp activities\n"+
        "3. Manage camp FAQ\n4. Manage bio\n5. Manage account\n",
        // The manage campers menu (4)
        "Please select:\n0. Go back\n1. Add camper\n2. Edit camper information\n",
        // The register camper menu (after you select a session to register for) (5)
        "Please select a camper to register:\n0. Go back\n1. Add camper",
        // The manage account menu (6)
        "Please select:\n0. Go back\n1. Update email\n2. Update password\n3. Update phone number\n4. Update security question\n",
        // The manage bio menu (7)
        "Please select:\n0. Go back\n1. Update bio\n",
        // The manage notes menu (8)
        "Please select:\n0. Go back\n1. Add note\n2. Remove note\n",
        // The manage discounts menu (9)
        "Please select:\n0. Go back\n1. Set discount\n",
        // The manage FAQs menu (10)
        "Please select:\n0. Go back\n1. Add FAQ\n2. Remove FAQ\n",
        // The camper edit menu (11)
        "Please select:\n0.  Go back\n1.  Add allergy\n2.  Remove allergy"+
        "\n3.  Add diet restriction\n4.  Remove diet restriction\n5.  Add medication\n6.  Remove medication\n"+
        "7.  Update guardian\n8.  Update doctor\n9.  Update dentist\n10. Update other\n",
        // The manage session for director menu (12)
        "Please select:\n0. Go back\n1. Update theme\n2. Update description\n3. Add Activity\n4. Remove Activity\n5. Manage cabin assignments\n",
        // the manage activites for directors menu (13)
        "Please select:\n0. Go back\n1. Add activity\n2. Remove activity\n",
        // the manage cabins for directors menu (14)
        "Please select:\n0. Go back\n1. Move Counselor\n2. Move Camper\n"
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
            "Please enter birthdate (dd-MMM-yyyy):",
            "Please select security question:\n",
            "Please answer the question you selected:",
        },
        {   // The prompts for loginUser()
            "Please enter email:",
            "Please enter password:"
        },
        {   // The prompts for createCamper()
            "Please enter camper first name:",
            "Please enter camper last name:",
            "Please enter camper birth date (dd-MMM-yyyy):",
            "Please enter camper tshirt size:",
            "Please enter guardian contact first name:",
            "Please enter guardian contact last name:",
            "Please enter guardian contact phone:",
            "Please enter guardian contact location (address/office):",
            "Please enter doctor contact first name:",
            "Please enter doctor contact last name:",
            "Please enter doctor contact phone:",
            "Please enter doctor contact location (address/office):",
            "Please enter dentist contact first name:",
            "Please enter dentist contact last name:",
            "Please enter dentist contact phone:",
            "Please enter dentist contact location (address/office):",
            "Please enter dentist contact first name:",
            "Please enter dentist contact last name:",
            "Please enter dentist contact phone:",
            "Please enter dentist contact location (address/office):"
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
                    selection = promptInt(0,6);
                    switch(selection){
                        case 0: logoutUser(); break;
                        case 1: viewSessions(); break;
                        case 2: viewRegistrations(); break;
                        case 3: manageNotes(); break;
                        case 4: manageBio(); break;
                        case 5: manageAccount(); break;
                        case 6: editCounselor(); break;
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
        final int FORM = 2;
        System.out.println("Add Camper Form:\n");
        String[] inputs = new String[forms[FORM].length-1];
        Date birth = null;
        for(int i=0; i < forms[FORM].length - 4; i++){
            System.out.println(forms[FORM][i]);
            if(i==2)
                birth = promptDate();
            else if(i>2)
                inputs[i-1] = prompt();
            else
                inputs[i] = prompt();
        }
        String[] guardian = new String[]{inputs[3], inputs[4],inputs[4],inputs[6]};
        String[] doctor = new String[]{inputs[7], inputs[8],inputs[9],inputs[10]};
        String[] dentist = new String[]{inputs[11], inputs[12],inputs[13],inputs[14]};
        boolean  success = campManager.addCamper(inputs[0], inputs[1], birth, guardian, doctor, dentist, inputs[2]);
        if(success){
            System.out.println("Camper created sucessfully! Be sure to update their allergies/meds.");
            prompt(true);
        }
        else{
            System.out.println("Camper creation failed, check information and try again...");
            prompt(true);
        }
        
    }

    public void editCounselor(){
        final int MENU = 11;
        int selection = 0;
        Counselor counselor = (Counselor)campManager.getUser();
        do{
            clearScreen();
            System.out.println("Viewing medical information:\n");
            System.out.printf("%-10s%s %s%n","Name: ", 
                counselor.getFirstName(), counselor.getLastName());
            System.out.printf("%-10s%s (Age: %d)%n","Birth: ", 
                    formatDate(counselor.getBirthDate()), counselor.getAge());
            System.out.printf("%-10s%s%n","Tshirt: ",
                counselor.getTShirt());
            System.out.println("Allergies:");
            for(int i = 0;  i < counselor.getAllergies().size(); i++) {
                System.out.printf("    %d. %s%n", i + 1, counselor.getAllergies().get(i));
            }
            System.out.println("Dietary Restrictions:");
            for(int i = 0;  i < counselor.getDietaryRestrictions().size(); i++){
                System.out.printf("    %d. %s%n",i+1,counselor.getDietaryRestrictions().get(i));
            }
            System.out.println("Medications:");
            for(int i = 0;  i < counselor.getMeds().size(); i++){
                System.out.printf("    %d. %s%n",i+1,counselor.getMeds().get(i));
            }
            Map<Relationship, EmergencyContact> contacts = counselor.getEmergencyContact();
            for(Relationship key : contacts.keySet()){
                System.out.printf("%n%-20s%s%n",key+" first:", contacts.get(key).getFirst());
                System.out.printf("%-20s%s%n",key+" last:", contacts.get(key).getLast());
                System.out.printf("%-20s%s%n",key+" phone:", contacts.get(key).getPhone());
                System.out.printf("%-20s%s%n",key+" location:", contacts.get(key).getLocation());
            }

            System.out.println("\n"+menus[MENU]);

            selection = promptInt(0,9);

            int input =0;
            switch(selection){
                case 0: break;
                case 1: 
                    System.out.println("Please enter allergy:");
                    counselor.addAllergy(prompt());
                    break;
                case 2: 
                    System.out.println("Please enter number to remove (0 to go back):");
                    input = promptInt(0,counselor.getAllergies().size());
                    if(input==0) break;
                    counselor.removeAllergy(input-1);
                    break;
                case 3: 
                    System.out.println("Please enter dietary restriction:");
                    counselor.addDietaryResriction(prompt());
                    break;
                case 4: 
                    System.out.println("Please enter number to remove (0 to go back):");
                    input = promptInt(0,counselor.getDietaryRestrictions().size());
                    if(input==0) break;
                    counselor.removeDietaryRestsriction(input-1);
                    break;
                case 5: 
                    System.out.println("Please enter medication:");
                    counselor.addMed(prompt());
                    break;
                case 6: 
                    System.out.println("Please enter number to remove (0 to go back):");
                    input = promptInt(0,counselor.getMeds().size());
                    if(input==0) break;
                    counselor.removeMed(input-1);
                    break;
                case 7:
                    System.out.println("Please enter tshirt size:");
                    counselor.setTShirt(prompt());
                    break;    
                case 8:
                case 9:
                case 10:
                case 11:
                    String[] inputs = new String[4];
                    //loop through the four prompts for emergency contact
                    for(int i = 0; i < 4; i++){
                        System.out.println(forms[2][(selection - 7)*4 + i]);
                        inputs[i] = prompt();
                    }
                    EmergencyContact contact = new EmergencyContact(inputs[0], inputs[1], inputs[2], inputs[3]);
                    if(selection==7)
                        counselor.addContact(Relationship.GUARDIAN, contact);
                    else if(selection==8)
                        counselor.addContact(Relationship.DOCTOR, contact);
                    else if(selection==9)
                        counselor.addContact(Relationship.DENTIST, contact);
                    else 
                        counselor.addContact(Relationship.OTHER, contact);
                    break;
                default: System.out.println("Something went wrong!");
            }
        }while(selection!=0);
    }
    
    public void editCamper(int index){
        final int MENU = 11;
        int selection = 0;
        Camper camper = campManager.getChildren().get(index);
        do{
            clearScreen();
            System.out.println("Viewing Camper:\n");
            System.out.printf("%-10s%s %s%n","Name: ", 
                    camper.getFirst(),camper.getLast());
            System.out.printf("%-10s%s (Age: %d)%n","Birth: ", 
                    formatDate(camper.getBirth()),camper.getAge());
            System.out.printf("%-10s%s%n","Tshirt: ",
                    camper.getTShirt());
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
            Map<Relationship, EmergencyContact> contacts = camper.getEmergencyContact();
            for(Relationship key : contacts.keySet()){
                System.out.printf("%n%-20s%s%n",key+" first:", contacts.get(key).getFirst());
                System.out.printf("%-20s%s%n",key+" last:", contacts.get(key).getLast());
                System.out.printf("%-20s%s%n",key+" phone:", contacts.get(key).getPhone());
                System.out.printf("%-20s%s%n",key+" location:", contacts.get(key).getLocation());
            }

            System.out.println("\n"+menus[MENU]);

            selection = promptInt(0,9);

            int input =0;
            switch(selection){
                case 0: break;
                case 1: 
                    System.out.println("Please enter allergy:");
                    camper.addAllergy(prompt());
                    break;
                case 2: 
                    System.out.println("Please enter number to remove (0 to go back):");
                    input = promptInt(0,camper.getAllergy().size());
                    if(input==0) break;
                    camper.removeAllergy(input-1);
                    break;
                case 3: 
                    System.out.println("Please enter dietary restriction:");
                    camper.addDietaryResriction(prompt());
                    break;
                case 4: 
                    System.out.println("Please enter number to remove (0 to go back):");
                    input = promptInt(0,camper.getDietaryRestrictions().size());
                    if(input==0) break;
                    camper.removeDietaryRestsriction(input-1);
                    break;
                case 5: 
                    System.out.println("Please enter medication:");
                    camper.addMed(prompt());
                    break;
                case 6: 
                    System.out.println("Please enter number to remove (0 to go back):");
                    input = promptInt(0,camper.getMeds().size());
                    if(input==0) break;
                    camper.removeMed(input-1);
                    break;
                case 7:
                    System.out.println("Please enter tshirt size:");
                    camper.setTShirt(prompt());
                    break;    
                case 8:
                case 9:
                case 10:
                case 11:
                    String[] inputs = new String[4];
                    //loop through the four prompts for emergency contact
                    for(int i = 0; i < 4; i++){
                        System.out.println(forms[2][(selection - 7)*4 + i]);
                        inputs[i] = prompt();
                    }
                    EmergencyContact contact = new EmergencyContact(inputs[0], inputs[1], inputs[2], inputs[3]);
                    if(selection==7)
                        camper.addContact(Relationship.GUARDIAN, contact);
                    else if(selection==8)
                        camper.addContact(Relationship.DOCTOR, contact);
                    else if(selection==9)
                        camper.addContact(Relationship.DENTIST, contact);
                    else 
                        camper.addContact(Relationship.OTHER, contact);
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
        int selection = 0;
        final int MENU = 12;
        do{
            clearScreen();
            System.out.println("Managing session:\n\n");
            Session session = campManager.getSessions().get(index);
            System.out.printf("%-20s%s%n", "Theme:", session.getTheme());
            System.out.printf("%-20s%s%n", "Description:", session.getDescription());
            System.out.printf("%-20s%s - %s%n", "Dates:", formatDate(session.getStartDate()),
                                formatDate(session.getEndDate()));
            System.out.printf("%-20s%s%n", "Priority deadline:", formatDate(session.getPriorityDeadline()));
            System.out.printf("%-20s%s%n", "Regular deadline:", formatDate(session.getRegularDeadline()));
            System.out.printf("%-20s$%.02f%n", "Price:", session.getPrice());
            System.out.println("Activities:");
            //for(int i=0;i<session.

            System.out.println("\n"+menus[MENU]);

            selection = promptInt(0, 5);
            switch(selection){
                case 0: break;
                case 1:
                    System.out.println("Please enter new theme:");
                    session.setTheme(prompt());
                    break;
                case 2:
                    System.out.println("Please enter new description:");
                    session.setDescription(prompt());
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    manageCabins(session);
                    break;
                default: System.out.println("Something went wrong");
            }
            
        }while(selection!=0);
    }
    
    public void addSession(){
        clearScreen();
        System.out.println("Creating session:\n\n");
        System.out.println("Please enter a theme:");
        String theme = prompt();
        System.out.println("Please enter a description:");
        String description = prompt();
        System.out.println("Please enter a start date for session (dd-MMM-yyyy):");
        Date start = promptDate();
        System.out.println("Please enter a end date for session (dd-MMM-yyyy):");
        Date end = promptDate();
        System.out.println("Please enter a deadline for priority registration (dd-MMM-yyyy):");
        Date priority = promptDate();
        System.out.println("Please enter a deadline for regular registration (dd-MMM-yyyy):");
        Date regular = promptDate();
        
        if(campManager.addSession(theme, description, priority, regular, start, end)){
            System.out.println("\nSession created successfully!");
        }
        else{
            System.out.println("\nSession creation failed... try again?");
        }
        prompt(true);
    }

    public void manageActivities(){
        final int MENU = 13;
        int selection = 0;
        do{
            clearScreen();
            System.out.println("Manage Activities:\n\nCurrent Activities:");
            ArrayList<Activity> activities = campManager.getActivities();
            for(int i=0;i<activities.size();i++){
                System.out.printf("%-10.00f%s | %s | %s%n",(float) (i+1), activities.get(i).getName(),
                                        activities.get(i).getDescription(), activities.get(i).getLocation());
            }

            System.out.println("\n\n"+menus[MENU]);
            selection = promptInt(0,2);

            switch(selection){
                case 0: exit(); break;
                case 1: 
                    System.out.println("Please enter activity name:");
                    String name = prompt();
                    System.out.println("Please enter activity description:");
                    String description = prompt();
                    System.out.println("Please enter activity location:");
                    String location = prompt();
                    if(!campManager.addActivity(name, description, location)){
                        System.out.println("Add activity was unsuccessful... try again?");
                        prompt(true);
                    }
                break;
                case 2:
                    System.out.println("Please enter a number to remove (0 to cancel)");
                    int input = promptInt(0, activities.size());
                    if(input == 0) 
                        break;
                    if(!campManager.removeActivity(input-1)){
                        System.out.println("Remove activity was unsuccessful... try again?");
                        prompt(true);
                    }
                default: System.out.println("Something went wrong!");
            }
        }while(selection!=0);
    }

    public void manageFAQ(){
        final int MENU = 10;
        int selection = 0;
        do{
            clearScreen();
            System.out.println("Manage FAQs:\n\nCurrent FAQs:");
            Map<String,String> faqs = campManager.getCamp().getFAQs();
            String[] questions = new String[faqs.size()];
            int index = 0;
            for(String key : faqs.keySet()){
                System.out.printf("%d. Q: %s%n   A: %s%n", index+1, key, faqs.get(key));
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
                    campManager.removeFAQ(questions[input-1]);
                default: System.out.println("Something went wrong!");
            }
        }while(selection!=0);
    }
    
    public void manageDiscounts(){
        clearScreen();
        System.out.println("This is the manage discounts menu. I don't do anything yet");
        prompt(true);
    }

    public void manageCabins(Session session){
        final int MENU = 14;
        int selection = 0;
        do{
            clearScreen();
            System.out.println("Manage Cabin Assignments:\n\nCurrent cabins:\n");
            ArrayList<Cabin> cabins = campManager.getCabins(session);
            for(int i=0;i<cabins.size();i++){
                System.out.printf("Cabin %d:%n",i+1);
                if(cabins.get(i).getCounselor() != null) {
                    System.out.printf("    Counselor: %s %s%n",
                                cabins.get(i).getCounselor().getFirstName(),
                                cabins.get(i).getCounselor().getLastName());
                }
                else {
                    System.out.println("Counselor not yet assigned");
                }
                System.out.printf("    Campers: %n");
                Camper[] campers = cabins.get(i).getCampers();
                for(int j=0;j<campers.length;j++){
                    if(campers[j]!=null)
                        System.out.printf("          %-5d%s %s%n",8*i+j+1,
                                        campers[j].getFirst(),campers[j].getLast());
                    else
                        System.out.printf("          %d%n",8*i+j+1);
                }
            }
            System.out.println("\n"+menus[MENU]);
            selection = promptInt(0,2);
            int input1=0;
            int input2=0;

            switch(selection){
                case 0: exit(); break;
                case 1: 
                    System.out.println("Please enter first counselor to swap (enter cabin #):");
                    input1 = promptInt(0,cabins.size());
                    if(input1==0)
                        break;
                    System.out.println("Please enter second counselor to swap (enter cabin #):");
                    input2 = promptInt(0,cabins.size());
                    if(input2==0)
                        break;
                    if(!campManager.swapCounselor(session, input1-1, input2-1)){
                        System.out.println("Assignment unsuccessful... try again?");
                        prompt(true);
                    }
                break;
                case 2: 
                    System.out.println("Please enter first kid to swap (enter #):");
                    input1 = promptInt(1,cabins.size()*8);
                    System.out.println("Please enter second kid to swap (enter #):");
                    input2 = promptInt(1,cabins.size()*8);
                    if(!campManager.swapCamper(session, input1-1, input2-1)){
                        System.out.println("Assignment unsuccessful... try again?");
                        prompt(true);
                    }
                break;
                default: System.out.println("Something went wrong!");
            }
        }while(selection!=0);
    }

    //These are accessible only by counselors
    public void manageNotes(){
        final int MENU = 8;
        int selection = 0;
        do{
            clearScreen();
            System.out.println("Manage Notess:\n\nCurrent Notes:");
            ArrayList<String> notes = campManager.getNotes();
            for(int i=0; i<notes.size();i++){
                System.out.printf("%d. %s%n", i+1, notes.get(i));
            }
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
                    campManager.removeNote(input-1);
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
            clearScreen();
            System.out.println("Viewing Sessions:\n\n0. Go back");
            ArrayList<Session> sessions = campManager.getSessions();
            for (int i=0; i < sessions.size(); i++){
                System.out.printf("%d. %s | %s - %s%n",i+1,sessions.get(i).getTheme(), 
                                            formatDate(sessions.get(i).getStartDate()),
                                            formatDate(sessions.get(i).getEndDate()));
            }
            if(campManager.getUser().getUserType()==UserType.DIRECTOR)
                System.out.printf("%d. %s%n", sessions.size()+1, "Add new session...");

            System.out.println("\nPlease select a session to see more information:");
            if(campManager.getUser().getUserType()==UserType.DIRECTOR)
                selection = promptInt(0,sessions.size()+1);
            else
                selection = promptInt(0,sessions.size());
            
            if(selection==0)
                break;
            switch(campManager.getUser().getUserType()){
                case DIRECTOR:
                    if(selection==sessions.size()+1){
                        addSession();
                    }
                    else{
                        manageSession(selection-1);
                    }
                    break;
                case PARENT:
                case COUNSELOR:
                    registerForSession(selection-1);
                    break;
            }
        }while(selection != 0);
    }

    public void registerForSession(int index){
        int selection = 0;
        do{
            clearScreen();
            System.out.println("Registering for session!\n\n");
            Session session = campManager.getSessions().get(index);
            System.out.printf("%-20s%s%n", "Theme:", session.getTheme());
            System.out.printf("%-20s%s - %s%n", "Dates:", formatDate(session.getStartDate()),
                                formatDate(session.getEndDate()));
            System.out.printf("%-20s%s%n", "Priority deadline:", formatDate(session.getPriorityDeadline()));
            System.out.printf("%-20s%s%n", "Regular deadline:", formatDate(session.getRegularDeadline()));
            System.out.printf("%-20s$%.02f", "Price:", session.getPrice());

            switch(campManager.getUser().getUserType()){
                case COUNSELOR:
                    System.out.println("\nPlease Select:\n0. Go back\n1. Register for session");
                    selection = promptInt(0,1);
                    if(selection==1){
                        boolean success = campManager.registerCounselor(session);
                        if(success)
                            System.out.println("Successfully registered for camp!");
                        else
                            System.out.println("Registration unsucessful");
                        prompt(true);
                    }
                    break;
                case PARENT:
                    System.out.println("\nPlease select a camper to register:\n0. Go back");
                    int size = campManager.getChildren().size();
                    for(int i=0; i < size; i++){
                        Camper c = campManager.getChildren().get(i);
                        System.out.printf("%d. %s %s%n",i+1,c.getFirst(),c.getLast());
                    }
                    System.out.printf("%d. Add a camper...%n%n", size+1);
                    selection = promptInt(0, size+1);
                    if(selection==size+1)
                        addCamper();
                    else if(selection!=0){
                        boolean success = campManager.registerCamper(campManager.getChildren().get(selection-1), session);
                        if(success)
                            System.out.println("Successfully registered for camp!");
                        else  
                            System.out.println("Registration unsuccessful...");
                        prompt(true);
                    }
                    break;
                default:
            }
        }while(selection!=0);
    }

    //This method is accessible to counselors and parents
    public void viewRegistrations(){
        switch(campManager.getType()){
            case PARENT:
                ArrayList<Camper> kids = campManager.getChildren();
                int input = 0;
                do{
                    clearScreen();
                    System.out.println("Viewing Registrations:\n");
                    if(kids==null || kids.size()==0){
                        System.out.println("No campers added yet, go to main user page and add some first...");
                        prompt(true);
                        break;
                    }
                    System.out.println("Please select a camper to view registration info of:\n0. Go back");
                    for(int i = 0; i<kids.size();i++){
                        System.out.printf("%d. %s %s%n",i+1,kids.get(i).getFirst(),kids.get(i).getLast());
                    }
                    input = promptInt(0, kids.size());
                    if(input>0){
                        System.out.println(campManager.getRegistrationsView(kids.get(input-1)));
                        prompt(true);
                    }
                }while(input!=0);
            break;
            case COUNSELOR:
                clearScreen();
                System.out.println("Viewing Registrations:\n");
                System.out.println(campManager.getRegistrationsView());
                prompt(true);
            break;
            default: System.out.println("Something went wrong");
        }   
    }

    //These methods are accessible to any logged in user
    public void manageAccount(){
        final int MENU = 6;
        int selection = 0;
        User user = campManager.getUser();
        String format = "%-10s%s%n";
        
        do{
            clearScreen();
            System.out.println("Viewing Account Information:\n");

            System.out.printf(format,"name:",user.getFirstName()+" "+user.getLastName());
            System.out.printf(format,"email:",user.getEmail());
            System.out.printf(format,"phone:",user.getPhone());
            //currently null errors for some reason
            System.out.printf(format,"birth:",formatDate(user.getBirthDate()));

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
                    System.out.println("Please enter new phone: "); 
                    if(!campManager.setPhone(prompt())){
                        System.out.println("Phone change unsuccessful...");
                        prompt(true);
                    }
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
        System.out.print("> ");
        String input = scan.nextLine();
        while(getDate(input)==null){
            System.out.print("Invalid input, please try again: \n> ");
            input = scan.nextLine();
        }
        //System.out.println(formatDate(getDate(input)));
        return getDate(input);
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
    private void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}
    }

    private Date getDate(String str) {
        try {
            return new SimpleDateFormat("dd-MMM-yyyy").parse(str);
        } catch (Exception e) {
            return null;
        } 
    }

    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        return sdf.format(date);
    }


    public static void main(String[] args) {
        (new CampUI()).start();
    }
}