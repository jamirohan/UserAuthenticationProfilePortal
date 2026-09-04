package com.team3.controller;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.team3.entities.User;
import com.team3.service.UserService;
import com.team3.util.PasswordUtil;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();

    /**
     * Default constructor. 
     */
    public RegisterServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    String fullName = request.getParameter("fullName");
	    String email = request.getParameter("email");
	    String password = request.getParameter("password");
	    
	    if (fullName == null || fullName.trim().isEmpty()
	            || email == null || email.trim().isEmpty()
	            || password == null || password.trim().isEmpty()) {

	        response.getWriter().println("All fields are required.");
	        return;
	    }

	   String hashedPassword = PasswordUtil.hashPassword(password);  
	   
	   User user = new User();

	   user.setFullName(fullName);
	   user.setEmail(email);
	   user.setPasswordHash(hashedPassword);
	   
	   boolean registered = userService.registerUser(user);

	   if (registered) {
	       response.getWriter().println("Registration successful!");
	   } else {
	       response.getWriter().println("Registration failed.");
	   }
	    
	    System.out.println("Registration request received");
	    System.out.println("Full Name: " + fullName);
	    System.out.println("Email: " + email);
	}

}
