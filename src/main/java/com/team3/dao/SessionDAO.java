package com.team3.dao;

import com.team3.entities.UserSession;

public class SessionDAO {
	
	 public boolean saveSession(UserSession session) {
	        return false;
	    }

	    public UserSession findSessionById(String sessionId) {
	        return null;
	    }

	    public boolean deleteSession(String sessionId) {
	        return false;
	    }
/*
saveSession(UserSession session) → Stores a user's session information.
findSessionById(String sessionId) → Finds a session using its session ID.
deleteSession(String sessionId) → Removes a session when the user logs out. */
	  
	    
	    /*	    Why are we returning false and null?
		Because we haven't implemented JDBC yet.	    */
	    
}
