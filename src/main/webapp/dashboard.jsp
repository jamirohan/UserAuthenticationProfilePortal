<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.team3.entities.User" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard | User Portal</title>

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
        }

        /* Navigation Bar */

        .navbar {
            height: 70px;
            background: white;
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 0 50px;
            box-shadow: 0 3px 15px rgba(0, 0, 0, 0.12);
        }

        .logo {
            font-size: 22px;
            font-weight: bold;
            color: #667eea;
        }

        .nav-right {
            display: flex;
            align-items: center;
            gap: 20px;
        }

        .nav-user {
            color: #555;
            font-size: 14px;
        }

        .logout-button {
            padding: 10px 22px;
            border: none;
            border-radius: 8px;
            background: #e74c3c;
            color: white;
            font-weight: bold;
            cursor: pointer;
            font-size: 14px;
            transition: background 0.3s;
        }

        .logout-button:hover {
            background: #c0392b;
        }

        /* Main Container */

        .page-container {
            width: 950px;
            max-width: 92%;
            margin: 60px auto;
        }

        /* Welcome Card */

        .welcome-card {
            background: white;
            padding: 50px;
            border-radius: 18px;
            box-shadow: 0 18px 40px rgba(0, 0, 0, 0.2);
            text-align: center;
        }

        .welcome-icon {
            width: 90px;
            height: 90px;
            margin: 0 auto 20px;
            border-radius: 50%;
            background: linear-gradient(135deg, #667eea, #764ba2);
            color: white;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 36px;
            font-weight: bold;
            box-shadow: 0 8px 20px rgba(102, 126, 234, 0.35);
        }

        h1 {
            color: #333;
            margin-bottom: 12px;
            font-size: 30px;
        }

        .welcome-text {
            color: #777;
            font-size: 16px;
            margin-bottom: 40px;
        }

        /* Dashboard Cards */

        .cards {
            display: flex;
            gap: 25px;
            justify-content: center;
        }

        .dashboard-card {
            flex: 1;
            max-width: 350px;
            padding: 30px;
            background: #f7f8fc;
            border: 1px solid #eee;
            border-radius: 14px;
            transition: transform 0.3s, box-shadow 0.3s;
        }

        .dashboard-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.1);
        }

        .card-icon {
            width: 55px;
            height: 55px;
            margin: 0 auto 18px;
            border-radius: 12px;
            background: #667eea;
            color: white;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 24px;
            font-weight: bold;
        }

        .security-icon {
            background: #27ae60;
        }

        .dashboard-card h3 {
            color: #333;
            margin-bottom: 10px;
            font-size: 19px;
        }

        .dashboard-card p {
            color: #777;
            font-size: 14px;
            line-height: 1.6;
            margin-bottom: 22px;
        }

        /* Buttons */

        .profile-button {
            display: inline-block;
            padding: 11px 25px;
            border-radius: 8px;
            background: #667eea;
            color: white;
            text-decoration: none;
            font-weight: bold;
            font-size: 14px;
            transition: background 0.3s;
        }

        .profile-button:hover {
            background: #5568d9;
        }

        .secured-badge {
            display: inline-block;
            padding: 11px 25px;
            border-radius: 8px;
            background: #27ae60;
            color: white;
            font-weight: bold;
            font-size: 14px;
        }

        /* Footer */

        .footer-text {
            margin-top: 30px;
            text-align: center;
            color: #888;
            font-size: 13px;
        }

        /* Responsive Design */

        @media (max-width: 700px) {

            .navbar {
                padding: 0 20px;
            }

            .nav-user {
                display: none;
            }

            .page-container {
                margin: 30px auto;
            }

            .welcome-card {
                padding: 35px 20px;
            }

            h1 {
                font-size: 25px;
            }

            .cards {
                flex-direction: column;
                align-items: center;
            }

            .dashboard-card {
                width: 100%;
                max-width: 100%;
            }
        }

    </style>
</head>

<body>

<%
    User user = (User) session.getAttribute("user");

    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

    <!-- Navigation Bar -->

    <div class="navbar">

        <div class="logo">
            User Portal
        </div>

        <div class="nav-right">

            <span class="nav-user">
                Welcome, <%= user.getFullName() %>
            </span>

            <form action="LogoutServlet" method="post">

                <button type="submit"
                        class="logout-button">
                    Logout
                </button>

            </form>

        </div>

    </div>


    <!-- Main Content -->

    <div class="page-container">

        <div class="welcome-card">

            <!-- User Icon -->

            <div class="welcome-icon">

                <%= user.getFullName()
                        .substring(0, 1)
                        .toUpperCase() %>

            </div>


            <!-- Welcome Message -->

            <h1>
                Welcome, <%= user.getFullName() %>!
            </h1>

            <p class="welcome-text">
                You are successfully logged in to your account.
            </p>


            <!-- Dashboard Cards -->

            <div class="cards">


                <!-- Profile Card -->

                <div class="dashboard-card">

                    <div class="card-icon">
                        👤
                    </div>

                    <h3>
                        My Profile
                    </h3>

                    <p>
                        View your personal account information,
                        including your name, email address and user ID.
                    </p>

                    <a class="profile-button"
                       href="<%= request.getContextPath() %>/ProfileServlet">

                        View Profile

                    </a>

                </div>


                <!-- Security Card -->

                <div class="dashboard-card">

                    <div class="card-icon security-icon">
                        ✓
                    </div>

                    <h3>
                        Account Security
                    </h3>

                    <p>
                        Your account password is securely protected
                        using BCrypt password hashing.
                    </p>

                    <span class="secured-badge">
                        Secured
                    </span>

                </div>


            </div>


            <div class="footer-text">
                User Authentication &amp; Profile Portal
            </div>

        </div>

    </div>

</body>
</html>