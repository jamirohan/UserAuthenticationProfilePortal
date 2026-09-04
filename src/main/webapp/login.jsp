<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>

<body>

    <h1>Login Page</h1>

    <form action="LoginServlet" method="post">

        <label>Email:</label>
        <input type="email" name="email" required>

        <br><br>

        <label>Password:</label>
        <input type="password" name="password" required>

        <br><br>
        
        <label>
    <input type="checkbox" name="rememberMe" value="true">
  	  Remember Me
		</label>

<br><br>

        <button type="submit">Login</button>

    </form>

</body>
</html>