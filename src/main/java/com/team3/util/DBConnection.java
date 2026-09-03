package com.team3.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
            "jdbc:oracle:thin:@localhost:1521/FREEPDB1";

    private static final String USERNAME = "SYSTEM";

    private static final String PASSWORD = "Team3project";

    public static Connection getConnection() {

        try {
            Connection connection =
                    DriverManager.getConnection(URL, USERNAME, PASSWORD);

            System.out.println("Database connected successfully!");

            return connection;

        } catch (SQLException e) {

            System.out.println("Database connection failed!");
            e.printStackTrace();

            return null;
        }
    }
}