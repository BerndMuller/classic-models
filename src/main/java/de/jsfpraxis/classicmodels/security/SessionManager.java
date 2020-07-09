package de.jsfpraxis.classicmodels.security;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.context.ApplicationScoped;
import javax.servlet.http.HttpSession;

@ApplicationScoped
public class SessionManager {
	
    private Map<Integer, HttpSession> sessionMap = new ConcurrentHashMap<>();

    public void replaceSessionForUser(Integer userId, HttpSession session) {
        HttpSession loggedInUserSession = sessionMap.get(userId);
        if(loggedInUserSession != null) {
            try {
                loggedInUserSession.invalidate();
            } catch (IllegalStateException e) {
                // Session already invalidated. Do nothing.
            }
        }
        if(session != null) {
            sessionMap.put(userId, session);
        } else {
            sessionMap.remove(userId);
        }
    }

}
