<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>Admin Dashboard</title>

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

    .logout {
        text-decoration: none;
        color: white;
        background: #dc2626;
        padding: 10px 18px;
        border-radius: 6px;
        font-size: 14px;
    }

    .logout:hover {
        background: #b91c1c;
    }

    .container {
        max-width: 1100px;
        margin: 50px auto;
        padding: 0 25px;
    }

    .welcome {
        margin-bottom: 30px;
    }

    .welcome h1 {
        color: #111827;
        margin-bottom: 8px;
    }

    .welcome p {
        color: #6b7280;
    }

    .cards {
        display: grid;
        grid-template-columns: repeat(3, 1fr);
        gap: 25px;
    }

    .card {
        background: white;
        padding: 30px;
        border-radius: 12px;
        box-shadow: 0 4px 15px rgba(0, 0, 0, 0.08);
        text-align: center;
    }

    .card h3 {
        color: #374151;
        margin-bottom: 12px;
    }

    .card p {
        color: #6b7280;
        margin-bottom: 20px;
        line-height: 1.5;
    }

    .btn {
        display: inline-block;
        text-decoration: none;
        background: #2563eb;
        color: white;
        padding: 11px 20px;
        border-radius: 6px;
        font-size: 14px;
    }

    .btn:hover {
        background: #1d4ed8;
    }

    @media (max-width: 800px) {

        .cards {
            grid-template-columns: 1fr;
        }

        .navbar {
            padding: 18px 20px;
        }

    }

</style>

</head>

<body>

    <div class="navbar">

        <h2>Admin Dashboard</h2>

        <a href="${pageContext.request.contextPath}/LogoutServlet"
           class="logout">
            Logout
        </a>

    </div>


    <div class="container">

        <div class="welcome">

            <h1>Welcome, Admin</h1>

            <p>
                Manage registered users and maintain the authentication system.
            </p>

        </div>


        <div class="cards">

            <div class="card">

                <h3>View Users</h3>

                <p>
                    View all registered users and their account information.
                </p>

                <a href="${pageContext.request.contextPath}/AdminServlet"
   class="btn">
    View Users
</a>

            </div>


            <div class="card">

                <h3>Search Users</h3>

                <p>
                    Search for registered users using their details.
                </p>

                <a href="#" class="btn">
                    Search Users
                </a>

            </div>


            <div class="card">

                <h3>Manage Users</h3>

                <p>
                    Delete or manage registered user accounts.
                </p>

                <a href="#" class="btn">
                    Manage Users
                </a>

            </div>

        </div>

    </div>

</body>

</html>