package com.team3.util;

import org.mindrot.jbcrypt.BCrypt;


public class PasswordUtil {
	
	public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean verifyPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
    


    
/*hashPassword()
    Takes the user's normal password.
    Converts it into a secure BCrypt hash.
    This hash will eventually be stored in Oracle.

    Example:
    Password entered:    MyPassword123
    Stored in database:  $2a$10$................. 

 verifyPassword():
    Takes the password entered during login.
    Compares it with the stored BCrypt hash.
    Returns true if they match, otherwise false.*/
}
