package com.team3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.team3.entities.RememberToken;
import com.team3.util.DBConnection;

public class RememberTokenDAO {
	 
	public boolean saveToken(RememberToken token) {

	    String sql = "INSERT INTO remember_token "
	               + "(token_value, expires_at, user_id) "
	               + "VALUES (?, ?, ?)";

	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, token.getTokenValue());
	        statement.setTimestamp(2,
	                Timestamp.valueOf(token.getExpiresAt()));
	        statement.setLong(3, token.getUserId());

	        int rowsInserted = statement.executeUpdate();

	        return rowsInserted > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	
	

	public RememberToken findToken(String tokenValue) {

	    String sql = "SELECT token_id, token_value, expires_at, user_id "
	               + "FROM remember_token WHERE token_value = ?";

	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, tokenValue);

	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {

	            RememberToken token = new RememberToken();

	            token.setTokenId(resultSet.getLong("token_id"));
	            token.setTokenValue(resultSet.getString("token_value"));
	            token.setExpiresAt(
	                resultSet.getTimestamp("expires_at").toLocalDateTime()
	            );
	            token.setUserId(resultSet.getLong("user_id"));

	            return token;
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return null;
	}

	
	
	
	public boolean deleteToken(String tokenValue) {

	    String sql = "DELETE FROM remember_token WHERE token_value = ?";

	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, tokenValue);

	        int rowsDeleted = statement.executeUpdate();

	        return rowsDeleted > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
}
}
