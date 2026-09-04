package com.team3.util;

import java.security.SecureRandom;
import java.util.Base64;

public class TokenUtil {

    public static String generateToken() {

        SecureRandom secureRandom = new SecureRandom();

        byte[] tokenBytes = new byte[32];

        secureRandom.nextBytes(tokenBytes);

        return Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(tokenBytes);
    }
}