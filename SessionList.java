import java.util.ArrayList;
import java.util.Date;

import Users.*;

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
        if (sessionList == null) {
            sessionList = new SessionList();
            return sessionList;
        }
        return sessionList;
    }

    /** 
     * Adds a session with the given parameters.
     * @param theme
     * @param description
     * @param priorityDeadline
     * @param regularDeadline
     * @param start
     * @param end
     */
    public void addSession(String theme, String description, Date priorityDeadline, Date regularDeadline, Date start, Date end) { 
        sessions.add(new Session(theme, description, priorityDeadline, regularDeadline, start, end));
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
     * @param start Start date of session to get
     * @return Desired session, or null if not found
     */
    public Session getSession(Date start) {
        for (Session session : sessions)
            if (session.getStartDate().equals(start))
                return session;
        // We didn't find it, so return null
        return null;
    }

    /**
     * Gets the session at given index
     * @param index Index in sessions ArrayList
     * @return Session at index
     */
    public Session getSession(int index) {
        return sessions.get(index);
    }

    /**
     * Replaces the session in the list with the same start date
     * with the session being passed in. 
     * @param session Replaces session in list with the same start date.
     */
    public boolean editSession(Session session) {
        for (int i = 0; i < sessions.size(); i++) {
            if (session.getStartDate().equals(sessions.get(i).getStartDate())) {
                sessions.set(i, session);
                return true;
            }
        }
        // We didn't find the session, so return false
        return false;
    }

    /**
     * Replaces session at index with parameter session
     * @param index Index of session to edit
     * @param session Session to replace one at index
     * @return True iff edit was sucessful
     */
    public boolean editSession(int index, Session session) {
        try {
            sessions.set(index, session);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Removes a session starting with date
     * @param start Start date of session to remove
     * @return True iff removal was sucessful
     */
    public boolean removeSession(Date start) {
        for (int i = 0; i < sessions.size(); i++) {
            if (sessions.get(i).getStartDate().equals(start)) {
                sessions.remove(i);
                return true;
            }
        }
        // We didn't find it, so return false
        return false;
    }

    /**
     * Removes session at index
     * @param index Index to remove session at
     * @return True iff removal was sucessful
     */
    public boolean removeSession(int index) {
        try {
            sessions.remove(index);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Return all sessions
     * @return ArrayList of sessions
     */
    public ArrayList<Session> getAllSessions() {
        return sessions;
    }

    /**
     * Provides an ArrayList of sessions containing specified camper
     * @param c camper to get all sessions for
     * @return all sessions containing camper
     */
    public ArrayList<Session> getCamperSessions(Camper c) {
        ArrayList<Session> ret = new ArrayList<Session>();
        for(Session s: sessions) {
            for(Cabin cab: s.getCabins()) {
                if(cab.camperInCabin(c)) {
                    ret.add(s);
                }
            }
        }
        return ret;
    }
    
     /**
     * Provides an ArrayList of sessions containing specified counselor
     * @param c counselor to get all sessions for
     * @return all sessions containing counselor
     */
    public ArrayList<Session> getCounselorSessions(Counselor c) {
        ArrayList<Session> ret = new ArrayList<Session>();
        for(Session s: sessions) {
            for(Cabin cab: s.getCabins()) {
                if(cab.counselorInCabin(c)) {
                    ret.add(s);
                }
            }
        }
        return ret;
    }

    /**
     * Return String representation of SessionList
     * @return String representing SessionList
     */
    public String toString() {
        return sessions.toString();
    }

    public static void clear() {
        sessionList = new SessionList();
    }

}
