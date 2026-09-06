package com.team3.service;

import java.util.List;


import com.team3.dao.UserDAO;
import com.team3.dao.RememberTokenDAO;
import com.team3.dao.SessionDAO;
import com.team3.entities.User;
import com.team3.util.PasswordUtil;


public class UserService {

    private UserDAO userDAO = new UserDAO();

    // Register user
    public boolean registerUser(User user) {

        return userDAO.registerUser(user);
    }

    // Find user by email
    public User getUserByEmail(String email) {

        return userDAO.findUserByEmail(email);
    }

    // Find user by ID
    public User getUserById(long userId) {

        return userDAO.findUserById(userId);
    }

    // Get all users
    // Used by Admin module
    public List<User> getAllUsers() {

        return userDAO.findAllUsers();
    }

    // Search users by name or email
    // Used by Admin module
    public List<User> searchUsers(String search) {

        return userDAO.searchUsers(search);
    }
    
 // Delete user
 // Used by Admin module
 // Delete user and related records
 // Used by Admin module
 public boolean deleteUser(long userId) {

     RememberTokenDAO rememberTokenDAO =
             new RememberTokenDAO();

     SessionDAO sessionDAO =
             new SessionDAO();

     // Delete Remember Me tokens
     rememberTokenDAO.deleteTokensByUserId(userId);

     // Delete database sessions
     sessionDAO.deleteSessionsByUserId(userId);

     // Finally delete the user
     return userDAO.deleteUser(userId);
 }
 
//Update user's full name and email
//Used by Profile module
public boolean updateUser(User user) {

  return userDAO.updateUser(user);
}

//Change user's password
//Used by Profile module
public boolean changePassword(long userId,
                          String currentPassword,
                          String newPassword) {

 User user =
         userDAO.findUserById(userId);

 if (user == null) {
     return false;
 }

 // Verify the current password
 boolean currentPasswordCorrect =
         PasswordUtil.verifyPassword(
                 currentPassword,
                 user.getPasswordHash()
         );

 if (!currentPasswordCorrect) {
     return false;
 }

 // Hash the new password before storing it
 String newPasswordHash =
         PasswordUtil.hashPassword(newPassword);

 return userDAO.updatePassword(
         userId,
         newPasswordHash
 );
}
}