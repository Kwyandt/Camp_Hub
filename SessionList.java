import java.util.ArrayList;
import java.util.Date;

public class SessionList {
    
    private ArrayList<String> Session;
    private SessionList SessionList;

    
    /** 
     * Private constructor for singleton.
     * @return SessionList
     */
    private SessionList SessionList() {
        return null;
    }

    
    /** 
     * Creates an instance of the class.
     * @return SessionList
     */
    public SessionList getInstance() {
        return null;
    }

    
    /** 
     * Adds a session with the given parameters.
     * @param theme
     * @param priorityDeadline
     * @param regularDeadline
     * @param start
     * @param end
     */
    public void addSession(String theme, Date priorityDeadline, Date regularDeadline, Date start, Date end) {

    }

    
    /** 
     * Gets the session based on start.
     * @param start
     * @return Session
     */
    public Session getSession(Date start) {
        return null;
    }

    public void editSession() {

    }

    public void saveSessions() {

    }

}
