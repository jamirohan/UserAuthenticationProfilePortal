package com.team3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.team3.entities.UserSession;
import com.team3.util.DBConnection;

public class SessionDAO {
	
	 
	public boolean saveSession(UserSession session) {

	    String sql = "INSERT INTO user_session "
	               + "(session_id, login_time, expiry_time, user_id) "
	               + "VALUES (?, ?, ?, ?)";

	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, session.getSessionId());

	        statement.setTimestamp(2,
	                Timestamp.valueOf(session.getLoginTime()));

	        statement.setTimestamp(3,
	                Timestamp.valueOf(session.getExpiryTime()));

	        statement.setLong(4, session.getUserId());

	        int rowsInserted = statement.executeUpdate();

	        return rowsInserted > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	    
	
	
	public UserSession findSessionById(String sessionId) {

	    String sql = "SELECT session_id, login_time, expiry_time, user_id "
	               + "FROM user_session WHERE session_id = ?";

	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, sessionId);

	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {

	            UserSession session = new UserSession();

	            session.setSessionId(resultSet.getString("session_id"));
	            session.setLoginTime(
	                resultSet.getTimestamp("login_time").toLocalDateTime()
	            );
	            session.setExpiryTime(
	                resultSet.getTimestamp("expiry_time").toLocalDateTime()
	            );
	            session.setUserId(resultSet.getLong("user_id"));

	            return session;
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return null;
	}
	
	
	
	

	public boolean deleteSession(String sessionId) {

	    String sql = "DELETE FROM user_session WHERE session_id = ?";

	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, sessionId);

	        int rowsDeleted = statement.executeUpdate();

	        return rowsDeleted > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
/*
saveSession(UserSession session) → Stores a user's session information.
findSessionById(String sessionId) → Finds a session using its session ID.
deleteSession(String sessionId) → Removes a session when the user logs out. */
	  
	    
	    /*	    Why are we returning false and null?
		Because we haven't implemented JDBC yet.	    */
	    
}
