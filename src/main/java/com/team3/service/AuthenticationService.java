package com.team3.service;

import com.team3.dao.UserDAO;
import com.team3.entities.User;

public class AuthenticationService {
	
	private UserDAO userDAO = new UserDAO();

    public User login(String email, String password) {
        User user = userDAO.findUserByEmail(email);

        if (user != null) {
            // Password verification will be added later
            return user;
        }

        return null;
    }

    public boolean logout(String sessionId) {
        // Logout logic will be added later
        return true;
    }
 /*
Why we're adding these methods
login() → authenticate a user using their login credentials..
logout() = end the user's authenticated session.
Password checking is intentionally NOT implemented yet.
 We'll add proper password hashing/security later. */   
    
    
}
