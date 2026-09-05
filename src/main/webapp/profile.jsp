<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.team3.entities.User" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Profile | User Portal</title>

    <style>

        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
        }

        body {
            min-height: 100vh;
            background: linear-gradient(135deg, #667eea, #764ba2);
            padding: 40px 20px;
        }

        .profile-container {
            width: 500px;
            max-width: 100%;
            margin: 40px auto;
            background: white;
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
        }

        .profile-icon {
            width: 80px;
            height: 80px;
            margin: 0 auto 20px;
            border-radius: 50%;
            background: #667eea;
            color: white;
            display: flex;
            justify-content: center;
            align-items: center;
            font-size: 32px;
            font-weight: bold;
        }

        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 10px;
        }

        .subtitle {
            text-align: center;
            color: #777;
            margin-bottom: 30px;
        }

        .profile-card {
            background: #f7f8fc;
            border-radius: 10px;
            padding: 20px;
            margin-bottom: 25px;
        }

        .profile-row {
            padding: 15px 0;
            border-bottom: 1px solid #ddd;
        }

        .profile-row:last-child {
            border-bottom: none;
        }

        .profile-label {
            display: block;
            color: #777;
            font-size: 13px;
            margin-bottom: 5px;
        }

        .profile-value {
            color: #333;
            font-size: 16px;
            font-weight: bold;
        }

        .button-row {
            display: flex;
            gap: 12px;
        }

        .back-button,
        .logout-button {
            flex: 1;
            padding: 12px;
            border-radius: 8px;
            text-align: center;
            font-size: 14px;
            font-weight: bold;
            cursor: pointer;
        }

        .back-button {
            background: #667eea;
            color: white;
            text-decoration: none;
        }

        .back-button:hover {
            background: #5568d9;
        }

        .logout-button {
            border: none;
            background: #e74c3c;
            color: white;
        }

        .logout-button:hover {
            background: #c0392b;
        }

    </style>
</head>

<body>

<%
    User user = (User) request.getAttribute("user");

    if (user == null) {
        response.sendRedirect(
            request.getContextPath() + "/login.jsp"
        );
        return;
    }
%>

    <div class="profile-container">

        <div class="profile-icon">
            <%= user.getFullName().substring(0, 1).toUpperCase() %>
        </div>

        <h1>My Profile</h1>

        <p class="subtitle">
            Your account information
        </p>

        <div class="profile-card">

            <div class="profile-row">

                <span class="profile-label">
                    Full Name
                </span>

                <span class="profile-value">
                    <%= user.getFullName() %>
                </span>

            </div>

            <div class="profile-row">

                <span class="profile-label">
                    Email Address
                </span>

                <span class="profile-value">
                    <%= user.getEmail() %>
                </span>

            </div>

            <div class="profile-row">

                <span class="profile-label">
                    User ID
                </span>

                <span class="profile-value">
                    <%= user.getUserId() %>
                </span>

            </div>

        </div>

        <div class="button-row">

            <a class="back-button"
               href="<%= request.getContextPath() %>/dashboard.jsp">
                Dashboard
            </a>

            <form action="<%= request.getContextPath() %>/LogoutServlet"
                  method="post"
                  style="flex: 1;">

                <button type="submit"
                        class="logout-button">
                    Logout
                </button>

            </form>

        </div>

    </div>

</body>
</html>