<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
</head>

<body>

    <h1>Register</h1>

    <form action="RegisterServlet" method="post">

        <label>Full Name:</label>
        <input type="text" name="fullName" required>
        <br><br>

        <label>Email:</label>
        <input type="email" name="email" required>
        <br><br>

        <label>Password:</label>
        <input type="password" name="password" required>
        <br><br>

        <button type="submit">Register</button>

    </form>

</body>
</html>