package com.team3.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.team3.entities.User;
import com.team3.service.UserService;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserService userService =
            new UserService();


    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        // Check whether user is logged in
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


        // Get logged-in user
        User loggedInUser =
                (User) session.getAttribute("user");


        // Only ADMIN can access AdminServlet
        if (!"ADMIN".equalsIgnoreCase(
                loggedInUser.getRole())) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/dashboard.jsp"
            );

            return;
        }


        // Search users
        String search =
                request.getParameter("search");

        List<User> users;

        if (search != null
                && !search.trim().isEmpty()) {

            users =
                    userService.searchUsers(
                            search.trim()
                    );

        } else {

            users =
                    userService.getAllUsers();
        }


        request.setAttribute(
                "users",
                users
        );

        request.setAttribute(
                "search",
                search
        );


        request.getRequestDispatcher(
                "admin-users.jsp"
        ).forward(request, response);
    }


    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        // Check whether user is logged in
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


        // Get logged-in user
        User loggedInUser =
                (User) session.getAttribute("user");


        // Only ADMIN can perform admin actions
        if (!"ADMIN".equalsIgnoreCase(
                loggedInUser.getRole())) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/dashboard.jsp"
            );

            return;
        }


        String action =
                request.getParameter("action");


        // Delete user
        if ("delete".equals(action)) {

            String userIdParameter =
                    request.getParameter("userId");

            try {

                long userId =
                        Long.parseLong(
                                userIdParameter
                        );


                // Prevent admin from deleting
                // their own account
                if (userId ==
                        loggedInUser.getUserId()) {

                    response.sendRedirect(
                            request.getContextPath()
                                    + "/AdminServlet?error=selfdelete"
                    );

                    return;
                }


                boolean deleted =
                        userService.deleteUser(
                                userId
                        );


                if (deleted) {

                    response.sendRedirect(
                            request.getContextPath()
                                    + "/AdminServlet?success=deleted"
                    );

                } else {

                    response.sendRedirect(
                            request.getContextPath()
                                    + "/AdminServlet?error=delete"
                    );
                }

            } catch (NumberFormatException e) {

                response.sendRedirect(
                        request.getContextPath()
                                + "/AdminServlet?error=delete"
                );
            }

            return;
        }


        doGet(request, response);
    }
}