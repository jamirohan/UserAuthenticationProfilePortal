package com.team3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.team3.entities.User;
import com.team3.util.DBConnection;

public class UserDAO {
	
//	registerUser(User user) → Used to save/register a new user in the database.
	public boolean registerUser(User user) {

	    String sql = "INSERT INTO users (email, password_hash, full_name) VALUES (?, ?, ?)";

	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, user.getEmail());
	        statement.setString(2, user.getPasswordHash());
	        statement.setString(3, user.getFullName());

	        int rowsInserted = statement.executeUpdate();

	        return rowsInserted > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	
	
//	findUserByEmail(String email) → Used to find a user by email during login.
	public User findUserByEmail(String email) {

	    String sql = "SELECT user_id, email, password_hash, full_name, created_at "
	               + "FROM users WHERE email = ?";

	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, email);

	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {

	            User user = new User();

	            user.setUserId(resultSet.getLong("user_id"));
	            user.setEmail(resultSet.getString("email"));
	            user.setPasswordHash(resultSet.getString("password_hash"));
	            user.setFullName(resultSet.getString("full_name"));

	            Timestamp timestamp = resultSet.getTimestamp("created_at");

	            if (timestamp != null) {
	                user.setCreatedAt(timestamp.toLocalDateTime());
	            }

	            return user;
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return null;
	}
	    
	    
//	findUserById(long userId) → Used to retrieve a specific user's details using their ID
	public User findUserById(long userId) {

	    String sql = "SELECT user_id, email, password_hash, full_name, created_at "
	               + "FROM users WHERE user_id = ?";

	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setLong(1, userId);

	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {

	            User user = new User();

	            user.setUserId(resultSet.getLong("user_id"));
	            user.setEmail(resultSet.getString("email"));
	            user.setPasswordHash(resultSet.getString("password_hash"));
	            user.setFullName(resultSet.getString("full_name"));

	            Timestamp timestamp = resultSet.getTimestamp("created_at");

	            if (timestamp != null) {
	                user.setCreatedAt(timestamp.toLocalDateTime());
	            }

	            return user;
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return null;
	}
	
	    
	    
	    
	    
	    
	    
	    
/*   DAO -> DATA ACCESS OBJECT   


.*/	    
	    
	    
	    
/*	    Why are we returning false and null?
	    		Because we haven't implemented JDBC yet.	    */
}
