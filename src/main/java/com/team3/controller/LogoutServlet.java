package com.team3.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.team3.dao.RememberTokenDAO;
import com.team3.dao.SessionDAO;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private RememberTokenDAO rememberTokenDAO =
            new RememberTokenDAO();

    private SessionDAO sessionDAO =
            new SessionDAO();

    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        // Get current session
        HttpSession session =
                request.getSession(false);

        // Delete session record from USER_SESSION table
        if (session != null) {

            String sessionId = session.getId();

            sessionDAO.deleteSession(sessionId);

            // Invalidate HTTP session
            session.invalidate();
        }

        // Find and delete Remember Me cookie
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {

            for (Cookie cookie : cookies) {

                if ("rememberMe".equals(cookie.getName())) {

                    String tokenValue = cookie.getValue();

                    // Delete Remember Me token from database
                    rememberTokenDAO.deleteToken(tokenValue);

                    // Delete Remember Me cookie from browser
                    Cookie deleteCookie =
                            new Cookie("rememberMe", "");

                    deleteCookie.setHttpOnly(true);
                    deleteCookie.setMaxAge(0);
                    deleteCookie.setPath(
                            request.getContextPath()
                    );

                    response.addCookie(deleteCookie);
                }
            }
        }

        // Redirect to login page
        response.sendRedirect(
                request.getContextPath() + "/login.jsp"
        );
    }

    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}