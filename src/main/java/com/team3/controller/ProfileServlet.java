package com.team3.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.team3.dao.RememberTokenDAO;

import com.team3.entities.User;
import com.team3.service.UserService;

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserService userService =
            new UserService();
    private RememberTokenDAO rememberTokenDAO =
            new RememberTokenDAO();


    // Display profile page
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        if (session == null
                || session.getAttribute("user") == null) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/login.jsp"
            );

            return;
        }

        User sessionUser =
                (User) session.getAttribute("user");

        User user =
                userService.getUserById(
                        sessionUser.getUserId()
                );

        if (user == null) {

            session.invalidate();

            response.sendRedirect(
                    request.getContextPath()
                            + "/login.jsp"
            );

            return;
        }

        request.setAttribute(
                "user",
                user
        );

        request.getRequestDispatcher(
                "profile.jsp"
        ).forward(request, response);
    }


    // Handle profile update and password change
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        if (session == null
                || session.getAttribute("user") == null) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/login.jsp"
            );

            return;
        }

        User sessionUser =
                (User) session.getAttribute("user");

        String action =
                request.getParameter("action");


        // ==========================================
        // CHANGE PASSWORD
        // ==========================================

        if ("changePassword".equals(action)) {

            String currentPassword =
                    request.getParameter("currentPassword");

            String newPassword =
                    request.getParameter("newPassword");

            String confirmPassword =
                    request.getParameter("confirmPassword");


            // Check required fields
            if (currentPassword == null
                    || currentPassword.trim().isEmpty()
                    || newPassword == null
                    || newPassword.trim().isEmpty()
                    || confirmPassword == null
                    || confirmPassword.trim().isEmpty()) {

                response.sendRedirect(
                        request.getContextPath()
                                + "/ProfileServlet?error=passwordRequired"
                );

                return;
            }


            // Check whether new passwords match
            if (!newPassword.equals(confirmPassword)) {

                response.sendRedirect(
                        request.getContextPath()
                                + "/ProfileServlet?error=passwordMismatch"
                );

                return;
            }


            // Change password
            boolean passwordChanged =
                    userService.changePassword(
                            sessionUser.getUserId(),
                            currentPassword,
                            newPassword
                    );


            if (passwordChanged) {
            	
            	// Remove existing Remember Me tokens
            	rememberTokenDAO.deleteTokensByUserId(
            	        sessionUser.getUserId()
            	);
                // Refresh user information in session
                User updatedUser =
                        userService.getUserById(
                                sessionUser.getUserId()
                        );

                session.setAttribute(
                        "user",
                        updatedUser
                );

                response.sendRedirect(
                        request.getContextPath()
                                + "/ProfileServlet?success=passwordChanged"
                );

            } else {

                response.sendRedirect(
                        request.getContextPath()
                                + "/ProfileServlet?error=passwordInvalid"
                );
            }

            return;
        }


        // ==========================================
        // UPDATE PROFILE
        // ==========================================

        String fullName =
                request.getParameter("fullName");

        String email =
                request.getParameter("email");


        if (fullName == null
                || fullName.trim().isEmpty()
                || email == null
                || email.trim().isEmpty()) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/ProfileServlet?error=required"
            );

            return;
        }


        User user =
                new User();

        user.setUserId(
                sessionUser.getUserId()
        );

        user.setFullName(
                fullName.trim()
        );

        user.setEmail(
                email.trim()
        );


        boolean updated =
                userService.updateUser(user);


        if (updated) {

            User updatedUser =
                    userService.getUserById(
                            sessionUser.getUserId()
                    );

            session.setAttribute(
                    "user",
                    updatedUser
            );

            response.sendRedirect(
                    request.getContextPath()
                            + "/ProfileServlet?success=updated"
            );

        } else {

            response.sendRedirect(
                    request.getContextPath()
                            + "/ProfileServlet?error=failed"
            );
        }
    }
}