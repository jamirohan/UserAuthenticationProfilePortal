<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.team3.entities.User" %>

<%
    List<User> users =
            (List<User>) request.getAttribute("users");

    String error =
            request.getParameter("error");

    String success =
            request.getParameter("success");
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Manage Users</title>

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

    .back {
        text-decoration: none;
        color: white;
        background: #2563eb;
        padding: 10px 18px;
        border-radius: 6px;
        margin-right: 10px;
    }

    .logout {
        text-decoration: none;
        color: white;
        background: #dc2626;
        padding: 10px 18px;
        border-radius: 6px;
    }

    .container {
        max-width: 1100px;
        margin: 40px auto;
        padding: 0 25px;
    }

    .title {
        margin-bottom: 25px;
    }

    .title h1 {
        color: #111827;
        margin-bottom: 8px;
    }

    .title p {
        color: #6b7280;
    }

    .message {
        padding: 12px;
        border-radius: 6px;
        margin-top: 20px;
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

    .search-form {
        margin-top: 20px;
    }

    .search-input {
        padding: 12px;
        width: 300px;
        border: 1px solid #d1d5db;
        border-radius: 6px;
        font-size: 14px;
    }

    .search-btn {
        padding: 12px 20px;
        margin-left: 8px;
        background: #2563eb;
        color: white;
        border: none;
        border-radius: 6px;
        cursor: pointer;
        font-size: 14px;
    }

    .show-all {
        padding: 12px 20px;
        margin-left: 8px;
        background: #6b7280;
        color: white;
        text-decoration: none;
        border-radius: 6px;
        font-size: 14px;
    }

    .table-container {
        background: white;
        border-radius: 12px;
        padding: 20px;
        box-shadow: 0 4px 15px rgba(0, 0, 0, 0.08);
        overflow-x: auto;
    }

    table {
        width: 100%;
        border-collapse: collapse;
    }

    th {
        background: #1f2937;
        color: white;
        padding: 14px;
        text-align: left;
    }

    td {
        padding: 14px;
        border-bottom: 1px solid #e5e7eb;
        color: #374151;
    }

    tr:hover {
        background: #f9fafb;
    }

    .role {
        font-weight: bold;
    }

    .admin {
        color: #dc2626;
    }

    .user {
        color: #2563eb;
    }

    .delete-btn {
        background: #dc2626;
        color: white;
        border: none;
        padding: 8px 14px;
        border-radius: 6px;
        cursor: pointer;
        font-size: 13px;
    }

    .delete-btn:hover {
        background: #b91c1c;
    }

    .empty {
        text-align: center;
        padding: 30px;
        color: #6b7280;
    }

</style>

</head>


<body>

    <div class="navbar">

        <h2>Admin - Manage Users</h2>

        <div>

            <a href="${pageContext.request.contextPath}/admin-dashboard.jsp"
               class="back">
                Admin Dashboard
            </a>

            <a href="${pageContext.request.contextPath}/LogoutServlet"
               class="logout">
                Logout
            </a>

        </div>

    </div>


    <div class="container">

        <div class="title">

            <h1>Registered Users</h1>

            <p>
                View, search and manage users registered in the system.
            </p>


            <!-- SUCCESS MESSAGE -->

            <% if ("deleted".equals(success)) { %>

                <div class="message success">
                    User deleted successfully.
                </div>

            <% } %>


            <!-- ERROR MESSAGE -->

            <% if ("delete".equals(error)) { %>

                <div class="message error">
                    User could not be deleted. Please try again.
                </div>

            <% } %>


            <!-- SELF DELETE MESSAGE -->

            <% if ("selfdelete".equals(error)) { %>

                <div class="message error">
                    You cannot delete your own administrator account.
                </div>

            <% } %>


            <form action="${pageContext.request.contextPath}/AdminServlet"
                  method="get"
                  class="search-form">

                <input type="text"
                       name="search"
                       class="search-input"
                       placeholder="Search by name or email"
                       value="<%= request.getAttribute("search") != null
                               ? request.getAttribute("search")
                               : "" %>">

                <button type="submit"
                        class="search-btn">
                    Search
                </button>

                <a href="${pageContext.request.contextPath}/AdminServlet"
                   class="show-all">
                    Show All
                </a>

            </form>

        </div>


        <div class="table-container">

            <table>

                <thead>

                    <tr>

                        <th>User ID</th>
                        <th>Full Name</th>
                        <th>Email</th>
                        <th>Role</th>
                        <th>Created At</th>
                        <th>Action</th>

                    </tr>

                </thead>


                <tbody>

                <%
                    if (users != null && !users.isEmpty()) {

                        for (User user : users) {
                %>

                    <tr>

                        <td>
                            <%= user.getUserId() %>
                        </td>

                        <td>
                            <%= user.getFullName() %>
                        </td>

                        <td>
                            <%= user.getEmail() %>
                        </td>

                        <td class="role
                            <%= "ADMIN".equalsIgnoreCase(user.getRole())
                                    ? "admin"
                                    : "user" %>">

                            <%= user.getRole() %>

                        </td>

                        <td>
                            <%= user.getCreatedAt() %>
                        </td>

                        <td>

                            <form action="${pageContext.request.contextPath}/AdminServlet"
                                  method="post"
                                  onsubmit="return confirm('Are you sure you want to delete this user?');">

                                <input type="hidden"
                                       name="action"
                                       value="delete">

                                <input type="hidden"
                                       name="userId"
                                       value="<%= user.getUserId() %>">

                                <button type="submit"
                                        class="delete-btn">
                                    Delete
                                </button>

                            </form>

                        </td>

                    </tr>

                <%
                        }

                    } else {
                %>

                    <tr>

                        <td colspan="6"
                            class="empty">

                            No users found.

                        </td>

                    </tr>

                <%
                    }
                %>

                </tbody>

            </table>

        </div>

    </div>

</body>

</html>