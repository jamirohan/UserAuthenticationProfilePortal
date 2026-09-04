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
import com.team3.entities.RememberToken;
import com.team3.entities.User;
import com.team3.service.AuthenticationService;
import com.team3.util.TokenUtil;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private AuthenticationService authenticationService =
            new AuthenticationService();

    private RememberTokenDAO rememberTokenDAO =
            new RememberTokenDAO();

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

        if (email == null || email.trim().isEmpty()
                || password == null || password.trim().isEmpty()) {

            response.getWriter()
                    .println("Email and password are required.");

            return;
        }

        User user = authenticationService.login(email, password);

        if (user != null) {

            // Create HTTP session
            HttpSession session = request.getSession();

            // Store logged-in user in session
            session.setAttribute("user", user);

            // Remember Me
            if (rememberMe) {

                // Generate secure random token
                String tokenValue = TokenUtil.generateToken();

                // Create RememberToken object
                RememberToken token = new RememberToken();

                token.setTokenValue(tokenValue);
                token.setUserId(user.getUserId());

                // Token expires after 30 days
                token.setExpiresAt(
                        LocalDateTime.now().plusDays(30)
                );

                // Save token in database
                rememberTokenDAO.saveToken(token);

                // Create Remember Me cookie
                Cookie rememberCookie =
                        new Cookie("rememberMe", tokenValue);

                // Prevent JavaScript from accessing the cookie
                rememberCookie.setHttpOnly(true);

                // Cookie lasts for 30 days
                rememberCookie.setMaxAge(
                        30 * 24 * 60 * 60
                );

                // Apply cookie to this application
                rememberCookie.setPath(
                        request.getContextPath()
                );

                // Send cookie to browser
                response.addCookie(rememberCookie);
            }

            // Redirect to dashboard
            response.sendRedirect("dashboard.jsp");

        } else {

            response.getWriter()
                    .println("Invalid email or password.");
        }

        System.out.println("Login request received");
        System.out.println("Email: " + email);
    }
}