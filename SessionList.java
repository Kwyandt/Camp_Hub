import java.util.ArrayList;
import java.util.Date;
import java.util.NoSuchElementException;

public class SessionList {
    
    private ArrayList<Session> sessions;
    private static SessionList sessionList;

    /** 
     * Private constructor for singleton.
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
            return sessionList;
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
        Session session = new Session(theme, priorityDeadline, regularDeadline, start, end);
        sessions.add(session);
    }

    /**
     * Adds an existing session object to the SessionList.
     * Most useful for loading in existing sessions.
     * @param session Session to add to list
     */
    public void addSession(Session session) {
        sessions.add(session);
    }

    /** 
     * Gets the session based on start.
     * @param start
     * @return Session
     */
    public Session getSession(Date start) {
        for (Session session : sessions)
            if (session.getStartDate().equals(start))
                return session;
        return null;
    }

    /**
     * Replaces the session in the list with the same start date
     * with the session being passed in. 
     * @throws NoSuchElementException if there is no session in the list
     * with a matching date as session
     * @param session Replaces session in list with the same start date.
     */
    public void editSession(Session session) throws NoSuchElementException {
        for (int i = 0; i < sessions.size(); i++)
            if (session.getStartDate().equals(sessions.get(i).getStartDate()))
                sessions.set(i, session);
        // We didn't find the session, so throw an exception
        throw new NoSuchElementException();
    }

    /**
     * Return all sessions
     * @return ArrayList of sessions
     */
    public ArrayList<Session> getAllSessions() {
        return sessions;
    }

    /**
     * Return String representation of SessionList
     * @return String representing SessionList
     */
    public String toString() {
        return sessions.toString();
    }

}
