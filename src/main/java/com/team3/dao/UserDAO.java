package com.team3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.team3.entities.User;
import com.team3.util.DBConnection;

public class UserDAO {

    // Register a new user
    public boolean registerUser(User user) {

        String sql = "INSERT INTO users "
                   + "(email, password_hash, full_name, role) "
                   + "VALUES (?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPasswordHash());
            statement.setString(3, user.getFullName());

            // All users registering through the portal
            // are normal users.
            statement.setString(4, "USER");

            int rowsInserted =
                    statement.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }


    // Find user by email
    public User findUserByEmail(String email) {

        String sql = "SELECT user_id, email, password_hash, "
                   + "full_name, role, created_at "
                   + "FROM users WHERE email = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, email);

            ResultSet resultSet =
                    statement.executeQuery();

            if (resultSet.next()) {

                User user = new User();

                user.setUserId(
                        resultSet.getLong("user_id")
                );

                user.setEmail(
                        resultSet.getString("email")
                );

                user.setPasswordHash(
                        resultSet.getString("password_hash")
                );

                user.setFullName(
                        resultSet.getString("full_name")
                );

                user.setRole(
                        resultSet.getString("role")
                );

                Timestamp createdAt =
                        resultSet.getTimestamp("created_at");

                if (createdAt != null) {

                    user.setCreatedAt(
                            createdAt.toLocalDateTime()
                    );
                }

                return user;
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return null;
    }


    // Find user by ID
    public User findUserById(long userId) {

        String sql = "SELECT user_id, email, password_hash, "
                   + "full_name, role, created_at "
                   + "FROM users WHERE user_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setLong(1, userId);

            ResultSet resultSet =
                    statement.executeQuery();

            if (resultSet.next()) {

                User user = new User();

                user.setUserId(
                        resultSet.getLong("user_id")
                );

                user.setEmail(
                        resultSet.getString("email")
                );

                user.setPasswordHash(
                        resultSet.getString("password_hash")
                );

                user.setFullName(
                        resultSet.getString("full_name")
                );

                user.setRole(
                        resultSet.getString("role")
                );

                Timestamp createdAt =
                        resultSet.getTimestamp("created_at");

                if (createdAt != null) {

                    user.setCreatedAt(
                            createdAt.toLocalDateTime()
                    );
                }

                return user;
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return null;
    }


    // Find all users
    // Used by Admin module
    public List<User> findAllUsers() {

        List<User> users =
                new ArrayList<>();

        String sql = "SELECT user_id, email, password_hash, "
                   + "full_name, role, created_at "
                   + "FROM users "
                   + "ORDER BY user_id";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet =
                     statement.executeQuery()) {

            while (resultSet.next()) {

                User user = new User();

                user.setUserId(
                        resultSet.getLong("user_id")
                );

                user.setEmail(
                        resultSet.getString("email")
                );

                user.setPasswordHash(
                        resultSet.getString("password_hash")
                );

                user.setFullName(
                        resultSet.getString("full_name")
                );

                user.setRole(
                        resultSet.getString("role")
                );

                Timestamp createdAt =
                        resultSet.getTimestamp("created_at");

                if (createdAt != null) {

                    user.setCreatedAt(
                            createdAt.toLocalDateTime()
                    );
                }

                users.add(user);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return users;
    }


    // Search users by full name or email
    // Used by Admin module
    public List<User> searchUsers(String search) {

        List<User> users =
                new ArrayList<>();

        String sql = "SELECT user_id, email, password_hash, "
                   + "full_name, role, created_at "
                   + "FROM users "
                   + "WHERE LOWER(full_name) LIKE LOWER(?) "
                   + "OR LOWER(email) LIKE LOWER(?) "
                   + "ORDER BY user_id";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            String searchPattern =
                    "%" + search + "%";

            statement.setString(
                    1,
                    searchPattern
            );

            statement.setString(
                    2,
                    searchPattern
            );

            ResultSet resultSet =
                    statement.executeQuery();

            while (resultSet.next()) {

                User user = new User();

                user.setUserId(
                        resultSet.getLong("user_id")
                );

                user.setEmail(
                        resultSet.getString("email")
                );

                user.setPasswordHash(
                        resultSet.getString("password_hash")
                );

                user.setFullName(
                        resultSet.getString("full_name")
                );

                user.setRole(
                        resultSet.getString("role")
                );

                Timestamp createdAt =
                        resultSet.getTimestamp("created_at");

                if (createdAt != null) {

                    user.setCreatedAt(
                            createdAt.toLocalDateTime()
                    );
                }

                users.add(user);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return users;
    }
 // Delete user
 // Used by Admin module
 public boolean deleteUser(long userId) {

     String sql = "DELETE FROM users WHERE user_id = ?";

     try (Connection connection = DBConnection.getConnection();
          PreparedStatement statement =
                  connection.prepareStatement(sql)) {

         statement.setLong(1, userId);

         int rowsDeleted =
                 statement.executeUpdate();

         return rowsDeleted > 0;

     } catch (SQLException e) {

         e.printStackTrace();
         return false;
     }
 }
 
//Update user's full name and email
//Used by Profile module
public boolean updateUser(User user) {

  String sql =
          "UPDATE users "
        + "SET full_name = ?, email = ? "
        + "WHERE user_id = ?";

  try (Connection connection = DBConnection.getConnection();
       PreparedStatement statement =
               connection.prepareStatement(sql)) {

      statement.setString(
              1,
              user.getFullName()
      );

      statement.setString(
              2,
              user.getEmail()
      );

      statement.setLong(
              3,
              user.getUserId()
      );

      int rowsUpdated =
              statement.executeUpdate();

      return rowsUpdated > 0;

  } catch (SQLException e) {

      e.printStackTrace();
      return false;
  }
}
//Update user's password
//Used by Profile module
public boolean updatePassword(long userId, String passwordHash) {

 String sql =
         "UPDATE users "
       + "SET password_hash = ? "
       + "WHERE user_id = ?";

 try (Connection connection = DBConnection.getConnection();
      PreparedStatement statement =
              connection.prepareStatement(sql)) {

     statement.setString(
             1,
             passwordHash
     );

     statement.setLong(
             2,
             userId
     );

     int rowsUpdated =
             statement.executeUpdate();

     return rowsUpdated > 0;

 } catch (SQLException e) {

     e.printStackTrace();
     return false;
 }
}
}