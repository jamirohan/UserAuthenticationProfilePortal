package com.team3.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.team3.entities.User;
import com.team3.service.UserService;

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserService userService = new UserService();

    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        // Get existing session
        HttpSession session = request.getSession(false);

        // Check whether user is logged in
        if (session == null || session.getAttribute("user") == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login.jsp"
            );

            return;
        }

        // Get logged-in user from session
        User sessionUser =
                (User) session.getAttribute("user");

        // Get latest user information from database
        User user =
                userService.getUserById(
                        sessionUser.getUserId()
                );

        // Check whether user still exists
        if (user == null) {

            session.invalidate();

            response.sendRedirect(
                    request.getContextPath() + "/login.jsp"
            );

            return;
        }

        // Send user information to profile.jsp
        request.setAttribute("user", user);

        // Open profile page
        request.getRequestDispatcher("profile.jsp")
               .forward(request, response);
    }

    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}