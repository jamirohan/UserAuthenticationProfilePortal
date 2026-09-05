package com.team3.controller;

import java.io.IOException;
import java.time.LocalDateTime;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.team3.dao.RememberTokenDAO;
import com.team3.dao.SessionDAO;
import com.team3.entities.RememberToken;
import com.team3.entities.User;
import com.team3.entities.UserSession;
import com.team3.service.AuthenticationService;
import com.team3.util.TokenUtil;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private AuthenticationService authenticationService =
            new AuthenticationService();

    private RememberTokenDAO rememberTokenDAO =
            new RememberTokenDAO();

    private SessionDAO sessionDAO =
            new SessionDAO();

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.getWriter()
                .append("Served at: ")
                .append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        boolean rememberMe =
                "true".equals(request.getParameter("rememberMe"));

        // Validate input
        if (email == null || email.trim().isEmpty()
                || password == null || password.trim().isEmpty()) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/login.jsp?error=required"
            );

            return;
        }

        // Authenticate user
        User user =
                authenticationService.login(email, password);

        if (user != null) {

            // Create HTTP session
            HttpSession session =
                    request.getSession();

            // Store logged-in user in session
            session.setAttribute("user", user);

            // Session expires after 30 minutes
            LocalDateTime loginTime =
                    LocalDateTime.now();

            LocalDateTime expiryTime =
                    loginTime.plusMinutes(30);

            // Save session information in USER_SESSION table
            UserSession userSession =
                    new UserSession();

            userSession.setSessionId(
                    session.getId()
            );

            userSession.setLoginTime(
                    loginTime
            );

            userSession.setExpiryTime(
                    expiryTime
            );

            userSession.setUserId(
                    user.getUserId()
            );

            sessionDAO.saveSession(userSession);

            // Remember Me
            if (rememberMe) {

                String tokenValue =
                        TokenUtil.generateToken();

                RememberToken token =
                        new RememberToken();

                token.setTokenValue(
                        tokenValue
                );

                token.setUserId(
                        user.getUserId()
                );

                token.setExpiresAt(
                        LocalDateTime.now()
                                .plusDays(30)
                );

                rememberTokenDAO.saveToken(token);

                Cookie rememberCookie =
                        new Cookie(
                                "rememberMe",
                                tokenValue
                        );

                // Prevent JavaScript from accessing cookie
                rememberCookie.setHttpOnly(true);

                // Cookie remains for 30 days
                rememberCookie.setMaxAge(
                        30 * 24 * 60 * 60
                );

                // Make cookie available to the application
                rememberCookie.setPath(
                        request.getContextPath()
                );

                response.addCookie(
                        rememberCookie
                );
            }

            // Login successful
            response.sendRedirect(
                    request.getContextPath()
                            + "/dashboard.jsp"
            );

        } else {

            // Login failed
            response.sendRedirect(
                    request.getContextPath()
                            + "/login.jsp?error=invalid"
            );
        }

        System.out.println(
                "Login request received"
        );

        System.out.println(
                "Email: " + email
        );
    }
}