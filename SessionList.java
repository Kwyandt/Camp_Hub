import java.util.ArrayList;
import java.util.Date;

public class SessionList {
    
    private ArrayList<Session> sessions;
    private static SessionList sessionList;

    
    /** 
     * Private constructor for singleton.
     * @return SessionList
     */
    private SessionList() {
        sessions = new ArrayList<Session>();
    }

    
    /** 
     * Creates an instance of the class.
     * @return SessionList
     */
    public static SessionList getInstance() {
        if(sessionList == null) {
            sessionList = new SessionList();
            return null;
        }
        return sessionList;
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
        Session session = new Session(theme, priorityDeadline, regularDeadline, start);
        sessions.add(session);
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

    public ArrayList<Session> getAllSessions() {
        return sessions;
    }

}
