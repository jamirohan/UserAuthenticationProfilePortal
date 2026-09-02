package com.team3.dao;

import com.team3.entities.User;

public class UserDAO {
	
	 public boolean registerUser(User user) {   
	        return false;
	    }
	 
	    public User findUserByEmail(String email) {
	        return null;
	    }
	    
	    public User findUserById(long userId) {
	        return null;
	    }
	    
/*   DAO -> DATA ACCESS OBJECT   
registerUser(User user) → Used to save/register a new user in the database.
findUserByEmail(String email) → Used to find a user by email during login.
findUserById(long userId) → Used to retrieve a specific user's details using their ID.*/	    
	    
	    
	    
/*	    Why are we returning false and null?
	    		Because we haven't implemented JDBC yet.	    */
}
