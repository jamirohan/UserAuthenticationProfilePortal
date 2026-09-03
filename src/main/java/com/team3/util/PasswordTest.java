package com.team3.util;

public class PasswordTest {

    public static void main(String[] args) {

        String password = "Test@123";

        String hashedPassword = PasswordUtil.hashPassword(password);

        System.out.println("Hashed Password: " + hashedPassword);

        boolean result = PasswordUtil.verifyPassword(password, hashedPassword);

        System.out.println("Password Verified: " + result);
    }
}