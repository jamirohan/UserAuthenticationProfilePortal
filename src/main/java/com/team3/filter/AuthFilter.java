package com.team3.filter;

import java.io.IOException;
import java.time.LocalDateTime;

import com.team3.dao.RememberTokenDAO;
import com.team3.entities.RememberToken;
import com.team3.entities.User;
import com.team3.service.UserService;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter({"/dashboard.jsp", "/profile.jsp"})
public class AuthFilter implements Filter {

    private RememberTokenDAO rememberTokenDAO =
            new RememberTokenDAO();

    private UserService userService =
            new UserService();

    @Override
    public void doFilter(ServletRequest request,
                          ServletResponse response,
                          FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest =
                (HttpServletRequest) request;

        HttpServletResponse httpResponse =
                (HttpServletResponse) response;

        // 1. Check existing HTTP session
        HttpSession session =
                httpRequest.getSession(false);

        if (session != null
                && session.getAttribute("user") != null) {

            chain.doFilter(request, response);
            return;
        }

        // 2. No session, so check Remember Me cookie
        Cookie[] cookies = httpRequest.getCookies();

        if (cookies != null) {

            for (Cookie cookie : cookies) {

                if ("rememberMe".equals(cookie.getName())) {

                    String tokenValue = cookie.getValue();

                    // Find token in database
                    RememberToken token =
                            rememberTokenDAO.findToken(tokenValue);

                    // Check whether token exists and is not expired
                    if (token != null
                            && token.getExpiresAt() != null
                            && token.getExpiresAt()
                                    .isAfter(LocalDateTime.now())) {

                        // Find user using the user ID stored in token
                        User user =
                                userService.getUserById(
                                        token.getUserId()
                                );

                        if (user != null) {

                            // Create a new session
                            HttpSession newSession =
                                    httpRequest.getSession(true);

                            // Store user in session
                            newSession.setAttribute(
                                    "user",
                                    user
                            );

                            // Allow request to continue
                            chain.doFilter(
                                    request,
                                    response
                            );

                            return;
                        }
                    }

                    // Invalid or expired token
                    break;
                }
            }
        }

        // 3. User is not authenticated
        httpResponse.sendRedirect(
                httpRequest.getContextPath()
                        + "/login.jsp"
        );
    }
}