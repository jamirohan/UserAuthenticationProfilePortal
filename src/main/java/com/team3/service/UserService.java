package com.team3.service;

import com.team3.dao.UserDAO;
import com.team3.entities.User;

public class UserService {

    private UserDAO userDAO = new UserDAO();

    public boolean registerUser(User user) {
        return userDAO.registerUser(user);
    }

    public User getUserByEmail(String email) {
        return userDAO.findUserByEmail(email);
    }

    public User getUserById(long userId) {
        return userDAO.findUserById(userId);
    }

    
    
    /*
     * registerUser(User user)
     * - Used when a new user registers.
     *
     * getUserByEmail(String email)
     * - Used during login to find a user by email.
     *
     * getUserById(long userId)
     * - Used to find a user by their user ID.
     */
}
/*
registerUser(User user) - This method is used when a new user registers.
getUserByEmail(String email) - When someone logs in, we need to find their account using their email address*/

