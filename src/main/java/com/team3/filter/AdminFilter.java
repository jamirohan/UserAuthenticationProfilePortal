package com.team3.filter;

import java.io.IOException;

import com.team3.entities.User;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/admin-dashboard.jsp")
public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                          ServletResponse response,
                          FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest =
                (HttpServletRequest) request;

        HttpServletResponse httpResponse =
                (HttpServletResponse) response;

        HttpSession session =
                httpRequest.getSession(false);

        // Check whether user is logged in
        if (session == null
                || session.getAttribute("user") == null) {

            httpResponse.sendRedirect(
                    httpRequest.getContextPath()
                            + "/login.jsp"
            );

            return;
        }

        // Get logged-in user
        User user =
                (User) session.getAttribute("user");

        // Check whether user is ADMIN
        if ("ADMIN".equalsIgnoreCase(user.getRole())) {

            // Admin is authorized
            chain.doFilter(request, response);

            return;
        }

        // Normal USER is not allowed
        httpResponse.sendRedirect(
                httpRequest.getContextPath()
                        + "/dashboard.jsp"
        );
    }
}