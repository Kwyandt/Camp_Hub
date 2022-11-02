import java.text.SimpleDateFormat;
import java.util.*;

import Users.*;

public class CampManager{
    private User currentUser;

    public CampManager() {
        currentUser = null;
    }

    public boolean createUser(UserType type, String email,  String pass, 
                        String first, String last, String phone, Date birth, 
                        Map<String, String> securityQuestion) {
        
        if(UserList.getInstance().getUser(email)!=null)
            //Can't create account because one with that email already exists!
            return false;
        //Creates the account
        switch(type){
            case PARENT:
                UserList.getInstance().addUser(new Parent(email, pass, first, last, phone, birth, securityQuestion));
            break;
            case DIRECTOR:
                UserList.getInstance().addUser(new Director(email, pass, first, last, phone, birth, securityQuestion));
            break;
            case COUNSELOR:
                UserList.getInstance().addUser(new Counselor(email, pass, first, last, phone, birth, securityQuestion));
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
    public boolean loginUser(String email, String pass) {
        User user = UserList.getInstance().getUser(email);
        if(user!=null && user.getPassword().equals(pass)){
            this.currentUser = user;
            return true;
        }
        return false;
    }

    /**
     * Does whatever processes are necessary to 
     */
    public boolean logoutUser() {
        if (currentUser==null)
            //Logout wasn't successful, return false
            return false;

        DataWriter.saveAllUsers(UserList.getInstance().getAllUsers());
        DataWriter.saveCamp(Camp.getInstance());
        // Insert save user info here?
        currentUser=null;
        //Logout was successful, return true
        return true;
    }

    /**
     * Verifies that the current user actually has the ability to perfom the action.
     * Each utility method in the manager will first check using this method that the currentUser's type is correct.
     * Some abilities have multiple allowed user types, so this should help that a little bit.
     * @param userTypes A String representing the allowed users. 'c' means counselors, 'p' parents, and 'd' directors
     * @return True if the currentUser's type matches one of the characters in the userType string
     */
    private boolean checkPermissions(String userTypes) {
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
    public boolean addCamper(String first, String last, Date birth, 
                        String[] guardian, String[] doctor, String[] dentist,
                        String tshirt) {
        if(!checkPermissions("p")) {
            //The current user isn't allowed here, return false to indicate the operation failed
            return false; 
        }

        Map<Relationship, EmergencyContact> contacts = new TreeMap<Relationship, EmergencyContact>();
        contacts.put(Relationship.DENTIST, new EmergencyContact(dentist[0], dentist[1], dentist[2], dentist[3]));
        contacts.put(Relationship.GUARDIAN, new EmergencyContact(guardian[0], guardian[1], guardian[2], guardian[3]));
        contacts.put(Relationship.DOCTOR, new EmergencyContact(doctor[0], doctor[1], doctor[2], doctor[3]));

        //Tell the appropriate classes to add a camper object
        ((Parent)currentUser).createCamper(new Camper(first, last, birth, contacts, tshirt));
        //Return true since the operation was successful
        return true;
    }
    public boolean registerCamper(Camper camper, Session session) {
        if(!checkPermissions("p"))
            return false;
        return session.addCamper(camper);
    }
    public boolean unregisterCamper(Camper camper, Session session) {
        if(!checkPermissions("p"))
            return false;
        return false;
    }

    /**
     * For parents, returns a list of the current user's campers
     * @return ArrayList<String> The list of children
     */
    public ArrayList<Camper> getChildren(){
        if(!checkPermissions("p"))
            return null;
        return ((Parent) currentUser).getChildren();
    }




    //director specific
    public boolean addActivity(String name, String description, String location) {
        if(!checkPermissions("d"))
            return false;
        Camp.getInstance().addActivity(new Activity(name, description, location));
        return true;
    }
    public boolean removeActivity(int index) {
        if(!checkPermissions("d"))
            return false;
        return Camp.getInstance().removeActivity(index);
    }

    public ArrayList<Activity> getActivities() {
        if(!checkPermissions("d"))
            return null;
        return Camp.getInstance().getActivities();
    }

    public boolean addSession(String theme, String description, Date priorityDate, Date regularDate, Date startDate, Date endDate) {
        if(!checkPermissions("d"))
            return false;
        SessionList.getInstance().addSession(new Session(theme, description, priorityDate, regularDate, startDate, endDate));
        return true;
    }
    public boolean removeSession(int index) {
        if(!checkPermissions("d"))
            return false;
        return SessionList.getInstance().removeSession(index);
    }

    public ArrayList<Session> getSessions() {
        if(!checkPermissions("dcp"))
            return null;
        return SessionList.getInstance().getAllSessions();
    }

    public ArrayList<Cabin> getCabins(Session session) {
        if(!checkPermissions("d"))
            return null;
        return session.getCabins();
    }

    /**
     * Switches counselor of cabin1 and cabin2
     * @param session session with cabin1 and cabin2
     * @param cabin1 first cabin
     * @param cabin2 second cabin
     * @return if swap was successfully completed
     */
    public boolean swapCounselor(Session session, int cabin1, int cabin2) {
        if(!checkPermissions("d"))
            return false;
        Counselor temp = session.getCabin(cabin1).getCounselor();
        session.getCabin(cabin1).setCounselor(session.getCabin(cabin2).getCounselor());
        session.getCabin(cabin2).setCounselor(temp);
        return true;
    }

    /**
     * Swaps cabin assignment of camper1 and camper2
     * @param session session containing camper1 and camper2
     * @param camper1 session-wide index of camper1
     * @param camper2 session-wide index of camper2
     * @return if swap was succesfully completed
     */
    public boolean swapCamper(Session session, int camper1, int camper2) {
        if(!checkPermissions("d"))
            return false;
        
        Cabin cabin1 = session.getCabin(camper1 / 8);
        Cabin cabin2 = session.getCabin(camper2 / 8);
        Camper temp = cabin1.getCampers()[camper1 % 8];
        cabin1.getCampers()[camper1 % 8] = cabin2.getCampers()[camper2 % 8];
        cabin2.getCampers()[camper2 % 8] = temp;
        return true;
    }
    
    /**
     * For directors, adds a new faq question
     * @param q String the question to add
     * @param a String the answer to add
     */
    public boolean addFAQ(String question, String answer) {
        if(!checkPermissions("d"))
            return false;
        Camp.getInstance().addFAQ(question, answer);
        return true;
    }
    
    /**
     * For directors, removes a given faq question.
     * @param question question being removed
     * @return boolean true or false if removed or not
     * 
     * NOTE: for the return it should also account for the fact that the question may not exist in the FAQ and thereore can't be removed
     */
    public boolean removeFAQ(String question) {
        if(!checkPermissions("d"))
            return false;
        Camp.getInstance().removeFAQ(question);
        return true;
    }

    /**
     * for directors, adds activity to specific schedule
     * @param schedule schedule being edited
     * @param activity activity being added
     * @param date date of actitit
     * @return yes or no for permission to edit schedule
     */
    public boolean addScheduleActivity(Schedule schedule, Activity activity, Date date) {
        if(!checkPermissions("d"))
            return false;
        Camp.getInstance().addScheduleActivity(schedule, activity, date);
        return true;
    }

    /**
     * for directors, removes activity from specifit schedule
     * @param schedule schedule being edited
     * @param activity activity being removes
     * @param date date of activity
     * @return yes or no for permission to edit schedule
     */
    public boolean removeScheduleActivity(Schedule schedule, Activity activity, Date date) {
        if(!checkPermissions("d"))
            return false;
        Camp.getInstance().removeScheduleActivity(schedule, activity, date);
        return true;
    }

    //set pricing
    public boolean setPricing(Session session, double price) {
        if(!checkPermissions("d"))
            return false;
        Camp.getInstance().setPricing(session, price);
        return true;
    }

    public String getSchedule(int sessionIndex, int cabinIndex){
        return getSessions().get(sessionIndex).getCabin(cabinIndex).viewSchedule(sessionIndex+1);
    }

    public void writeFiles(int sessionIndex, int cabinIndex){
        Session session = SessionList.getInstance().getSession(sessionIndex);
        Cabin c = session.getCabin(cabinIndex);
        DataWriter.writeCabinRoster(c.getCabinRoster(), session.getTheme(), sessionIndex+1);
        DataWriter.writeCabinSchedule(c.getSchedule().displayOrderedSchedule(sessionIndex, cabinIndex), session.getTheme(), sessionIndex+1);
        DataWriter.writeCabinVitals(c.getAllCampersInfo(), session.getTheme(), sessionIndex+1);
    }

    public double getPricing(Session session, Parent parent) {
        return session.getPrice() *  parent.getDiscount();
    }

    public boolean addPackingItem(String item) {
        if(!checkPermissions("d"))
            return false;
        Camp.getInstance().addPackingItem(item);
        return true;
    }
    public boolean removePackingItem(int index) {
        if(!checkPermissions("d"))
            return false;
        return Camp.getInstance().removePackingItem(index);
    }

    /**
     * Shows all sessions a counselor is registered for
     * @return formatted String containing all of the currently logged in counselor's registrations
     */
    public String getRegistrationsView() {
        if(!checkPermissions("c"))
            return null;
        Counselor c = (Counselor)currentUser;
            String ret = "You are currently registered for ";

        ArrayList<Session> list = SessionList.getInstance().getCounselorSessions(c);
        ret += list.size() + " sessions.\n";

        if(list.size() == 0)
            return ret+"No registrations! Go to the main user page to register.";

        ret+="\n";
        for(int i = 0; i  <  list.size(); i++) {
            Session s = list.get(i);
            ret += String.format("%-20s%s%n", "Theme:", s.getTheme());
            ret += String.format("%-20s%s - %s%n%n", "Dates:", formatDate(s.getStartDate()),
                                formatDate(s.getEndDate()));
            //ret += String.format("%-20s%s%n", "Priority deadline:", formatDate(s.getPriorityDeadline()));
            //ret += String.format("%-20s%s%n", "Regular deadline:", formatDate(s.getRegularDeadline()));
            //ret += String.format("%-20s$%.02f%n", "Price:", s.getPrice());

            for(int j=0;j<s.getCabins().size();j++) {
                Cabin cab = s.getCabin(j);
                if(cab.counselorInCabin(c)){
                    writeFiles(2, j);
                    ret+=cab.getCabinRoster() + "\n";
                    ret+="Information has been written to a txt file\n";

                }
            }
            ret+="\n";
        }
       return ret;
    }

    /**
     * Shows all sessions a camper is registered for
     * @param c Camper to view registrations of
     * @return formatted String containing all of the camper's registrations
     */
    public String getRegistrationsView(Camper c) {
        if(!checkPermissions("p"))
            return null;
        String ret = c.getFirst()+" is currently registered for ";

        ArrayList<Session> list = SessionList.getInstance().getCamperSessions(c);
        ret += list.size() + " sessions.\n";

        if(list.size() == 0)
            return ret+"No registrations! Go to the main user page to register.";

        ret+="\n";
        for(int i = 0; i  <  list.size(); i++){
            Session s = list.get(i);
            ret += String.format("%-20s%s%n", "Theme:", s.getTheme());
            ret += String.format("%-20s%s - %s%n", "Dates:", formatDate(s.getStartDate()),
                                formatDate(s.getEndDate()));
            //ret += String.format("%-20s%s%n", "Priority deadline:", formatDate(s.getPriorityDeadline()));
            //ret += String.format("%-20s%s%n", "Regular deadline:", formatDate(s.getRegularDeadline()));
            //ret += String.format("%-20s$%.02f%n", "Price:", s.getPrice());
            for(Cabin cab : s.getCabins()){
                if(cab.camperInCabin(c))
                    ret+=cab.getCabinRoster() + "\n";
            }
            ret+="\n";
        }
       return ret;
    }


    //counselor specific
    public boolean registerCounselor(Session session) {
        if(!checkPermissions("c"))
            return false;
        session.addCounselor(((Counselor)currentUser));
        return true;
    }
    public boolean unregisterCounselor(Session session) { 
        if(!checkPermissions("c"))
            return false;
        return false;
    }
    public boolean addNote(String note) {
        if(!checkPermissions("c")) 
            return false;
        ((Counselor) currentUser).addNote(note);
        return true;
    }
    public boolean removeNote(int index) {
        if(!checkPermissions("c")) 
            return false;
        ((Counselor) currentUser).removeNote(index);
        return true;
    }
    public ArrayList<String> getNotes() {
        if(!checkPermissions("c")) 
            return null;
        return ((Counselor) currentUser).getNotes();
    }
    
    //shared by certain users
    /**
     * Set's the current user's bio to the given bio (if they're a director or counselor only)
     * @param bio String the bio to set
     * @return boolean indicating if the operation was successful
     */
    public boolean setBio(String bio) {
        if(!checkPermissions("dc")) 
            return false;
        if(currentUser.getUserType()==UserType.COUNSELOR)
            ((Counselor)currentUser).addBio(bio);
        else
            ((Director)currentUser).addBio(bio);
        return true;
    }

     /**
     * Retrieves the user's bio, returning null if the user is a parent,
     * and an empty string if the user has not set thier bio
     * @return String the bio of the current user
     */
    public String getBio() {
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
    
    public String getAboutPage() {
        String info = "Camp Information:\n"+Camp.getInstance().getName()+"\n\nFAQ:\n";
        for(String key : Camp.getInstance().getFAQs().keySet()){
            info += String.format("Q: %s%nA: %s%n", key, Camp.getInstance().getFAQs().get(key));
        }
        info += "\nSuggested Packing List\n";
        for(int i = 0; i < Camp.getInstance().getPackingList().size(); i++){
            info += String.format("%d. %s%n", i+1, Camp.getInstance().getPackingList().get(i));
        }
        return info;
    }

    public boolean setEmail(String email) {
        if(currentUser==null)
            return false;
        if(UserList.getInstance().getUser(email)!=null)
            return false;
        currentUser.setEmail(email);
        return true;
    }

    public boolean setPass(String oldPass, String newPass) {
        if(currentUser==null)
            return false;
        return currentUser.changePassword(oldPass, newPass);
    }    

    public boolean setPhone(String phone) {
        if(currentUser==null)
            return false;
        currentUser.setPhone(phone);
        return true;
	}

    // Accessors
    public Camp getCamp() {
        return Camp.getInstance();
    }
    public UserList getUserList() {
        return UserList.getInstance();
    }
    public User getUser() {
        return currentUser;
    }
    public UserType getType() {
        return currentUser.getUserType();
    }

    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        return sdf.format(date);
    }
}