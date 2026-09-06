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
	
	
/*	findToken() searches the REMEMBER_TOKEN table using the token value.
	This will later allow the AuthFilter to check whether a Remember Me token is valid.*/

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

	
	
//	This helps prevent an old Remember Me token from being reused after logout.
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
	// Delete all remember tokens for a user
	// Used when Admin deletes a user
	public boolean deleteTokensByUserId(long userId) {

	    String sql =
	            "DELETE FROM remember_token WHERE user_id = ?";

	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement statement =
	                 connection.prepareStatement(sql)) {

	        statement.setLong(1, userId);

	        statement.executeUpdate();

	        return true;

	    } catch (SQLException e) {

	        e.printStackTrace();
	        return false;
	    }
	}
}
