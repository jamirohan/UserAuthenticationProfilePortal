<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login | User Portal</title>

    <style>

        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
        }

        body {
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            background: linear-gradient(135deg, #667eea, #764ba2);
        }

        .login-container {
            width: 400px;
            background: white;
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
        }

        .login-container h1 {
            text-align: center;
            margin-bottom: 10px;
            color: #333;
        }

        .subtitle {
            text-align: center;
            color: #777;
            margin-bottom: 30px;
            font-size: 14px;
        }

        .success-message {
            background: #e8f8f0;
            color: #1e8449;
            border: 1px solid #a9dfbf;
            padding: 12px;
            border-radius: 8px;
            margin-bottom: 20px;
            text-align: center;
            font-size: 14px;
            font-weight: bold;
        }

        .error-message {
            background: #fdedec;
            color: #c0392b;
            border: 1px solid #f5b7b1;
            padding: 12px;
            border-radius: 8px;
            margin-bottom: 20px;
            text-align: center;
            font-size: 14px;
            font-weight: bold;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            display: block;
            margin-bottom: 8px;
            color: #444;
            font-weight: bold;
        }

        .form-group input[type="email"],
        .form-group input[type="password"] {
            width: 100%;
            padding: 12px;
            border: 1px solid #ddd;
            border-radius: 8px;
            font-size: 15px;
            outline: none;
            transition: border-color 0.3s;
        }

        .form-group input[type="email"]:focus,
        .form-group input[type="password"]:focus {
            border-color: #667eea;
        }

        .remember {
            display: flex;
            align-items: center;
            gap: 8px;
            margin-bottom: 25px;
            color: #555;
            font-size: 14px;
        }

        .login-button {
            width: 100%;
            padding: 13px;
            border: none;
            border-radius: 8px;
            background: #667eea;
            color: white;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
            transition: background 0.3s;
        }

        .login-button:hover {
            background: #5568d9;
        }

        .register-link {
            text-align: center;
            margin-top: 25px;
            font-size: 14px;
            color: #666;
        }

        .register-link a {
            color: #667eea;
            text-decoration: none;
            font-weight: bold;
        }

        .register-link a:hover {
            text-decoration: underline;
        }

    </style>
</head>

<body>

<%
    String success = request.getParameter("success");
    String error = request.getParameter("error");
%>

    <div class="login-container">

        <!-- Registration success message -->
        <%
            if ("registered".equals(success)) {
        %>

            <div class="success-message">
                Account created successfully! Please login.
            </div>

        <%
            }
        %>


        <!-- Required fields error -->
        <%
            if ("required".equals(error)) {
        %>

            <div class="error-message">
                Please fill in all required fields.
            </div>

        <%
            }
        %>


        <!-- Invalid login error -->
        <%
            if ("invalid".equals(error)) {
        %>

            <div class="error-message">
                Invalid email or password. Please try again.
            </div>

        <%
            }
        %>


        <h1>Welcome Back</h1>

        <p class="subtitle">
            Login to your account
        </p>

        <form action="LoginServlet" method="post">

            <div class="form-group">

                <label for="email">
                    Email Address
                </label>

                <input type="email"
                       id="email"
                       name="email"
                       placeholder="Enter your email"
                       required>

            </div>


            <div class="form-group">

                <label for="password">
                    Password
                </label>

                <input type="password"
                       id="password"
                       name="password"
                       placeholder="Enter your password"
                       required>

            </div>


            <label class="remember">

                <input type="checkbox"
                       name="rememberMe"
                       value="true">

                Remember Me

            </label>


            <button type="submit"
                    class="login-button">

                Login

            </button>

        </form>


        <div class="register-link">

            Don't have an account?

            <a href="register.jsp">
                Create an account
            </a>

        </div>

    </div>

</body>
</html>