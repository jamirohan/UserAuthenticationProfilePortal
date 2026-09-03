package com.team3.util;

import java.sql.Connection;

public class DBConnectionTest {

    public static void main(String[] args) {

        Connection connection = DBConnection.getConnection();

        if (connection != null) {
            System.out.println("JDBC TEST PASSED!");
        } else {
            System.out.println("JDBC TEST FAILED!");
        }
    }
}