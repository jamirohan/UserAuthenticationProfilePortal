<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.team3.entities.User" %>

<%
    User user = (User) request.getAttribute("user");

    String success =
            request.getParameter("success");

    String error =
            request.getParameter("error");
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>My Profile</title>

<style>

    * {
        box-sizing: border-box;
        margin: 0;
        padding: 0;
        font-family: Arial, sans-serif;
    }

    body {
        background: #f4f6f9;
        min-height: 100vh;
    }

    .navbar {
        background: #1f2937;
        color: white;
        padding: 18px 40px;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    .navbar h2 {
        font-size: 22px;
    }

    .nav-links a {
        text-decoration: none;
        color: white;
        padding: 10px 18px;
        border-radius: 6px;
        margin-left: 8px;
    }

    .dashboard {
        background: #2563eb;
    }

    .logout {
        background: #dc2626;
    }

    .container {
        max-width: 700px;
        margin: 50px auto;
        padding: 0 25px;
    }

    .card {
        background: white;
        border-radius: 12px;
        padding: 35px;
        box-shadow: 0 4px 15px rgba(0, 0, 0, 0.08);
    }

    .card h1 {
        color: #111827;
        margin-bottom: 8px;
    }

    .card-description {
        color: #6b7280;
        margin-bottom: 30px;
    }

    .message {
        padding: 12px;
        border-radius: 6px;
        margin-bottom: 20px;
        font-size: 14px;
    }

    .success {
        background: #dcfce7;
        color: #166534;
    }

    .error {
        background: #fee2e2;
        color: #991b1b;
    }

    .form-group {
        margin-bottom: 20px;
    }

    .form-group label {
        display: block;
        margin-bottom: 8px;
        color: #374151;
        font-weight: bold;
    }

    .form-group input {
        width: 100%;
        padding: 12px;
        border: 1px solid #d1d5db;
        border-radius: 6px;
        font-size: 15px;
    }

    .form-group input:focus {
        outline: none;
        border-color: #2563eb;
    }

    .update-btn {
        width: 100%;
        padding: 13px;
        background: #2563eb;
        color: white;
        border: none;
        border-radius: 6px;
        font-size: 15px;
        cursor: pointer;
        margin-top: 5px;
    }

    .update-btn:hover {
        background: #1d4ed8;
    }

    .security-section {
        margin-top: 30px;
        padding-top: 25px;
        border-top: 1px solid #e5e7eb;
    }

    .security-section h2 {
        color: #111827;
        margin-bottom: 8px;
        font-size: 20px;
    }

    .security-section p {
        color: #6b7280;
        margin-bottom: 20px;
    }

    .password-form {
        margin-top: 20px;
    }

    .password-btn {
        width: 100%;
        padding: 13px;
        background: #6b7280;
        color: white;
        border: none;
        border-radius: 6px;
        font-size: 15px;
        cursor: pointer;
    }

    .password-btn:hover {
        background: #4b5563;
    }

</style>

</head>

<body>

    <div class="navbar">

        <h2>My Profile</h2>

        <div class="nav-links">

            <a href="${pageContext.request.contextPath}/dashboard.jsp"
               class="dashboard">
                Dashboard
            </a>

            <a href="${pageContext.request.contextPath}/LogoutServlet"
               class="logout">
                Logout
            </a>

        </div>

    </div>


    <div class="container">

        <div class="card">

            <h1>Profile Information</h1>

            <p class="card-description">
                Update your personal information below.
            </p>


            <!-- SUCCESS MESSAGES -->

            <% if ("updated".equals(success)) { %>

                <div class="message success">
                    Profile updated successfully.
                </div>

            <% } %>


            <% if ("passwordChanged".equals(success)) { %>

                <div class="message success">
                    Password changed successfully.
                </div>

            <% } %>


            <!-- PROFILE ERRORS -->

            <% if ("required".equals(error)) { %>

                <div class="message error">
                    Full Name and Email are required.
                </div>

            <% } %>


            <% if ("failed".equals(error)) { %>

                <div class="message error">
                    Profile update failed. Please try again.
                </div>

            <% } %>


            <!-- PASSWORD ERRORS -->

            <% if ("passwordRequired".equals(error)) { %>

                <div class="message error">
                    All password fields are required.
                </div>

            <% } %>


            <% if ("passwordMismatch".equals(error)) { %>

                <div class="message error">
                    New password and confirm password do not match.
                </div>

            <% } %>


            <% if ("passwordInvalid".equals(error)) { %>

                <div class="message error">
                    Current password is incorrect.
                </div>

            <% } %>


            <!-- PROFILE UPDATE FORM -->

            <form action="${pageContext.request.contextPath}/ProfileServlet"
                  method="post">

                <div class="form-group">

                    <label for="fullName">
                        Full Name
                    </label>

                    <input type="text"
                           id="fullName"
                           name="fullName"
                           value="<%= user != null
                                   ? user.getFullName()
                                   : "" %>"
                           required>

                </div>


                <div class="form-group">

                    <label for="email">
                        Email
                    </label>

                    <input type="email"
                           id="email"
                           name="email"
                           value="<%= user != null
                                   ? user.getEmail()
                                   : "" %>"
                           required>

                </div>


                <button type="submit"
                        class="update-btn">

                    Update Profile

                </button>

            </form>


            <!-- CHANGE PASSWORD -->

            <div class="security-section">

                <h2>Password Security</h2>

                <p>
                    Change your password by entering your
                    current password and a new password.
                </p>


                <form action="${pageContext.request.contextPath}/ProfileServlet"
                      method="post"
                      class="password-form">

                    <input type="hidden"
                           name="action"
                           value="changePassword">


                    <div class="form-group">

                        <label for="currentPassword">
                            Current Password
                        </label>

                        <input type="password"
                               id="currentPassword"
                               name="currentPassword"
                               required>

                    </div>


                    <div class="form-group">

                        <label for="newPassword">
                            New Password
                        </label>

                        <input type="password"
                               id="newPassword"
                               name="newPassword"
                               minlength="6"
                               required>

                    </div>


                    <div class="form-group">

                        <label for="confirmPassword">
                            Confirm New Password
                        </label>

                        <input type="password"
                               id="confirmPassword"
                               name="confirmPassword"
                               minlength="6"
                               required>

                    </div>


                    <button type="submit"
                            class="password-btn">

                        Change Password

                    </button>

                </form>

            </div>

        </div>

    </div>

</body>

</html>